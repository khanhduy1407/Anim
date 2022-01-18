package tk.nkduy.anim.core.rotation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import androidx.annotation.Nullable;
import android.view.View;

import tk.nkduy.anim.ViewCalculator;
import tk.nkduy.anim.core.AnimExpectation;
import tk.nkduy.anim.core.ExpectAnimManager;

import java.util.ArrayList;
import java.util.List;

public class ExpectAnimRotationManager extends ExpectAnimManager {

    @Nullable
    private Float rotation = null;

    @Nullable
    private Float rotationX;

    @Nullable
    private Float rotationY;

    public ExpectAnimRotationManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
    }

    @Override
    public void calculate() {
        for (AnimExpectation expectation : animExpectations) {
            if (expectation instanceof RotationExpectation) {
                final Float rotation = ((RotationExpectation) expectation).getCalculatedRotation(viewToMove);
                if (rotation != null) {
                    this.rotation = rotation;
                }

                final Float rotationX = ((RotationExpectation) expectation).getCalculatedRotationX(viewToMove);
                if (rotationX != null) {
                    this.rotationX = rotationX;
                }

                final Float rotationY = ((RotationExpectation) expectation).getCalculatedRotationY(viewToMove);
                if (rotationY != null) {
                    this.rotationY = rotationY;
                }
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        final List<Animator> animations = new ArrayList<>();

        calculate();

        if (rotation != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ROTATION, rotation));
        }

        if (rotationX != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ROTATION_X, rotationX));
        }
        if (rotationY != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ROTATION_Y, rotationY));
        }

        return animations;
    }

    public Float getRotation() {
        return rotation;
    }

    @Nullable
    public Float getRotationX() {
        return rotationX;
    }

    @Nullable
    public Float getRotationY() {
        return rotationY;
    }
}
