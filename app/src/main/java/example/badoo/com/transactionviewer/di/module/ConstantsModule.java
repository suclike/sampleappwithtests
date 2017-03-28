package example.badoo.com.transactionviewer.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import example.badoo.com.transactionviewer.constant.Constants;

@Module
public class ConstantsModule {

    @Provides
    @Singleton
    @Named("transactionJSON")
    String provideNetworkTimeoutInSeconds() {
        return Constants.PATH_TO_TRANSACTIONS_JSON;
    }

    @Provides
    @Singleton
    @Named("rateJSON")
    String provideCacheSize() {
        return Constants.PATH_TO_RATES_JSON;
    }
}
