package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationAlignBottom extends PositionAnimationViewDependant {

    public PositionAnimExpectationAlignBottom(View otherView) {
        super(otherView);

        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return viewCalculator.finalPositionBottomOfView(otherView) - getMargin(viewToMove) - viewToMove.getHeight();
    }
}
