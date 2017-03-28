package example.badoo.com.transactionviewer.ui.model.presenter;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import example.badoo.com.transactionviewer.domain.modifier.TransactionItemConverter;
import example.badoo.com.transactionviewer.domain.service.RateService;
import example.badoo.com.transactionviewer.model.data.ConvertedTransaction;
import example.badoo.com.transactionviewer.model.data.RawRate;
import example.badoo.com.transactionviewer.model.data.RawTransaction;
import example.badoo.com.transactionviewer.model.data.TransactionListWithAmount;
import example.badoo.com.transactionviewer.ui.model.view.TransactionDetailView;

import rx.Subscriber;
import rx.Subscription;

import rx.android.schedulers.AndroidSchedulers;

import rx.functions.Func1;

import rx.schedulers.Schedulers;

public class TransactionDetailPresenter extends BasePresenter<TransactionDetailView> {

    private RateService rateService;
    private TransactionItemConverter transactionItemConverter;

    @Inject
    TransactionDetailPresenter(final RateService rateService, final TransactionItemConverter transactionItemConverter) {
        this.rateService = rateService;
        this.transactionItemConverter = transactionItemConverter;
    }

    public Subscription getTransactionDetailSubscription(final List<RawTransaction> rawValue) {
        return
            rateService.getRateList()  //
                       .toObservable() //
                       .map(new Func1<List<RawRate>, List<ConvertedTransaction>>() {
                               @Override
                               public List<ConvertedTransaction> call(final List<RawRate> rawRates) {
                                   List<ConvertedTransaction> transaction = new ArrayList<>();
                                   for (RawTransaction rawTransaction : rawValue) {
                                       transaction.add(transactionItemConverter.transform(rawTransaction, rawRates));
                                   }

                                   return transaction;
                               }
                           }) //
                       .map(new Func1<List<ConvertedTransaction>, TransactionListWithAmount>() {
                               @Override
                               public TransactionListWithAmount call(final List<ConvertedTransaction> transactions) {
                                   double totalTransactionsValue = 0;
                                   for (ConvertedTransaction transaction : transactions) {
                                       totalTransactionsValue = totalTransactionsValue
                                               + transaction.convertedTransaction.amount;
                                   }

                                   DecimalFormat df = new DecimalFormat("#.##");

                                   return new TransactionListWithAmount(transactions,
                                           df.format(totalTransactionsValue));
                               }
                           })                                     //
                       .observeOn(AndroidSchedulers.mainThread()) //
                       .subscribeOn(Schedulers.io())              //
                       .subscribe(new Subscriber<TransactionListWithAmount>() {
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
                               public void onNext(final TransactionListWithAmount transactionAmount) {
                                   getAttachedView().notifyDataChanged(transactionAmount);
                               }
                           });
    }
}
