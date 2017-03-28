package example.badoo.com.transactionviewer.di;

import dagger.Subcomponent;

import example.badoo.com.transactionviewer.di.module.ActivityModule;
import example.badoo.com.transactionviewer.di.qualifier.ActivityInstanceScope;
import example.badoo.com.transactionviewer.ui.TransactionDetailActivity;
import example.badoo.com.transactionviewer.ui.TransactionListActivity;

@ActivityInstanceScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(TransactionListActivity activity);

    void inject(TransactionDetailActivity tranactionDetailActivity);
}
