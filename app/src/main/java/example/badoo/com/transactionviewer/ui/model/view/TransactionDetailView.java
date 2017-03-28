package example.badoo.com.transactionviewer.ui.model.view;

import example.badoo.com.transactionviewer.model.data.TransactionListWithAmount;

public interface TransactionDetailView extends TransactionBaseView {

    void notifyDataChanged(TransactionListWithAmount transactionAmount);
}
