package example.badoo.com.transactionviewer.ui.adapter;

import static example.badoo.com.transactionviewer.ui.adapter.ListViewTypes.VIEW_HOLDER_ITEM;

import java.util.List;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.ViewGroup;

import example.badoo.com.transactionviewer.model.data.TransactionList;
import example.badoo.com.transactionviewer.ui.adapter.viewholder.BaseViewHolder;
import example.badoo.com.transactionviewer.ui.adapter.viewholder.TransactionItemViewHolder;
import example.badoo.com.transactionviewer.ui.listener.OnTransactionClickListener;

public class TransactionListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<TransactionList> currentTransactionLists;
    private OnTransactionClickListener onTransactionClickListener;

    public static TransactionListAdapter create(final List<TransactionList> transactionLists,
            final OnTransactionClickListener onTransactionClickListener) {
        return new TransactionListAdapter(transactionLists, onTransactionClickListener);
    }

    private TransactionListAdapter(@NonNull final List<TransactionList> transactionLists,
            final OnTransactionClickListener onTransactionClickListener) {
        this.currentTransactionLists = transactionLists;
        this.onTransactionClickListener = onTransactionClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return TransactionItemViewHolder.create(parent, onTransactionClickListener);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        ((TransactionItemViewHolder) holder).bind(currentTransactionLists.get(position));
    }

    @Override
    public int getItemCount() {
        return currentTransactionLists.isEmpty() ? 0 : currentTransactionLists.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return VIEW_HOLDER_ITEM;
    }

    public void swapContent(final List<TransactionList> transactionLists) {
        currentTransactionLists.clear();
        currentTransactionLists.addAll(transactionLists);
        notifyDataSetChanged();
    }
}
