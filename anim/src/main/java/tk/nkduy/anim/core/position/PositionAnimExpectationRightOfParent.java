package tk.nkduy.anim.core.position;

import android.view.View;
import android.view.ViewParent;

public class PositionAnimExpectationRightOfParent extends PositionAnimExpectation {

    public PositionAnimExpectationRightOfParent() {
        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        final ViewParent viewParent = viewToMove.getParent();
        if((viewParent instanceof View)){
            final View parentView = (View)viewParent;
            return parentView.getWidth() - getMargin(viewToMove) - viewCalculator.finalWidthOfView(viewToMove);
        }
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
