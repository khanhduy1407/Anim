package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationTopOfParent extends PositionAnimExpectation {

    public PositionAnimExpectationTopOfParent() {
        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return getMargin(viewToMove);
    }
}
