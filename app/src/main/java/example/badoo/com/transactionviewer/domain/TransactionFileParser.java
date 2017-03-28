package example.badoo.com.transactionviewer.domain;

import java.lang.reflect.Type;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import example.badoo.com.transactionviewer.domain.modifier.LocalGSonParser;
import example.badoo.com.transactionviewer.model.data.RawData;
import example.badoo.com.transactionviewer.model.data.RawTransaction;

import rx.Single;

import rx.functions.Func1;

public class TransactionFileParser implements LocalGSonParser {

    private Gson gson;

    @Named("transactions")
    private LocalFileDataSource transactionDataSource;

    @Inject
    TransactionFileParser(final Gson gson,
            @Named("transactions") final LocalFileDataSource transactionDataSource) {
        this.gson = gson;
        this.transactionDataSource = transactionDataSource;
    }

    @Override
    public Single<List<? extends RawData>> getListFromJson() {
        return transactionDataSource.getLocalJSON() //
                                    .map(new Func1<String, List<? extends RawData>>() {
                                            @Override
                                            public List<RawTransaction> call(final String s) {
                                                final Type typeOfListOfIdea =
                                                    new TypeToken<List<RawTransaction>>() { }.getType();

                                                return gson.fromJson(s, typeOfListOfIdea);
                                            }
                                        }); //
    }
}
