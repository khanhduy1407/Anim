package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationRightOf extends PositionAnimationViewDependant {

    public PositionAnimExpectationRightOf(View otherView) {
        super(otherView);

        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return viewCalculator.finalPositionRightOfView(otherView) + getMargin(viewToMove);
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
