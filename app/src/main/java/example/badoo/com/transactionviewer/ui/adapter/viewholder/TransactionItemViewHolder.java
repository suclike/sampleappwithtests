package example.badoo.com.transactionviewer.ui.adapter.viewholder;

import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import butterknife.Bind;

import example.badoo.com.transactionviewer.R;
import example.badoo.com.transactionviewer.model.data.TransactionList;
import example.badoo.com.transactionviewer.ui.listener.OnTransactionClickListener;

public class TransactionItemViewHolder extends BaseViewHolder<TransactionList> {

    public static TransactionItemViewHolder create(@NonNull final ViewGroup viewGroup,
            final OnTransactionClickListener onTransactionClickListener) {
        return new TransactionItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.list_item_layout, viewGroup, false), onTransactionClickListener);
    }

    @Bind(R.id.list_item_title)
    TextView itemTitle;

    @Bind(R.id.list_item_subtitle)
    TextView itemSubtitle;

    private TransactionItemViewHolder(final View itemView,
            final OnTransactionClickListener onTransactionClickListener) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onTransactionClickListener.onTransactionClicked(getAdapterPosition());
                }
            });
    }

    @Override
    public void bind(@NonNull final TransactionList transaction) {
        itemTitle.setText(transaction.sku);

        final String transactionAmount = transaction.rawTransactions + " transactions";
        itemSubtitle.setText(transactionAmount);
    }
}
