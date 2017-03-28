package example.badoo.com.transactionviewer.domain;

import java.lang.reflect.Type;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import example.badoo.com.transactionviewer.domain.modifier.LocalGSonParser;
import example.badoo.com.transactionviewer.model.data.RawData;
import example.badoo.com.transactionviewer.model.data.RawRate;

import rx.Single;

import rx.functions.Func1;

public class RateFileParser implements LocalGSonParser {

    private Gson gson;

    @Named("transactions")
    private LocalFileDataSource rateDataSource;

    @Inject
    RateFileParser(final Gson gson,
            @Named("rates") final LocalFileDataSource rateDataSource) {
        this.gson = gson;
        this.rateDataSource = rateDataSource;
    }

    @Override
    public Single<List<? extends RawData>> getListFromJson() {
        return rateDataSource.getLocalJSON() //
                             .map(new Func1<String, List<? extends RawData>>() {
                                     @Override
                                     public List<RawRate> call(final String s) {
                                         final Type typeOfListOfIdea = new TypeToken<List<RawRate>>() { }.getType();

                                         return gson.fromJson(s, typeOfListOfIdea);
                                     }
                                 }); //
    }
}
