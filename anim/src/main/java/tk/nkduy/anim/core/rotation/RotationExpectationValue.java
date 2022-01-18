package tk.nkduy.anim.core.rotation;

import android.view.View;

public class RotationExpectationValue extends RotationExpectation {

    private final float rotation;

    public RotationExpectationValue(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public Float getCalculatedRotation(View viewToMove) {
        return rotation;
    }

    @Override
    public Float getCalculatedRotationX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedRotationY(View viewToMove) {
        return null;
    }
}
