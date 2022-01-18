package tk.nkduy.anim.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import tk.nkduy.anim.Anim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static tk.nkduy.anim.core.Expectations.flippedHorizontally;
import static tk.nkduy.anim.core.Expectations.flippedHorizontallyAndVertically;
import static tk.nkduy.anim.core.Expectations.flippedVertically;
import static tk.nkduy.anim.core.Expectations.withCameraDistance;

public class AnimConcatActivity extends AppCompatActivity {

    @BindView(R.id.image_1)
    View image1;
    @BindView(R.id.image_2)
    View image2;
    @BindView(R.id.image_3)
    View image3;
    @BindView(R.id.image_4)
    View image4;

    private Anim animMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_flip);
        ButterKnife.bind(this);

        this.animMove = Anim.concat(
                new Anim()
                        .expect(image1)
                        .toBe(
                                withCameraDistance(500f),
                                flippedHorizontally()
                        )
                        .toAnimation()
                        .setDuration(1000),
                new Anim()
                        .expect(image2)
                        .toBe(
                                withCameraDistance(1000f),
                                flippedVertically()
                        )
                        .toAnimation()
                        .setDuration(500),
                new Anim()
                        .expect(image3)
                        .toBe(
                                withCameraDistance(1500f),
                                flippedVertically()
                        )
                        .toAnimation()
                        .setDuration(300),
                new Anim()
                        .expect(image4)
                        .toBe(
                                withCameraDistance(2000f),
                                flippedHorizontallyAndVertically()
                        )
                        .toAnimation()
                        .setDuration(1000)
        )
        .start();
    }

    @OnClick(R.id.content)
    public void onMoveClicked() {
        animMove.setPercent(0f);
        animMove.start();
    }

}
