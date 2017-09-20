package cashkaro.com.cashkarodeals.view;

import android.content.Context;
import android.view.MenuItem;

import cashkaro.com.cashkarodeals.utils.CashKaroDealsApplication;

/**
 * Home Presenter Implementation file with all the UI logic for the Home Screen
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;

    public HomePresenterImpl(Context context) {
        ((CashKaroDealsApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void handleBackButtonClick() {
        homeView.handleNavigationDrawer();
    }

    @Override
    public void navigationMenuActions(MenuItem item) {
        homeView.handleNavigationMenuActions(item);
    }

    @Override
    public void handleRuntimePermission(String permissionType, int permissionRequestType) {
        homeView.checkGrantedRuntimePermissions(permissionType, permissionRequestType);
    }
}
