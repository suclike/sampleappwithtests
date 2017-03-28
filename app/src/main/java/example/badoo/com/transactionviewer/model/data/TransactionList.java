package example.badoo.com.transactionviewer.model.data;

import java.io.Serializable;

public class TransactionList implements Serializable {

    public String sku;
    public String rawTransactions;

    public TransactionList(final String sku, final String rawTransactions) {
        this.sku = sku;
        this.rawTransactions = rawTransactions;
    }
}
