package example.badoo.com.transactionviewer.ui.adapter.viewholder;

import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import butterknife.Bind;

import example.badoo.com.transactionviewer.R;
import example.badoo.com.transactionviewer.model.data.ConvertedTransaction;

public class DetailListViewHolder extends BaseViewHolder<ConvertedTransaction> {

    public static DetailListViewHolder create(@NonNull final ViewGroup viewGroup) {
        return new DetailListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.detail_list_item_layout, viewGroup, false));
    }

    @Bind(R.id.list_item_title)
    TextView itemTitle;

    @Bind(R.id.list_item_subtitle)
    TextView itemSubtitle;

    private DetailListViewHolder(final View itemView) {
        super(itemView);
    }

    @Override
    public void bind(@NonNull final ConvertedTransaction transaction) {
        itemTitle.setText(transaction.currentValue);
        itemSubtitle.setText(transaction.convertedTransaction.convertedValue);
    }
}
