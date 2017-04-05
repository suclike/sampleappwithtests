package example.badoo.com.transactionviewer.ui;

import android.os.Bundle;

import android.support.annotation.LayoutRes;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.ViewGroup;

import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

import example.badoo.com.transactionviewer.BaseApplication;
import example.badoo.com.transactionviewer.R;
import example.badoo.com.transactionviewer.di.ActivityComponent;
import example.badoo.com.transactionviewer.di.ApplicationComponent;
import example.badoo.com.transactionviewer.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.list_recycler_view_toolbar)
    Toolbar toolbar;

    @Bind(R.id.list_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.list_recycler_view_empty_layout)
    ViewGroup errorLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        // handling injection
        handleInjection(createActivityComponent());

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);

        setContentView(layoutToInflate());

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initRecycleView(recyclerView);

        setupActionBar(toolbar, getActionBarTitle());
    }

    @LayoutRes
    protected int layoutToInflate() {
        return R.layout.list_activity_layout;
    }

    private ActivityComponent createActivityComponent() {
        ApplicationComponent applicationComponent = ((BaseApplication) getApplication()).applicationComponent;
        ActivityModule activityInstanceModule = new ActivityModule(this);
        return applicationComponent.plus(activityInstanceModule);
    }

    protected abstract void handleInjection(final ActivityComponent component);

    protected void setupActionBar(final Toolbar toolbar, final String actionBarTitle) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
        }
    }

    protected String getActionBarTitle() {
        return "";
    }

    protected void initRecycleView(final RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(5);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                mLayoutManager.getOrientation()));
    }
}
