package example.badoo.com.transactionviewer.domain.modifier;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PriceValueTransformerTest {

    private PriceValueTransformer transformer;

    @Before
    public void setUp() {
        transformer = new PriceValueTransformer();
    }

    @Test
    public void transformer_Should_returnCorrectValue() {
        assertEquals(transformer.transform(6D, 6D), 36D);
    }
}
