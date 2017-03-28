package example.badoo.com.transactionviewer.ui.model.view;

import java.util.List;

import example.badoo.com.transactionviewer.model.data.RawTransaction;
import example.badoo.com.transactionviewer.model.data.TransactionList;

public interface TransactionListView extends TransactionBaseView {

    void notifyDataChanged(List<TransactionList> transactionLists);

    void openTransactionActivityList(String sku, List<RawTransaction> value);
}
