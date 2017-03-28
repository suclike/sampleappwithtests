package example.badoo.com.transactionviewer.domain.modifier;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PriceValueConverterTest {

    private PriceValueConverter converter;

    @Before
    public void setUp() {
        converter = new PriceValueConverter();
    }

    @Test(expected = NumberFormatException.class)
    public void converter_Should_throwNumberFormatException_when_nonFloatValue() {
        converter.convert("list");
    }

    @Test(expected = NumberFormatException.class)
    public void converter_Should_throwNumberFormatException_when_emptyValue() {
        converter.convert("");
    }

    @Test
    public void converter_Should_convert_when_correctValue() {
        assertEquals(converter.convert("55.66"), 55.66, 2F);
    }
}
