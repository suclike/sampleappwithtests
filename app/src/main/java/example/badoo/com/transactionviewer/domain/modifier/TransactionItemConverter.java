package example.badoo.com.transactionviewer.domain.modifier;

import static example.badoo.com.transactionviewer.constant.Constants.CURRENCY_MAP;

import java.text.DecimalFormat;

import java.util.List;

import javax.inject.Inject;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import example.badoo.com.transactionviewer.model.data.ConvertedPrice;
import example.badoo.com.transactionviewer.model.data.ConvertedTransaction;
import example.badoo.com.transactionviewer.model.data.RawRate;
import example.badoo.com.transactionviewer.model.data.RawTransaction;

public class TransactionItemConverter implements DataTransformer<RawTransaction, List<RawRate>, ConvertedTransaction> {

    private PriceValueConverter priceValueConverter;
    private PriceValueTransformer priceValueTransformer;

    private static DecimalFormat df = new DecimalFormat("#.##");

    @Inject
    public TransactionItemConverter(final PriceValueConverter priceValueConverter,
            final PriceValueTransformer priceValueTransformer) {
        this.priceValueConverter = priceValueConverter;
        this.priceValueTransformer = priceValueTransformer;
    }

    @NonNull
    @Override
    public ConvertedTransaction transform(@NonNull final RawTransaction rawTransaction,
            @NonNull final List<RawRate> rawRateTransactions) {

        final float amount = convertFromStingValue(rawTransaction.amount);
        final String currency = rawTransaction.currency;
        final String originalPriceValue = getCurrencySymbol(currency) + " " + String.valueOf(amount);

        return new ConvertedTransaction(originalPriceValue, convertedPriceValue(currency, amount, rawRateTransactions));
    }

    private ConvertedPrice convertedPriceValue(String originalCurrency, double amount,
            final List<RawRate> rawRateTransactions) {

        if (!needToConvert(originalCurrency)) {
            return new ConvertedPrice(getCurrencySymbol(originalCurrency) + " " + df.format(amount), amount);
        }

        final RawRate rawRate = getConversionRate(rawRateTransactions, originalCurrency);
        final float rateFromString = convertFromStingValue(rawRate.rate);
        originalCurrency = rawRate.to;
        amount = transformPriceValue(amount, rateFromString);

        return convertedPriceValue(originalCurrency, amount, rawRateTransactions);
    }

    @VisibleForTesting
    boolean needToConvert(final String currency) {
        return !currency.equalsIgnoreCase("GBP");
    }

    @VisibleForTesting
    static RawRate getConversionRate(final List<RawRate> rawRateTransactions, final String currency) {

        // todo doesn't cover all the cases, rework!
        for (int i = 0; i < rawRateTransactions.size(); i++) {
            if (rawRateTransactions.get(i).from.equalsIgnoreCase(currency)) {
                return rawRateTransactions.get(i);
            }
        }

        return rawRateTransactions.get(0);
    }

    @VisibleForTesting
    static String getCurrencySymbol(final String currency) {
        if (!CURRENCY_MAP.containsKey(currency)) {
            CURRENCY_MAP.get("GBP");
        }

        return CURRENCY_MAP.get(currency);
    }

    private float convertFromStingValue(final String from) {
        return priceValueConverter.convert(from);
    }

    private double transformPriceValue(final double from, final double with) {
        return priceValueTransformer.transform(from, with);
    }
}
