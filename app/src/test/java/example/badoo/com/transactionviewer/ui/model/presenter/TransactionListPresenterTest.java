package example.badoo.com.transactionviewer.ui.model.presenter;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;

import example.badoo.com.transactionviewer.domain.service.TransactionService;
import example.badoo.com.transactionviewer.model.data.RawTransaction;

import rx.Single;

public class TransactionListPresenterTest {

    private TransactionListPresenter presenter;

    @Mock
    TransactionService transactionService;

    @Before
    public void setUp() {
        presenter = new TransactionListPresenter(transactionService);
    }

    @Test(expected = NullPointerException.class)
    public void transformer_Should_throwNullPointerExceptionException_whenNoRateList() {
        HashMap<String, List<RawTransaction>> map = new HashMap<>();
        List<RawTransaction> list = new ArrayList<>();
        list.add(new RawTransaction("A1d", "1", "CAD"));
        map.put("USD", list);

        // todo add proper mocks && create test observer
        when(transactionService.getListFromJson()).thenReturn(Single.just(map));
        presenter.getTransactionListSubscription();
    }
}
