package tk.nkduy.anim.core.alpha;

import android.view.View;

import tk.nkduy.anim.core.AnimExpectation;

public abstract class AlphaAnimExpectation extends AnimExpectation{
    public abstract Float getCalculatedAlpha(View viewToMove);
}
