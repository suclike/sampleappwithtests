package example.badoo.com.transactionviewer.model.data;

import java.io.Serializable;

public class RawRate extends RawData implements Serializable {

    public String from;
    public String rate;
    public String to;

    public RawRate(final String from, final String rate, final String to) {
        this.from = from;
        this.rate = rate;
        this.to = to;
    }
}
