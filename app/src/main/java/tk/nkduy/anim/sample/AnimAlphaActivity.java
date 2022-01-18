package tk.nkduy.anim.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tk.nkduy.anim.Anim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static tk.nkduy.anim.core.Expectations.invisible;
import static tk.nkduy.anim.core.Expectations.visible;

public class AnimAlphaActivity extends AppCompatActivity {

    @BindView(R.id.image_1)
    View image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_alpha);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_1)
    public void onClickdImage(){
        Toast.makeText(getBaseContext(), "click", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.visible)
    public void onVisibleClicked() {
        new Anim()
                .expect(image1)
                .toBe(
                        visible()
                )
                .toAnimation()
                .setDuration(1000)
                .start();
    }

    @OnClick(R.id.invisible)
    public void onInvisibleClicked() {
        new Anim()
                .expect(image1)
                .toBe(
                        invisible()
                )
                .toAnimation()
                .setDuration(1000)
                .start();
    }

}
