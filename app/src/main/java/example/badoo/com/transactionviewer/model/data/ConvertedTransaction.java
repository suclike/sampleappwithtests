package example.badoo.com.transactionviewer.model.data;

import java.io.Serializable;

public class ConvertedTransaction implements Serializable {

    public String currentValue;
    public ConvertedPrice convertedTransaction;

    public ConvertedTransaction(final String currentValue, final ConvertedPrice convertedTransaction) {
        this.currentValue = currentValue;
        this.convertedTransaction = convertedTransaction;
    }
}
