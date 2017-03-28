package example.badoo.com.transactionviewer.constant;

import java.util.HashMap;
import java.util.Map;

public final class Constants {
    public static final String PATH_TO_TRANSACTIONS_JSON = "transactions.json";
    public static final String PATH_TO_RATES_JSON = "rates.json";

    public static final Map<String, String> CURRENCY_MAP = new HashMap<String, String>() {

        {
            put("GBP", "£");
            put("USD", "$");
            put("EUR", "€");
            put("AUD", "A$");
            put("CAD", "C$");
        }
    };
}
