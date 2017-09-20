package cashkaro.com.cashkarodeals.dagger;

import android.content.Context;

import javax.inject.Singleton;

import cashkaro.com.cashkarodeals.view.HomePresenter;
import cashkaro.com.cashkarodeals.view.HomePresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module file for providing the presenter objects at runtime.
 */

@Module
class PresenterModule {

    @Provides
    @Singleton
    HomePresenter provideHomePresenter(Context context) {
        return new HomePresenterImpl(context);
    }

}
