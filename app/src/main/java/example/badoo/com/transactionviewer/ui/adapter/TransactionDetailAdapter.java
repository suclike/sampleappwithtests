package example.badoo.com.transactionviewer.ui.adapter;

import static example.badoo.com.transactionviewer.ui.adapter.ListViewTypes.VIEW_HOLDER_HEADER;
import static example.badoo.com.transactionviewer.ui.adapter.ListViewTypes.VIEW_HOLDER_ITEM;

import java.util.List;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.ViewGroup;

import example.badoo.com.transactionviewer.model.data.ConvertedTransaction;
import example.badoo.com.transactionviewer.model.data.TransactionListWithAmount;
import example.badoo.com.transactionviewer.ui.adapter.viewholder.BaseViewHolder;
import example.badoo.com.transactionviewer.ui.adapter.viewholder.DetailListHeaderViewHolder;
import example.badoo.com.transactionviewer.ui.adapter.viewholder.DetailListViewHolder;

public class TransactionDetailAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<ConvertedTransaction> currentTransactionLists;
    private String totalAmount = null;

    public static TransactionDetailAdapter create(final List<ConvertedTransaction> transactionLists) {
        return new TransactionDetailAdapter(transactionLists);
    }

    private TransactionDetailAdapter(@NonNull final List<ConvertedTransaction> transactionLists) {
        this.currentTransactionLists = transactionLists;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        switch (viewType) {

            case VIEW_HOLDER_HEADER :
                return DetailListHeaderViewHolder.create(parent);

            default :
            case VIEW_HOLDER_ITEM :
                return DetailListViewHolder.create(parent);
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        if (position == VIEW_HOLDER_HEADER) {
            ((DetailListHeaderViewHolder) holder).bind(getListTitle());
        } else {
            ((DetailListViewHolder) holder).bind(currentTransactionLists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return currentTransactionLists.isEmpty() ? 0 : currentTransactionLists.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return position == 0 ? VIEW_HOLDER_HEADER : VIEW_HOLDER_ITEM;
    }

    public void swapContent(final TransactionListWithAmount transactionAmount) {
        currentTransactionLists.clear();
        currentTransactionLists.addAll(transactionAmount.transactions);
        totalAmount = transactionAmount.totalAmount;
        notifyDataSetChanged();
    }

    private String getListTitle() {
        return totalAmount != null && !totalAmount.isEmpty() ? totalAmount : "No total amount calculated";
    }
}
