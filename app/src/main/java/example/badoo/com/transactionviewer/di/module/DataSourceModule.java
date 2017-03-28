package example.badoo.com.transactionviewer.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

import example.badoo.com.transactionviewer.di.qualifier.AppScope;
import example.badoo.com.transactionviewer.domain.LocalFileDataSource;
import example.badoo.com.transactionviewer.domain.TransactionDataSource;

@Module
public class DataSourceModule {

    @Provides
    @Singleton
    @Named("transactions")
    LocalFileDataSource provideTransitionsLocalFileDataSource(@AppScope final Application context,
            @Named("transactionJSON") final String assertFileName) {
        return new TransactionDataSource(context, assertFileName);
    }

    @Provides
    @Singleton
    @Named("rates")
    LocalFileDataSource provideRatesLocalFileDataSource(@AppScope final Application context,
            @Named("rateJSON") final String assertFileName) {
        return new TransactionDataSource(context, assertFileName);
    }
}
