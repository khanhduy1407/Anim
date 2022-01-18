package tk.nkduy.anim.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import tk.nkduy.anim.Anim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static tk.nkduy.anim.core.Expectations.alignLeft;
import static tk.nkduy.anim.core.Expectations.alignTop;
import static tk.nkduy.anim.core.Expectations.belowOf;
import static tk.nkduy.anim.core.Expectations.leftOfParent;
import static tk.nkduy.anim.core.Expectations.toRightOf;
import static tk.nkduy.anim.core.Expectations.topOfParent;
import static tk.nkduy.anim.core.Expectations.rotated;

public class AnimRotationActivity extends AppCompatActivity {

    @BindView(R.id.text1)
    View text1;
    @BindView(R.id.text2)
    View text2;
    @BindView(R.id.text3)
    View text3;
    @BindView(R.id.text4)
    View text4;

    private Anim animMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_rotation);
        ButterKnife.bind(this);

        this.animMove = new Anim()

                .expect(text1)
                .toBe(
                        topOfParent(),
                        leftOfParent(),
                        rotated(90)
                )

                .expect(text2)
                .toBe(
                        alignLeft(text1),
                        belowOf(text1)
                )

                .expect(text3)
                .toBe(
                        alignTop(text1),
                        toRightOf(text1)
                )

                .expect(text4)
                .toBe(
                        belowOf(text3),
                        alignLeft(text3)
                )

                .toAnimation()
                .setDuration(1500);
    }

    @OnClick(R.id.content)
    public void onMoveClicked() {
        if (text1.getRotation() == 0) {
            animMove.start();
        } else {
            animMove.reset();
        }
    }

}
