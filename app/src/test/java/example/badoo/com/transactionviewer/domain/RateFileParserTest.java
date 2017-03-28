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
public class RateFileParserTest {

    private RateFileParser parser;

    private String mockedRate = "[\n" + "  {\n" + "    \"from\": \"USD\",\n" + "    \"rate\": \"0.77\",\n"
            + "    \"to\": \"GBP\"\n" + "  },\n" + "  {\n" + "    \"from\": \"GBP\",\n" + "    \"rate\": \"1.3\",\n"
            + "    \"to\": \"USD\"\n" + "  },\n" + "  {\n" + "    \"from\": \"USD\",\n" + "    \"rate\": \"1.09\",\n"
            + "    \"to\": \"CAD\"\n" + "  },\n" + "  {\n" + "    \"from\": \"CAD\",\n" + "    \"rate\": \"0.92\",\n"
            + "    \"to\": \"USD\"\n" + "  },\n" + "  {\n" + "    \"from\": \"GBP\",\n" + "    \"rate\": \"0.83\",\n"
            + "    \"to\": \"AUD\"\n" + "  },\n" + "  {\n" + "    \"from\": \"AUD\",\n" + "    \"rate\": \"1.2\",\n"
            + "    \"to\": \"GBP\"\n" + "  }\n" + "]\n";

    private String mockedEmptyRate = "";

    @Mock
    LocalFileDataSource rateDataSource;

    @Before
    public void setUp() {
        parser = new RateFileParser(new Gson(), rateDataSource);
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerExceptionException_whenNoAccessToFile() {
        parser.getListFromJson();
    }

    @Test
    public void transformer_Should_throwIndexOutOfBoundsException_whenCorrectJSON() {
        when(rateDataSource.getLocalJSON()).thenReturn(Single.just(mockedRate));
        assertTrue(!parser.getListFromJson().toBlocking().value().isEmpty());
    }

    @Test
    public void transformer_Should_throwIndexOutOfBoundsException_whenCorrectFile() {
        when(rateDataSource.getLocalJSON()).thenReturn(Single.just(mockedEmptyRate));
        assertTrue(parser.getListFromJson().toBlocking().value() == null);
    }
}
