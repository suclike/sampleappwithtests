package example.badoo.com.transactionviewer.ui.model.presenter;

interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
