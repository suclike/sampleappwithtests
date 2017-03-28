package example.badoo.com.transactionviewer.domain.service;

import java.util.List;

import javax.inject.Inject;

import example.badoo.com.transactionviewer.domain.RateFileParser;
import example.badoo.com.transactionviewer.model.data.RawData;
import example.badoo.com.transactionviewer.model.data.RawRate;

import rx.Observable;
import rx.Single;

import rx.functions.Func1;

public class RateService implements RateDataService {

    private RateFileParser rateFileParcer;

    @Inject
    RateService(final RateFileParser rateFileParcer) {
        this.rateFileParcer = rateFileParcer;
    }

    @Override
    public Single<List<RawRate>> getRateList() {
        return
            rateFileParcer.getListFromJson() //
                          .toObservable()    //
                          .flatMap(new Func1<List<? extends RawData>, Observable<? extends RawData>>() {
                                  @Override
                                  public Observable<? extends RawData> call(final List<? extends RawData> rawDatas) {
                                      return Observable.from(rawDatas);
                                  }
                              })             //
                          .filter(new Func1<RawData, Boolean>() {
                                  @Override
                                  public Boolean call(final RawData rawData) {
                                      final RawRate rawRate = ((RawRate) rawData);
                                      return (rawRate.from != null && !rawRate.from.isEmpty())
                                              && (rawRate.rate != null && !rawRate.rate.isEmpty())
                                              && (rawRate.to != null && !rawRate.to.isEmpty());
                                  }
                              })             //
                          .map(new Func1<RawData, RawRate>() {
                                  @Override
                                  public RawRate call(final RawData rawData) {
                                      return ((RawRate) rawData);
                                  }
                              })             //
                          .toList()          //
                          .toSingle();
    }
}
