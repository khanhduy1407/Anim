package tk.nkduy.anim.core.position;

import android.view.View;

public class PositionAnimExpectationOriginal extends PositionAnimExpectation {

    public PositionAnimExpectationOriginal() {
        setForTranslationX(true);
        setForTranslationY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return 0f;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return 0f;
    }
}
