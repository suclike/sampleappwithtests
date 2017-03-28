package example.badoo.com.transactionviewer.domain.modifier;

import java.util.List;

import example.badoo.com.transactionviewer.model.data.RawData;

import rx.Single;

public interface LocalGSonParser {
    Single<List<? extends RawData>> getListFromJson();
}
