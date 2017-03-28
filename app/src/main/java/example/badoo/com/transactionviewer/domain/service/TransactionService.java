package example.badoo.com.transactionviewer.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import example.badoo.com.transactionviewer.domain.TransactionFileParser;
import example.badoo.com.transactionviewer.model.data.RawData;
import example.badoo.com.transactionviewer.model.data.RawTransaction;

import rx.Observable;
import rx.Single;

import rx.functions.Func1;

public class TransactionService implements TransactionDataService {

    private TransactionFileParser transactionFileParcer;

    @Inject
    TransactionService(final TransactionFileParser transactionFileParcer) {
        this.transactionFileParcer = transactionFileParcer;
    }

    @Override
    public Single<HashMap<String, List<RawTransaction>>> getListFromJson() {
        return
            transactionFileParcer.getListFromJson() //
                                 .toObservable()    //
                                 .flatMap(new Func1<List<? extends RawData>, Observable<? extends RawData>>() {
                                         @Override
                                         public Observable<? extends RawData> call(
                                                 final List<? extends RawData> rawDatas) {
                                             return Observable.from(rawDatas);
                                         }
                                     })             //
                                 .filter(new Func1<RawData, Boolean>() {
                                         @Override
                                         public Boolean call(final RawData rawData) {
                                             final RawTransaction rawTransaction = ((RawTransaction) rawData);
                                             return rawTransaction.sku != null || !rawTransaction.sku.isEmpty();
                                         }
                                     })             //
                                 .toList()          //
                                 .toSingle()        //
                                 .flatMap(
                                     new Func1<List<? extends RawData>, Single<HashMap<String, List<RawTransaction>>>>() {
                                         @Override
                                         public Single<HashMap<String, List<RawTransaction>>> call(
                                                 final List<? extends RawData> rawDatas) {
                                             final HashMap<String, List<RawTransaction>> transactionMap =
                                                 new HashMap<>();

                                             for (RawData rawData : rawDatas) {
                                                 final List<RawTransaction> rawTransactions = new ArrayList<>();
                                                 final RawTransaction transaction = ((RawTransaction) rawData);
                                                 final String transactionSku = transaction.sku;

                                                 if (transactionMap.containsKey(transactionSku)) {
                                                     transactionMap.get(transactionSku).add(transaction);
                                                 } else {
                                                     rawTransactions.add(transaction);
                                                     transactionMap.put(transactionSku, rawTransactions);
                                                 }
                                             }

                                             return Single.just(transactionMap);
                                         }
                                     }); //
    }
}
