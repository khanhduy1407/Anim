package tk.nkduy.anim.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.view.Gravity;
import android.view.View;

import tk.nkduy.anim.Anim;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

import static tk.nkduy.anim.core.Expectations.alpha;
import static tk.nkduy.anim.core.Expectations.height;
import static tk.nkduy.anim.core.Expectations.leftOfParent;
import static tk.nkduy.anim.core.Expectations.rightOfParent;
import static tk.nkduy.anim.core.Expectations.sameCenterVerticalAs;
import static tk.nkduy.anim.core.Expectations.scale;
import static tk.nkduy.anim.core.Expectations.toRightOf;
import static tk.nkduy.anim.core.Expectations.topOfParent;

public class AnimScrollActivity extends AppCompatActivity {

    @BindView(R.id.username)
    View username;
    @BindView(R.id.avatar)
    View avatar;
    @BindView(R.id.follow)
    View follow;
    @BindView(R.id.background)
    View backbground;

    @BindView(R.id.scrollview)
    NestedScrollView scrollView;

    @BindDimen(R.dimen.height)
    int height;

    private Anim animMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_scroll);
        ButterKnife.bind(this);

        this.animMove = new Anim()
                .expect(avatar)
                .toBe(
                        topOfParent().withMarginDp(20),
                        leftOfParent().withMarginDp(20),
                        scale(0.5f, 0.5f)
                )

                .expect(username)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),

                        alpha(0.5f)
                )

                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(20),
                        sameCenterVerticalAs(avatar)
                )

                .expect(backbground)
                .toBe(
                        height(height).withGravity(Gravity.LEFT, Gravity.TOP)
                )

                .toAnimation();

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                final float percent = (scrollY * 1f) / v.getMaxScrollAmount();
                animMove.setPercent(percent);
            }
        });
    }

}
