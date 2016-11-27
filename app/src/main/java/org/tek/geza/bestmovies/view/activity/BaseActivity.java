package org.tek.geza.bestmovies.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.tek.geza.bestmovies.BestMoviesApplication;
import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.view.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    protected CompositeSubscription subscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createComponent(((BestMoviesApplication) getApplication()).getAppComponent());

        subscriptions = new CompositeSubscription();
        setContentView(getLayoutId());
        ButterKnife.bind(this);

    }

    protected abstract void createComponent(AppComponent appcomponent);

    protected abstract int getLayoutId();

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(String message) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
