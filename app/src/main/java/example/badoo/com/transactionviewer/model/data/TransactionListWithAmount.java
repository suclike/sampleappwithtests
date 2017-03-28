package example.badoo.com.transactionviewer.model.data;

import java.io.Serializable;

import java.util.List;

public class TransactionListWithAmount implements Serializable {

    public List<ConvertedTransaction> transactions;
    public String totalAmount;

    public TransactionListWithAmount(final List<ConvertedTransaction> transactions, final String totalAmount) {
        this.transactions = transactions;
        this.totalAmount = totalAmount;
    }
}
