package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationAlignRight extends PositionAnimationViewDependant {

    public PositionAnimExpectationAlignRight(View otherView) {
        super(otherView);

        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return viewCalculator.finalPositionRightOfView(otherView) - getMargin(viewToMove) - viewToMove.getWidth();
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
