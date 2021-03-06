package tk.nkduy.anim.core.scale;

import androidx.annotation.Nullable;
import android.view.View;

public class ScaleAnimExpectationHeight extends ScaleAnimExpectation {

    private int height;

    public ScaleAnimExpectationHeight(int height, @Nullable Integer gravityHorizontal, @Nullable Integer gravityVertical) {
        super(gravityHorizontal, gravityVertical);
        this.height = height;
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        if(keepRatio){
            return getCalculatedValueScaleY(viewToMove);
        }
        return null;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {

        if(toDp){
            this.height = dpToPx(this.height, viewToMove);
        }

        final int viewToMoveHeight = viewToMove.getHeight();
        if(this.height == 0 || viewToMoveHeight == 0f){
            return 0f;
        }
        return 1f * this.height / viewToMoveHeight;
    }
}
