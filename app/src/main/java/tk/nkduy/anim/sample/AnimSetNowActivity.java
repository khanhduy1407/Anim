package tk.nkduy.anim.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import tk.nkduy.anim.Anim;

import butterknife.BindView;
import butterknife.ButterKnife;

import static tk.nkduy.anim.core.Expectations.invisible;

public class AnimSetNowActivity extends AppCompatActivity {

    @BindView(R.id.follow)
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity_set_now);
        ButterKnife.bind(this);

        new Anim()
                .expect(view)
                .toBe(
                        invisible()
                )
                .toAnimation()
                .setNow();

    }

}
