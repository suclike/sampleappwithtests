package example.badoo.com.transactionviewer.ui.adapter.viewholder;

import static example.badoo.com.transactionviewer.constant.Constants.CURRENCY_MAP;

import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import butterknife.Bind;

import example.badoo.com.transactionviewer.R;

public class DetailListHeaderViewHolder extends BaseViewHolder<String> {

    public static DetailListHeaderViewHolder create(@NonNull final ViewGroup viewGroup) {
        return new DetailListHeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.list_header_layout, viewGroup, false));
    }

    @Bind(R.id.list_header)
    TextView listHeader;

    private DetailListHeaderViewHolder(final View itemView) {
        super(itemView);
    }

    @Override
    public void bind(@NonNull final String text) {
        final String headerMessage = "Total: " + CURRENCY_MAP.get("GBP") + " " + text;
        listHeader.setText(headerMessage);
    }
}
