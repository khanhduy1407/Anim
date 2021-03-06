package tk.nkduy.anim.core.alpha;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

import tk.nkduy.anim.ViewCalculator;
import tk.nkduy.anim.core.AnimExpectation;
import tk.nkduy.anim.core.ExpectAnimManager;

import java.util.ArrayList;
import java.util.List;

public class ExpectAnimAlphaManager extends ExpectAnimManager {

    private Float alpha = null;

    public ExpectAnimAlphaManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
    }

    @Override
    public void calculate() {
        for (AnimExpectation expectation : animExpectations) {
            if (expectation instanceof AlphaAnimExpectation) {
                final Float alpha = ((AlphaAnimExpectation) expectation).getCalculatedAlpha(viewToMove);
                if (alpha != null) {
                    this.alpha = alpha;
                }
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        final List<Animator> animations = new ArrayList<>();

        calculate();

        if (alpha != null) {
            final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewToMove, View.ALPHA, alpha);

            if (alpha == 0) {
                if (viewToMove.getAlpha() != 0) {
                    animations.add(objectAnimator);

                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            viewToMove.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            } else if (alpha == 1) {
                if (viewToMove.getAlpha() != 1) {
                    animations.add(objectAnimator);

                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            viewToMove.setVisibility(View.VISIBLE);
                        }
                    });
                }
            } else {
                animations.add(objectAnimator);

                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        viewToMove.setVisibility(View.VISIBLE);
                    }
                });
            }

        }

        return animations;
    }
}
