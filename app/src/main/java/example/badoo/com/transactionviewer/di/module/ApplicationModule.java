package example.badoo.com.transactionviewer.di.module;

import javax.inject.Singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

import example.badoo.com.transactionviewer.di.qualifier.AppScope;

import rx.subscriptions.CompositeSubscription;

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(final Context context) {

        // keeps you from leaking
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @AppScope
    @Singleton
    Application provideApplication() {
        return ((Application) context.getApplicationContext());
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
