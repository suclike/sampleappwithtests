package example.badoo.com.transactionviewer.model.data;

import java.io.Serializable;

public class RawTransaction extends RawData implements Serializable {

    public String amount;
    public String sku;
    public String currency;

    public RawTransaction(final String sku, final String price, final String currency) {
        this.sku = sku;
        this.amount = price;
        this.currency = currency;
    }
}
