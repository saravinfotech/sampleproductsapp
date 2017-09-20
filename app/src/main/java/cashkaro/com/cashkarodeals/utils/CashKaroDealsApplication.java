package cashkaro.com.cashkarodeals.utils;

import android.app.Application;

import cashkaro.com.cashkarodeals.dagger.AppComponent;
import cashkaro.com.cashkarodeals.dagger.AppModule;
import cashkaro.com.cashkarodeals.dagger.DaggerAppComponent;

/**
 * Cash Karo Application class for this project
 */

public class CashKaroDealsApplication extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
        AnalyticsHelper objAnalyticsHelper = new AnalyticsHelper();
        objAnalyticsHelper.initCrashReporting(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent initDagger(CashKaroDealsApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
