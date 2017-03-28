package example.badoo.com.transactionviewer.di;

import javax.inject.Singleton;

import dagger.Component;

import example.badoo.com.transactionviewer.BaseApplication;
import example.badoo.com.transactionviewer.di.module.ActivityModule;
import example.badoo.com.transactionviewer.di.module.ApplicationModule;
import example.badoo.com.transactionviewer.di.module.ConstantsModule;
import example.badoo.com.transactionviewer.di.module.DataSourceModule;
import example.badoo.com.transactionviewer.di.qualifier.AppScope;

@Singleton
@AppScope
@Component(
    modules = {
        ApplicationModule.class, //
        DataSourceModule.class,  //
        ConstantsModule.class
    }
)
public interface ApplicationComponent {

    ActivityComponent plus(ActivityModule activityModule);

    void injectApplication(BaseApplication application);
}
