package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationLeftOfParent extends PositionAnimExpectation {

    public PositionAnimExpectationLeftOfParent() {
        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return getMargin(viewToMove);
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
