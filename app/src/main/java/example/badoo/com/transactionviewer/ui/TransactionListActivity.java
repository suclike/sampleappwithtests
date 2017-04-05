package example.badoo.com.transactionviewer.ui;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.content.Intent;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;

import example.badoo.com.transactionviewer.R;
import example.badoo.com.transactionviewer.di.ActivityComponent;
import example.badoo.com.transactionviewer.model.data.RawTransaction;
import example.badoo.com.transactionviewer.model.data.TransactionList;
import example.badoo.com.transactionviewer.ui.adapter.TransactionListAdapter;
import example.badoo.com.transactionviewer.ui.model.presenter.TransactionListPresenter;
import example.badoo.com.transactionviewer.ui.model.view.TransactionListView;

import rx.subscriptions.CompositeSubscription;

public class TransactionListActivity extends BaseActivity implements TransactionListView {

    @Inject
    CompositeSubscription compositeSubscription;

    @Inject
    TransactionListPresenter transactionListPresenter;

    private TransactionListAdapter transactionListAdapter;
    private List<TransactionList> currentTransactionLists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        transactionListPresenter.attachView(this);

        if (savedInstanceState != null) {
            currentTransactionLists = (List<TransactionList>) savedInstanceState.getSerializable("detailList");
        } else {
            showProgress();
            compositeSubscription.add(transactionListPresenter.getTransactionListSubscription());
        }

        transactionListAdapter = createAdapter(currentTransactionLists);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        transactionListPresenter.detachView();

        compositeSubscription.unsubscribe();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("detailList", (Serializable) currentTransactionLists);
    }

    @Override
    protected void handleInjection(final ActivityComponent component) {
        component.inject(this);
    }

    @NonNull
    private TransactionListAdapter createAdapter(final List<TransactionList> transactionLists) {
        return TransactionListAdapter.create(transactionLists, transactionListPresenter);
    }

    @Override
    protected void initRecycleView(final RecyclerView recyclerView) {
        super.initRecycleView(recyclerView);

        recyclerView.setAdapter(transactionListAdapter);
    }

    @Override
    protected String getActionBarTitle() {
        return getString(R.string.transaction_activity_title);
    }

    @Override
    protected void setupActionBar(final Toolbar toolbar, final String actionBarTitle) {
        super.setupActionBar(toolbar, actionBarTitle);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_tree_icon));
    }

    @Override
    public void showEmptyLayout() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void notifyDataChanged(@NonNull final List<TransactionList> transactionLists) {
        currentTransactionLists.clear();
        currentTransactionLists.addAll(transactionLists);
        transactionListAdapter.swapContent(transactionLists);
    }

    @Override
    public void openTransactionActivityList(final String currentSku, @NonNull final List<RawTransaction> value) {
        Intent intent = new Intent(this, TransactionDetailActivity.class);
        intent.putExtra("detailList", (Serializable) value);
        intent.putExtra("currentSku", currentSku);
        startActivity(intent);
    }
}
