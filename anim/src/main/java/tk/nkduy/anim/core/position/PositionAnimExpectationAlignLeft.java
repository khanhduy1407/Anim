package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationAlignLeft extends PositionAnimationViewDependant {

    public PositionAnimExpectationAlignLeft(View otherView) {
        super(otherView);

        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return viewCalculator.finalPositionLeftOfView(otherView) + getMargin(viewToMove) ;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
