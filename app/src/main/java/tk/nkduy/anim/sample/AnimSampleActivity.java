package tk.nkduy.anim.sample;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import tk.nkduy.anim.Anim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static tk.nkduy.anim.core.Expectations.aboveOf;
import static tk.nkduy.anim.core.Expectations.atItsOriginalPosition;
import static tk.nkduy.anim.core.Expectations.bottomOfParent;
import static tk.nkduy.anim.core.Expectations.invisible;
import static tk.nkduy.anim.core.Expectations.leftOfParent;
import static tk.nkduy.anim.core.Expectations.outOfScreen;
import static tk.nkduy.anim.core.Expectations.rightOfParent;
import static tk.nkduy.anim.core.Expectations.sameCenterVerticalAs;
import static tk.nkduy.anim.core.Expectations.toHaveBackgroundAlpha;
import static tk.nkduy.anim.core.Expectations.toHaveTextColor;
import static tk.nkduy.anim.core.Expectations.toRightOf;
import static tk.nkduy.anim.core.Expectations.visible;
import static tk.nkduy.anim.core.Expectations.width;

public class AnimSampleActivity extends AppCompatActivity {

    @BindView(R.id.name)
    View name;
    @BindView(R.id.avatar)
    View avatar;
    @BindView(R.id.subname)
    View subname;
    @BindView(R.id.follow)
    View follow;
    @BindView(R.id.message)
    View message;

    @BindView(R.id.bottomLayout)
    View bottomLayout;
    @BindView(R.id.content)
    View content;

    private Anim animMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_sample);
        ButterKnife.bind(this);

        new Anim()
                .expect(bottomLayout)
                .toBe(
                        outOfScreen(Gravity.BOTTOM)
                )
                .expect(content)
                .toBe(
                        outOfScreen(Gravity.BOTTOM),
                        invisible()
                )
                .toAnimation()
                .setNow();

        this.animMove = new Anim()

                .expect(avatar)
                .toBe(
                        bottomOfParent().withMarginDp(36),
                        leftOfParent().withMarginDp(16),
                        width(40).toDp().keepRatio()
                )

                .expect(name)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),
                        toHaveTextColor(Color.WHITE)
                )

                .expect(subname)
                .toBe(
                        toRightOf(name).withMarginDp(5),
                        sameCenterVerticalAs(name),
                        toHaveTextColor(Color.WHITE)
                )

                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(4),
                        bottomOfParent().withMarginDp(12),
                        toHaveBackgroundAlpha(0f)
                )

                .expect(message)
                .toBe(
                        aboveOf(follow).withMarginDp(4),
                        rightOfParent().withMarginDp(4),
                        toHaveBackgroundAlpha(0f)
                )

                .expect(bottomLayout)
                .toBe(
                        atItsOriginalPosition()
                )

                .expect(content)
                .toBe(
                        atItsOriginalPosition(),
                        visible()
                )

                .toAnimation()
                .setDuration(1500);
    }

    @OnClick(R.id.message)
    public void onMoveClicked() {
        animMove.start();
    }

    @OnClick(R.id.follow)
    public void onResetClicked() {
        animMove.reset();
    }

}
