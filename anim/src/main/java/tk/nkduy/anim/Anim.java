package tk.nkduy.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tk.nkduy.anim.listener.AnimationEndListener;
import tk.nkduy.anim.listener.AnimationStartListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Anim {

    private static final long DEFAULT_DURATION = 300l;

    private List<ViewExpectation> expectationList;
    private View anyView;

    private long startDelay = 5;

    private List<View> viewToMove;
    private ViewCalculator viewCalculator;

    private AnimatorSet animatorSet;

    private List<AnimationEndListener> endListeners = new ArrayList<>();
    private List<AnimationStartListener> startListeners = new ArrayList<>();
    private AtomicBoolean isPlaying = new AtomicBoolean(false);


    @Nullable
    private Interpolator interpolator;
    private Long duration = DEFAULT_DURATION;

    public Anim() {
        this.expectationList = new ArrayList<>();
        this.viewToMove = new ArrayList<>();
        this.viewCalculator = new ViewCalculator();
    }

    public ViewExpectation expect(View view) {
        this.anyView = view;
        final ViewExpectation viewExpectation = new ViewExpectation(this, view);
        expectationList.add(viewExpectation);
        return viewExpectation;
    }

    private Anim calculate() {
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();

            if (interpolator != null) {
                animatorSet.setInterpolator(interpolator);
            }

            animatorSet.setDuration(duration);

            final List<Animator> animatorList = new ArrayList<>();

            final List<ViewExpectation> expectationsToCalculate = new ArrayList<>();

            //"ViewDependencies" = récupérer toutes les vues des "Expectations"
            for (ViewExpectation viewExpectation : expectationList) {
                viewExpectation.calculateDependencies();
                viewToMove.add(viewExpectation.getViewToMove());
                expectationsToCalculate.add(viewExpectation);

                viewCalculator.setExpectationForView(viewExpectation.getViewToMove(), viewExpectation);
            }

            while (!expectationsToCalculate.isEmpty()) {
                //pour chaque expectation dans "Expectations"
                final Iterator<ViewExpectation> iterator = expectationsToCalculate.iterator();
                while (iterator.hasNext()) {
                    final ViewExpectation viewExpectation = iterator.next();

                    //regarder si une de ces dépendance est dans "ViewDependencies"
                    if (!hasDependency(viewExpectation)) {
                        //si non
                        viewExpectation.calculate(viewCalculator);
                        animatorList.addAll(viewExpectation.getAnimations());

                        final View view = viewExpectation.getViewToMove();
                        viewToMove.remove(view);
                        viewCalculator.wasCalculated(viewExpectation);

                        iterator.remove();
                    } else {
                        //si oui, attendre le prochain tour
                    }
                }
            }

            animatorSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isPlaying.set(false);
                    notifyListenerEnd();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    isPlaying.set(true);
                    notifyListenerStart();
                }

            });

            animatorSet.playTogether(animatorList);
        }
        return this;
    }

    private void notifyListenerStart() {
        for (AnimationStartListener startListener : startListeners) {
            if (startListener != null) {
                startListener.onAnimationStart(Anim.this);
            }
        }
    }

    private void notifyListenerEnd() {
        for (AnimationEndListener endListener : endListeners) {
            if (endListener != null) {
                endListener.onAnimationEnd(Anim.this);
            }
        }
    }

    public Anim setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public Anim start() {
        executeAfterDraw(anyView, new Runnable() {
            @Override
            public void run() {
                calculate();
                animatorSet.start();
            }
        });
        return this;
    }

    private boolean hasDependency(ViewExpectation viewExpectation) {
        final List<View> dependencies = viewExpectation.getDependencies();
        if (!dependencies.isEmpty()) {
            for (View view : viewToMove) {
                if (dependencies.contains(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPercent(float percent) {
        calculate();
        if (animatorSet != null) {
            final ArrayList<Animator> anims = animatorSet.getChildAnimations();
            for (Animator animator : anims) {
                if (animator instanceof ValueAnimator) {
                    ((ValueAnimator) animator).setCurrentPlayTime((long) (percent * animator.getDuration()));
                }
            }
        }
    }

    public boolean isPlaying() {
        return isPlaying.get();
    }

    public void setNow() {
        executeAfterDraw(anyView, new Runnable() {
            @Override
            public void run() {
                setPercent(1f);
            }
        });
    }

    public void executeAfterDraw(final View view, final Runnable runnable) {
        view.postDelayed(runnable, Math.max(5, startDelay));
    }

    public void reset() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "percent", 1f, 0f);
        objectAnimator.setDuration(duration);
        if (interpolator != null) {
            objectAnimator.setInterpolator(interpolator);
        }
        objectAnimator.start();
    }

    public Anim setInterpolator(@NonNull Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public Anim setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public Anim addEndListener(AnimationEndListener listener) {
        this.endListeners.add(listener);
        return this;
    }

    public Anim addStartListener(AnimationStartListener listener) {
        this.startListeners.add(listener);
        return this;
    }

    private void concatWith(final Anim otherAnim) {
        addEndListener(new AnimationEndListener() {
            @Override
            public void onAnimationEnd(Anim anim) {
                otherAnim.start();
            }
        });
    }

    public static Anim concat(Anim anim, Anim... anims) {
        if(anims.length > 0) {
            anim.concatWith(anims[0]);
            for (int i = 0; i < anims.length - 1; i++) {
                anims[i].concatWith(anims[i + 1]);
            }
        }
        return anim;
    }
}
