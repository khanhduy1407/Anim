package tk.nkduy.anim;

import android.animation.Animator;
import androidx.annotation.Nullable;
import android.view.View;

import tk.nkduy.anim.core.AnimExpectation;
import tk.nkduy.anim.core.alpha.ExpectAnimAlphaManager;
import tk.nkduy.anim.core.anim3d.ExpectAnimCameraDistanceManager;
import tk.nkduy.anim.core.custom.ExpectAnimCustomManager;
import tk.nkduy.anim.core.position.ExpectAnimPositionManager;
import tk.nkduy.anim.core.rotation.ExpectAnimRotationManager;
import tk.nkduy.anim.core.scale.ExpectAnimScaleManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewExpectation {

    private final Anim anim;
    private final View viewToMove;
    private final List<View> dependencies;
    private List<Animator> animations;
    private List<AnimExpectation> animExpectations;

    private Float willHasScaleX;
    private Float willHasScaleY;

    private Float willHasPositionX;
    private Float willHasPositionY;

    private Float willHasRotationX;

    private Float willHaveRotationX, willHaveRotationY;
    private Float willHaveCameraDistance;

    ViewExpectation(Anim anim, View viewToMove) {
        this.anim = anim;
        this.viewToMove = viewToMove;
        this.animations = new ArrayList<>();
        this.animExpectations = new ArrayList<>();
        this.dependencies = new ArrayList<>();
    }

    public ViewExpectation expect(View view) {
        return anim.expect(view);
    }

    public ViewExpectation toBe(AnimExpectation... animExpectations) {
        this.animExpectations.addAll(Arrays.asList(animExpectations));
        return this;
    }

    private void calculatePosition(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimPositionManager manager = new ExpectAnimPositionManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            willHasPositionX = manager.getPositionX();
            willHasPositionY = manager.getPositionY();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculateScale(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimScaleManager manager = new ExpectAnimScaleManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            willHasScaleX = manager.getScaleX();
            willHasScaleY = manager.getScaleY();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculateAlpha(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimAlphaManager manager = new ExpectAnimAlphaManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculate3DTransforms(ViewCalculator viewCalculator) {
        if (animExpectations != null) {

            // camera distance animations
            final ExpectAnimCameraDistanceManager cameraDistanceManager = new ExpectAnimCameraDistanceManager(animExpectations, viewToMove, viewCalculator);
            cameraDistanceManager.calculate();
            willHaveCameraDistance = cameraDistanceManager.getCameraDistance();
            animations.addAll(cameraDistanceManager.getAnimators());

        }

    }

    private void calculateRotation(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimRotationManager manager = new ExpectAnimRotationManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            willHasRotationX = manager.getRotation();
            willHaveRotationX = manager.getRotationX();
            willHaveRotationY = manager.getRotationY();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculateCustom(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimCustomManager manager = new ExpectAnimCustomManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            animations.addAll(manager.getAnimators());
        }

    }

    Anim start() {
        return anim.start();
    }

    void setPercent(float percent) {
        anim.setPercent(percent);
    }

    void calculate(ViewCalculator viewCalculator) {
        calculate3DTransforms(viewCalculator);
        calculateRotation(viewCalculator);
        calculateScale(viewCalculator);
        calculatePosition(viewCalculator);
        calculateAlpha(viewCalculator);
        calculateCustom(viewCalculator);
    }

    List<Animator> getAnimations() {
        return animations;
    }

    List<View> calculateDependencies() {
        dependencies.clear();
        if (animExpectations != null) {
            for (AnimExpectation animExpectation : animExpectations) {
                dependencies.addAll(animExpectation.getViewsDependencies());
            }
        }
        return dependencies;
    }

    List<View> getDependencies() {
        return dependencies;
    }

    View getViewToMove() {
        return viewToMove;
    }

    Float getFuturPositionX() {
        return willHasPositionX;
    }

    Float getFuturPositionY() {
        return willHasPositionY;
    }

    Float getWillHasScaleX() {
        if (willHasScaleX != null) {
            return willHasScaleX;
        } else {
            return 1f;
        }
    }

    Float getWillHasScaleY() {
        if (willHasScaleY != null) {
            return willHasScaleY;
        } else {
            return 1f;
        }
    }

    Float getWillHaveRotation() {
        if (willHasRotationX != null) {
            return willHasRotationX;
        } else {
            return null;
        }
    }

    @Nullable
    Float getWillHaveRotationX() {
        return willHaveRotationX;
    }

    @Nullable
    Float getWillHaveRotationY() {
        return willHaveRotationY;
    }

    public Anim toAnimation() {
        return anim;
    }
}
