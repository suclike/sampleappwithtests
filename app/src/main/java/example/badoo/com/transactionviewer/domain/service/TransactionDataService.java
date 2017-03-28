package example.badoo.com.transactionviewer.domain.service;

import java.util.HashMap;
import java.util.List;

import example.badoo.com.transactionviewer.model.data.RawTransaction;

import rx.Single;

interface TransactionDataService {
    Single<HashMap<String, List<RawTransaction>>> getListFromJson();
}
