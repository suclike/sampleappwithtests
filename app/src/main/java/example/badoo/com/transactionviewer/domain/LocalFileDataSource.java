package example.badoo.com.transactionviewer.domain;

import rx.Single;

public interface LocalFileDataSource {
    Single<String> getLocalJSON();
}
