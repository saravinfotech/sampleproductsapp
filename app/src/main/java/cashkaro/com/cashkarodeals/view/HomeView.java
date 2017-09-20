package cashkaro.com.cashkarodeals.view;

import android.view.MenuItem;

/**
 * Interface file for the Home Screen.
 */

interface HomeView {
    void handleNavigationDrawer();

    void handleNavigationMenuActions(MenuItem item);

    void checkGrantedRuntimePermissions(String permissionType, int permissionRequestType);
}
