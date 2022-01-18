package tk.nkduy.anim.core.scale;

import android.view.View;

public class ScaleAnimExpectationOriginalScale extends ScaleAnimExpectation {

    public ScaleAnimExpectationOriginalScale() {
        super(null, null);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        return 1f;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        return 1f;
    }
}
