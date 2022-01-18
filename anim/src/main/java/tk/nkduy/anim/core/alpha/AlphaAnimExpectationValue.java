package tk.nkduy.anim.core.alpha;

import android.view.View;

public class AlphaAnimExpectationValue extends AlphaAnimExpectation {

    private final float alpha;

    public AlphaAnimExpectationValue(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public Float getCalculatedAlpha(View viewToMove) {
        return alpha;
    }
}
