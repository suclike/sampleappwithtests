package example.badoo.com.transactionviewer.ui.model.presenter;

class BasePresenter<V> implements Presenter<V> {

    protected V view;

    @Override
    public void attachView(final V view) {
        this.view = view;
        checkViewAttached();
    }

    @Override
    public void detachView() {
        view = null;
    }

    private void checkViewAttached() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

    V getAttachedView() {
        return view;
    }

    private boolean isViewAttached() {
        return view != null;
    }

    private static class ViewNotAttachedException extends RuntimeException {
        private ViewNotAttachedException() {
            super("Please call Presenter.attachView(view) before" + " requesting data from the Presenter");
        }
    }
}
