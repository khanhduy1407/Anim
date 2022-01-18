package tk.nkduy.anim.core;

import android.animation.Animator;
import android.view.View;

import tk.nkduy.anim.ViewCalculator;

import java.util.List;

public abstract class ExpectAnimManager {

    protected final List<AnimExpectation> animExpectations;
    protected final View viewToMove;
    protected final ViewCalculator viewCalculator;

    public ExpectAnimManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        this.animExpectations = animExpectations;
        this.viewToMove = viewToMove;
        this.viewCalculator = viewCalculator;
    }

    public abstract void calculate();

    public abstract List<Animator> getAnimators();

}
