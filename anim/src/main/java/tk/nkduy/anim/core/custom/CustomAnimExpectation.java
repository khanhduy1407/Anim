package tk.nkduy.anim.core.custom;

import android.animation.Animator;
import android.view.View;

import tk.nkduy.anim.core.AnimExpectation;

public abstract class CustomAnimExpectation extends AnimExpectation {
    public abstract Animator getAnimator(View viewToMove);
}
