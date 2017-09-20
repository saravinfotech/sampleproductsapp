package cashkaro.com.cashkarodeals.view;

import android.view.MenuItem;

/**
 * Presenter file for the Home screen to handle all the UI actions
 */

public interface HomePresenter {
    void setView(HomeView view);

    void handleBackButtonClick();

    void navigationMenuActions(MenuItem item);

    void handleRuntimePermission(String permissionType, int permissionRequestType);
}
