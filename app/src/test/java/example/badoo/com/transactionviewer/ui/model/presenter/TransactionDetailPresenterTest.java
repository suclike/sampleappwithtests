package example.badoo.com.transactionviewer.ui.model.presenter;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;

import example.badoo.com.transactionviewer.domain.modifier.PriceValueConverter;
import example.badoo.com.transactionviewer.domain.modifier.PriceValueTransformer;
import example.badoo.com.transactionviewer.domain.modifier.TransactionItemConverter;
import example.badoo.com.transactionviewer.domain.service.RateDataService;
import example.badoo.com.transactionviewer.domain.service.RateService;
import example.badoo.com.transactionviewer.model.data.RawRate;
import example.badoo.com.transactionviewer.model.data.RawTransaction;

import rx.Single;

public class TransactionDetailPresenterTest {

    private TransactionDetailPresenter presenter;

    @Mock
    RateDataService rateDataService;

    @Mock
    RateService rateService;

    @Before
    public void setUp() {
        presenter = new TransactionDetailPresenter(rateService,
                new TransactionItemConverter(new PriceValueConverter(), new PriceValueTransformer()));
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerExceptionException_whenNoRateList() {
        presenter.getTransactionDetailSubscription(new ArrayList<RawTransaction>());
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerExceptionException_whenRateList() {
        List<RawRate> list = new ArrayList<>();
        list.add(new RawRate("A1d", "1", "CAD"));

        // todo add proper mocks && create test observer
        when(rateService.getRateList()).thenReturn(Single.just(list));

        presenter.getTransactionDetailSubscription(new ArrayList<RawTransaction>());
    }
}
