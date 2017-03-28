package example.badoo.com.transactionviewer.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.os.Bundle;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;

import example.badoo.com.transactionviewer.R;
import example.badoo.com.transactionviewer.di.ActivityComponent;
import example.badoo.com.transactionviewer.model.data.ConvertedTransaction;
import example.badoo.com.transactionviewer.model.data.RawTransaction;
import example.badoo.com.transactionviewer.model.data.TransactionListWithAmount;
import example.badoo.com.transactionviewer.ui.adapter.TransactionDetailAdapter;
import example.badoo.com.transactionviewer.ui.model.presenter.TransactionDetailPresenter;
import example.badoo.com.transactionviewer.ui.model.view.TransactionDetailView;

public class TransactionDetailActivity extends BaseActivity implements TransactionDetailView {

    @Inject
    TransactionDetailPresenter transactionDetailPresenter;

    private TransactionDetailAdapter transactionDetailAdapter;
    private List<RawTransaction> rawTransactions = new ArrayList<>();
    private String currentSku;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        transactionDetailPresenter.attachView(this);

        if (getIntent().getExtras() != null) {
            rawTransactions = (List<RawTransaction>) getIntent().getExtras().getSerializable("detailList");
            currentSku = getIntent().getExtras().getString("currentSku");
        }

        if (!rawTransactions.isEmpty()) {
            showProgress();
            compositeSubscription.add(transactionDetailPresenter.getTransactionDetailSubscription(rawTransactions));
        }

        transactionDetailAdapter = createAdapter();
    }

    @Override
    protected String getActionBarTitle() {
        return (currentSku == null || !currentSku.isEmpty())
            ? getString(R.string.transaction_details_activity_title) + " " + currentSku
            : getString(R.string.transaction_details_activity_empty_title);
    }

    @Override
    protected void setupActionBar(final Toolbar toolbar, final String actionBarTitle) {
        super.setupActionBar(toolbar, actionBarTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_ab_back_material_white);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        onBackPressed();
                    }
                });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        transactionDetailPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void handleInjection(final ActivityComponent component) {
        component.inject(this);
    }

    @NonNull
    private TransactionDetailAdapter createAdapter() {
        return TransactionDetailAdapter.create(new ArrayList<ConvertedTransaction>());
    }

    @Override
    protected void initRecycleView(final RecyclerView recyclerView) {
        super.initRecycleView(recyclerView);

        recyclerView.swapAdapter(transactionDetailAdapter, true);
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
    public void notifyDataChanged(final TransactionListWithAmount transactionAmount) {
        transactionDetailAdapter.swapContent(transactionAmount);
    }
}
