package tk.nkduy.anim.core.rotation;

import android.view.View;

import tk.nkduy.anim.core.AnimExpectation;

public abstract class RotationExpectation extends AnimExpectation {

    public abstract Float getCalculatedRotation(View viewToMove);
    public abstract Float getCalculatedRotationX(View viewToMove);
    public abstract Float getCalculatedRotationY(View viewToMove);
}
