package tk.nkduy.anim.core;

import android.view.View;

import tk.nkduy.anim.ViewCalculator;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimExpectation {

    protected ViewCalculator viewCalculator;

    public void setViewCalculator(ViewCalculator viewCalculator) {
        this.viewCalculator = viewCalculator;
    }

    public List<View> getViewsDependencies(){
        return new ArrayList<>();
    }
}
