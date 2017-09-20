package cashkaro.com.cashkarodeals.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.model.ImageSliderImageModel;

/**
 * Adapter for displaying Sliding Images on Home Screen
 */

public class SlidingImage_Adapter extends PagerAdapter {

    private final ArrayList<ImageSliderImageModel> imageSliderImageModelArrayList;
    private final LayoutInflater inflater;

    public SlidingImage_Adapter(Context context, ArrayList<ImageSliderImageModel> imageSliderImageModelArrayList) {
        this.imageSliderImageModelArrayList = imageSliderImageModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageSliderImageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        assert imageLayout != null;
        @SuppressWarnings("RedundantCast") final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        imageView.setImageResource(imageSliderImageModelArrayList.get(position).getImage_drawable());
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
