package tk.nkduy.anim.core.scale;

import androidx.annotation.Nullable;
import android.view.View;

public class ScaleAnimExpectationValues extends ScaleAnimExpectation {

    private final float scaleX;
    private final float scaleY;

    public ScaleAnimExpectationValues(float scaleX, float scaleY, @Nullable Integer gravityHorizontal, @Nullable Integer gravityVertical) {
        super(gravityHorizontal, gravityVertical);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        return scaleX;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        return scaleY;
    }
}
