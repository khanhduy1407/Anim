package tk.nkduy.anim.core.position;

import android.view.View;

import tk.nkduy.anim.ViewCalculator;

import java.util.List;

public abstract class PositionAnimationViewDependant extends PositionAnimExpectation {

    protected View otherView;

    public PositionAnimationViewDependant(View otherView) {
        this.otherView = otherView;
    }

    @Override
    public List<View> getViewsDependencies() {
        final List<View> viewsDependencies = super.getViewsDependencies();
        viewsDependencies.add(otherView);
        return viewsDependencies;
    }

    public void setViewCalculator(ViewCalculator viewCalculator) {
        this.viewCalculator = viewCalculator;
    }

}
