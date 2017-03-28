package example.badoo.com.transactionviewer.domain.modifier;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import example.badoo.com.transactionviewer.model.data.ConvertedPrice;
import example.badoo.com.transactionviewer.model.data.ConvertedTransaction;
import example.badoo.com.transactionviewer.model.data.RawRate;
import example.badoo.com.transactionviewer.model.data.RawTransaction;

public class TransactionItemConverterTest {

    private TransactionItemConverter converter;

    @Before
    public void setUp() {
        converter = new TransactionItemConverter(new PriceValueConverter(), new PriceValueTransformer());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void transformer_Should_throwIndexOutOfBoundsException_whenEmptyList() {
        RawTransaction rawTransaction = new RawTransaction("A1D", "1", "USD");
        List<RawRate> rawRates = new ArrayList<RawRate>();

        assertTrue(converter.transform(rawTransaction, rawRates).equals(
                new ConvertedTransaction("1", new ConvertedPrice("1", 1))));
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerException_whenNullTransaction() {
        assertTrue(converter.transform(null, new ArrayList<RawRate>()).equals(
                new ConvertedTransaction("1", new ConvertedPrice("1", 1.0))));
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerException_whenNullRate() {
        RawTransaction rawTransaction = new RawTransaction("A1D", "1", "USD");
        List<RawRate> rawRates = new ArrayList<RawRate>();
        rawRates.add(new RawRate(null, null, null));
        assertTrue(converter.transform(rawTransaction, rawRates).equals(
                new ConvertedTransaction("1", new ConvertedPrice("1", 1.0))));
    }

    @Test
    public void transformer_Should_returnCorrectValue() {
        RawTransaction rawTransaction = new RawTransaction("A1D", "1", "USD");
        List<RawRate> rawRates = new ArrayList<RawRate>();
        RawRate rawRate = new RawRate("USD", "55", "GBP");
        rawRates.add(rawRate);

        assertTrue(converter.transform(rawTransaction, rawRates).currentValue.equalsIgnoreCase("$ 1.0"));
        assertTrue(converter.transform(rawTransaction, rawRates).convertedTransaction.convertedValue.equalsIgnoreCase(
                "£ 55"));
        assertTrue(converter.transform(rawTransaction, rawRates).convertedTransaction.amount == 55.0);
    }

    @Test
    public void transformer_Should_returnCorrectBoolean_whenNeedToConvert() {
        assertTrue(converter.needToConvert(""));
    }

    @Test
    public void transformer_Should_returnCorrectBoolean_whenNoNeedToConvert() {
        assertFalse(converter.needToConvert("GBP"));
    }

    @Test
    public void transformer_Should_returnCorrectCurrencySymbol_whenExists() {
        assertTrue(TransactionItemConverter.getCurrencySymbol("USD").contains("$"));
    }
}
