package cashkaro.com.cashkarodeals.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Minor Animation for Recycler View
 */

public class AnimationUtil {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown ? 200 : -200, 0);
        animatorTranslateY.setDuration(1000);
        animatorSet.playTogether(animatorTranslateY);
        animatorSet.start();

    }
}
