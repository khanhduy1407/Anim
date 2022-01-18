package tk.nkduy.anim.core.custom;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

public class TextColorAnimExpectation extends CustomAnimExpectation {

    private final int textColor;

    public TextColorAnimExpectation(int textColor) {
        this.textColor = textColor;
    }


    @Override
    public Animator getAnimator(View viewToMove) {
        if (viewToMove instanceof TextView) {
            final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewToMove, "textColor", ((TextView) viewToMove).getCurrentTextColor(), textColor);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            return objectAnimator;
        } else {
            return null;
        }
    }
}
