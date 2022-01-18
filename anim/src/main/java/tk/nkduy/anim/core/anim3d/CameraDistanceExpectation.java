package tk.nkduy.anim.core.anim3d;

import androidx.annotation.Nullable;
import android.view.View;

import tk.nkduy.anim.core.AnimExpectation;

public abstract class CameraDistanceExpectation extends AnimExpectation {

    @Nullable
    public abstract Float getCalculatedCameraDistance(View viewToMove);

}
