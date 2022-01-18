package tk.nkduy.anim.core.scale;

import android.view.View;

public class ScaleAnimExpectationSameScaleAs extends ScaleAnimExpectationViewDependant {

    public ScaleAnimExpectationSameScaleAs(View otherView) {
        super(otherView, null, null);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        return otherView.getScaleX();
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        return otherView.getScaleY();
    }
}
