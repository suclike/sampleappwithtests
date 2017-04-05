package example.badoo.com.transactionviewer.di.module;

import javax.inject.Singleton;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

import example.badoo.com.transactionviewer.di.qualifier.ActivityInstanceScope;

import rx.subscriptions.CompositeSubscription;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityInstanceScope
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
