package example.badoo.com.transactionviewer;

import javax.inject.Inject;

import android.app.Application;

import example.badoo.com.transactionviewer.di.ApplicationComponent;
import example.badoo.com.transactionviewer.di.DaggerApplicationComponent;
import example.badoo.com.transactionviewer.di.module.ApplicationModule;
import example.badoo.com.transactionviewer.di.module.ConstantsModule;
import example.badoo.com.transactionviewer.di.module.DataSourceModule;

import rx.subscriptions.CompositeSubscription;

public class BaseApplication extends Application {

    @Inject
    CompositeSubscription compositeSubscription;

    // Dagger injection
    public ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /** Creating component itself */
        applicationComponent = createComponent();

        /** Injecting application itself */
        applicationComponent.injectApplication(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        compositeSubscription.unsubscribe();
    }

    /**
     * Building DaggerApplicationComponent generated within the Dagger2 lib.
     */
    private ApplicationComponent createComponent() {
        return
            DaggerApplicationComponent.builder()                                //
                                      .applicationModule(new ApplicationModule(this))
                                      .dataSourceModule(new DataSourceModule()) //
                                      .constantsModule(new ConstantsModule())   //
                                      .build();
    }
}
