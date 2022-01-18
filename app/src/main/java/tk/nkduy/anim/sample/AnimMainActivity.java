package tk.nkduy.anim.sample;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sample)
    public void onSampleClicked() {
        startActivity(new Intent(this, AnimSampleActivity.class));
    }

    @OnClick(R.id.scroll)
    public void onScrollClicked() {
        startActivity(new Intent(this, AnimScrollActivity.class));
    }

    @OnClick(R.id.rotation)
    public void onRotationClicked() {
        startActivity(new Intent(this, AnimRotationActivity.class));
    }

    @OnClick(R.id.flip)
    public void onFlipClicked() {
        startActivity(new Intent(this, AnimFlipActivity.class));
    }

    @OnClick(R.id.setnow)
    public void onSetNowClicked() {
        startActivity(new Intent(this, AnimSetNowActivity.class));
    }

    @OnClick(R.id.visible)
    public void onVisibleClicked() {
        startActivity(new Intent(this, AnimAlphaActivity.class));
    }

    @OnClick(R.id.concat)
    public void onConcatClicked() {
        startActivity(new Intent(this, AnimConcatActivity.class));
    }

}
