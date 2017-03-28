package example.badoo.com.transactionviewer.ui.model.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import android.support.annotation.NonNull;

import example.badoo.com.transactionviewer.domain.service.TransactionService;
import example.badoo.com.transactionviewer.model.data.RawTransaction;
import example.badoo.com.transactionviewer.model.data.TransactionList;
import example.badoo.com.transactionviewer.ui.listener.OnTransactionClickListener;
import example.badoo.com.transactionviewer.ui.model.view.TransactionListView;

import rx.Single;
import rx.Subscriber;
import rx.Subscription;

import rx.android.schedulers.AndroidSchedulers;

import rx.functions.Func1;

import rx.schedulers.Schedulers;

public class TransactionListPresenter extends BasePresenter<TransactionListView> implements OnTransactionClickListener {

    private List<TransactionList> transactionLists = new ArrayList<>();
    private HashMap<String, List<RawTransaction>> transactionListHashMap = new HashMap<>();
    private TransactionService transactionService;

    @Inject
    TransactionListPresenter(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Subscription getTransactionListSubscription() {
        return transactionService.getListFromJson()
                                 .flatMap(
                                     new Func1<HashMap<String, List<RawTransaction>>, Single<List<TransactionList>>>() {
                                         @Override
                                         public Single<List<TransactionList>> call(
                                                 final HashMap<String, List<RawTransaction>> stringListHashMap) {

                                             transactionListHashMap.putAll(stringListHashMap);

                                             return Single.just(getTransactionLists(stringListHashMap));
                                         }
                                     })                                           //
                                 .observeOn(AndroidSchedulers.mainThread())       //
                                 .subscribeOn(Schedulers.io())                    //
                                 .subscribe(new Subscriber<List<TransactionList>>() {
                                         @Override
                                         public void onCompleted() {
                                             getAttachedView().hideProgress();
                                         }

                                         @Override
                                         public void onError(final Throwable e) {
                                             getAttachedView().hideProgress();
                                             getAttachedView().showEmptyLayout();
                                         }

                                         @Override
                                         public void onNext(final List<TransactionList> transactionLists) {
                                             getAttachedView().notifyDataChanged(transactionLists);
                                         }
                                     });
    }

    @NonNull
    private List<TransactionList> getTransactionLists(final HashMap<String, List<RawTransaction>> stringListHashMap) {
        transactionLists = new ArrayList<>(stringListHashMap.size());
        for (Map.Entry<String, List<RawTransaction>> stringListEntry : stringListHashMap.entrySet()) {
            transactionLists.add(new TransactionList(stringListEntry.getKey(),
                    String.valueOf(stringListEntry.getValue().size())));
        }

        return transactionLists;
    }

    @Override
    public void onTransactionClicked(final int adapterPosition) {
        final String selectedSku = transactionLists.get(adapterPosition).sku;

        for (Map.Entry<String, List<RawTransaction>> stringListEntry : transactionListHashMap.entrySet()) {
            if (stringListEntry.getKey().contains(selectedSku)) {
                getAttachedView().openTransactionActivityList(selectedSku, stringListEntry.getValue());
                break;
            }
        }
    }
}
