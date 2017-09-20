package cashkaro.com.cashkarodeals.view;

import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.utils.CashKaroDealsApplication;
import cashkaro.com.cashkarodeals.view.homefeature.CategoriesFragment;
import cashkaro.com.cashkarodeals.view.homefeature.ImageSliderFragment;


/**
 * Class for initializing the Slider View & the Tab Bar
 */
public class Home extends BaseActivity implements HomeView {

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CashKaroDealsApplication) getApplication()).getAppComponent().inject(this);
        homePresenter.setView(this);
        setupImageSlider();
        setupHomeFragment();
    }

    private void setupImageSlider() {
        ImageSliderFragment objImageSliderFragment = new ImageSliderFragment();
        loadFragmentContents(R.id.sliderImageFragment, objImageSliderFragment);
    }

    private void setupHomeFragment() {
        CategoriesFragment objCategoriesFragment = new CategoriesFragment();
        loadFragmentContents(R.id.mainPageFragmentContainer, objCategoriesFragment);
    }

    @Override
    public void onBackPressed() {
        homePresenter.handleBackButtonClick();
    }

    public void checkGrantedRuntimePermissions(String permissionType, int permissionRequestType) {
        super.checkGrantedRuntimePermissions(permissionType, permissionRequestType);
    }

    public void handleNavigationMenuActions(MenuItem item) {
        super.handleNavigationMenuActions(item);
    }

    public void handleNavigationDrawer() {
        super.handleNavigationDrawer();
    }
}
