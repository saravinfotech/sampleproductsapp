package cashkaro.com.cashkarodeals.view.homefeature;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.adapter.SlidingImage_Adapter;
import cashkaro.com.cashkarodeals.model.ImageSliderImageModel;
import cashkaro.com.cashkarodeals.utils.AnalyticsHelper;
import cashkaro.com.cashkarodeals.utils.Constants;
import cashkaro.com.cashkarodeals.utils.CustomViewPager;
import cashkaro.com.cashkarodeals.utils.DisplayLocalNotification;
import cashkaro.com.cashkarodeals.view.Home;
import cashkaro.com.cashkarodeals.view.storedetails.StoreDetailsActivity;

/**
 * Fragment to inialize the Image Slider on the Home Screen
 */

public class ImageSliderFragment extends Fragment {

    private static int NUM_PAGES = 0;
    private static int currentPage = 0;
    private CustomViewPager slidingImagePager;
    private CirclePageIndicator indicator;
    private ArrayList<ImageSliderImageModel> imageSliderImageModelArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageSliderImageModelArrayList = new ArrayList<>();
        imageSliderImageModelArrayList = populateList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sliding_image_layout,
                container, false);
        //noinspection RedundantCast
        slidingImagePager = (CustomViewPager) view.findViewById(R.id.slidingImagePager);
        //noinspection RedundantCast
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        initImageSlider();
        initPageIndicator();
        handlePageSlideAnimation();
        return view;
    }

    /**
     * Initiate the sliding images
     */
    private void initImageSlider() {
        slidingImagePager.setAdapter(new SlidingImage_Adapter(getActivity(), imageSliderImageModelArrayList));
        slidingImagePager.setOnItemClickListener(new CustomViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                dealsImageOnclick(position);
            }
        });
    }

    /**
     * Initiate the page Indicator for the slider
     */
    private void initPageIndicator() {
        indicator.setViewPager(slidingImagePager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
    }

    /**
     * Method to handle the page slide animation
     */
    private void handlePageSlideAnimation() {
        NUM_PAGES = imageSliderImageModelArrayList.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                slidingImagePager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 1000);
    }

    /**
     * Action to be taken when the deals Image is clicked
     * Display local Notification when a deals image is clicked
     * Open the corresponding Store Details page
     *
     * @param position to display the image in the current position
     */
    private void dealsImageOnclick(int position) {
        String retailerName = Constants.BANNER_RETAILERS[position];
        AnalyticsHelper.logCrashlytics(Constants.IMAGE_SLIDER_BANNER_VIEWED + retailerName);
        DisplayLocalNotification objDisplayLocalNotification = new DisplayLocalNotification();
        objDisplayLocalNotification.displayLocalNotification(getActivity(), retailerName, Home.class);
        Intent openStoreDetails = new Intent(getActivity(), StoreDetailsActivity.class);
        openStoreDetails.putExtra(Constants.STORE_URL, Constants.BANNER_IMAGE_URL[position]);
        startActivity(openStoreDetails);
    }

    /**
     * Method used in combination with Auto Image Slider
     *
     * @return Returns Array list of images
     */
    private ArrayList<ImageSliderImageModel> populateList() {
        ArrayList<ImageSliderImageModel> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ImageSliderImageModel imageSliderImageModel = new ImageSliderImageModel();
            imageSliderImageModel.setImage_drawable(Constants.BANNER_IMAGE_LIST[i]);
            list.add(imageSliderImageModel);
        }
        return list;
    }
}
