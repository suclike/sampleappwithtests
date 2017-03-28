package example.badoo.com.transactionviewer.domain.service;

import java.util.List;

import example.badoo.com.transactionviewer.model.data.RawRate;

import rx.Single;

public interface RateDataService {
    Single<List<RawRate>> getRateList();
}
