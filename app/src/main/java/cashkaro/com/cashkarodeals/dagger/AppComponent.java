package cashkaro.com.cashkarodeals.dagger;

import javax.inject.Singleton;

import cashkaro.com.cashkarodeals.view.Home;
import cashkaro.com.cashkarodeals.view.HomePresenterImpl;
import dagger.Component;

/**
 * AppComponent is a singleton component interface for the app.
 * The @Component annotation takes a list of modules as an input
 */

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(Home target);

    void inject(HomePresenterImpl target);

}
