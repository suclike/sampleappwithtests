package example.badoo.com.transactionviewer.domain;

import static org.mockito.Mockito.when;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import rx.Single;

@RunWith(MockitoJUnitRunner.class)
public class TransactionFileParserTest {

    private TransactionFileParser parser;

    private String mockedJson = "[\n" + "  {\n" + "    \"amount\": \"25.2\",\n" + "    \"sku\": \"J4064\",\n"
            + "    \"currency\": \"CAD\"\n" + "  },\n" + "  {\n" + "    \"amount\": \"19.5\",\n"
            + "    \"sku\": \"R9704\",\n" + "    \"currency\": \"USD\"\n" + "  },\n" + "  {\n"
            + "    \"amount\": \"25.4\",\n" + "    \"sku\": \"C7156\",\n" + "    \"currency\": \"GBP\"\n" + "  },\n"
            + "  {\n" + "    \"amount\": \"30.1\",\n" + "    \"sku\": \"X1893\",\n" + "    \"currency\": \"GBP\"\n"
            + "  },\n" + "  {\n" + "    \"amount\": \"33.0\",\n" + "    \"sku\": \"R9704\",\n"
            + "    \"currency\": \"USD\"\n" + "  }\n" + "]";

    @Mock
    LocalFileDataSource transactionDataSource;

    @Before
    public void setUp() {
        parser = new TransactionFileParser(new Gson(), transactionDataSource);
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwIndexOutOfBoundsException_whenEmptyList() {
        parser.getListFromJson();
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerExceptionException_whenNoAccessToFile() {
        parser.getListFromJson();
    }

    @Test
    public void transformer_Should_throwIndexOutOfBoundsException_whenCorrectJSON() {
        when(transactionDataSource.getLocalJSON()).thenReturn(Single.just(mockedJson));
        assertTrue(!parser.getListFromJson().toBlocking().value().isEmpty());
    }
}
