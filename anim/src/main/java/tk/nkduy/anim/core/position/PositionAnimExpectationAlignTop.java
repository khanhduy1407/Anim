package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationAlignTop extends PositionAnimationViewDependant {

    public PositionAnimExpectationAlignTop(View otherView) {
        super(otherView);

        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return viewCalculator.finalPositionTopOfView(otherView) + getMargin(viewToMove);
    }
}
