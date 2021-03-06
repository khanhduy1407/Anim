package tk.nkduy.anim.core.scale;

import android.view.View;

public class ScaleAnimExpectationSameWidthAs extends ScaleAnimExpectationViewDependant {

    public ScaleAnimExpectationSameWidthAs(View otherView, Integer gravityHorizontal, Integer gravityVertical) {
        super(otherView, gravityHorizontal, gravityVertical);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        final int viewToMoveWidth = viewToMove.getWidth();

        final float otherViewWidth = viewCalculator.finalWidthOfView(otherView);

        if (otherViewWidth == 0 || viewToMoveWidth == 0f) {
            return 0f;
        }
        return otherViewWidth / viewToMoveWidth;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        if(keepRatio){
            return getCalculatedValueScaleX(viewToMove);
        }
        return null;
    }
}
