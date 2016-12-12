package org.tek.geza.bestmovies.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.tek.geza.bestmovies.BestMoviesApplication;
import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.HomeModule;
import org.tek.geza.bestmovies.presenter.HomePresenter;
import org.tek.geza.bestmovies.util.OnTextClearedWatcher;
import org.tek.geza.bestmovies.util.SearchListener;
import org.tek.geza.bestmovies.view.adapter.MovieDbPager;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view_pager_main)
    ViewPager viewPager;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.view_pager_header)
    PagerTitleStrip titleStrip;

    @BindView(R.id.et_search)
    EditText searchTextView;

    @BindView(R.id.adView)
    AdView adView;

    @Inject
    MovieDbPager pagerAdapter;

    @Inject
    SearchListener searchListener;

    @Inject
    OnTextClearedWatcher watcher;

    @Inject
    HomePresenter presenter;

    ActivityModule activityModule;

    public ActivityModule getActivityModule() {
        return activityModule;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAds();
        setSupportActionBar(toolbar);
        setupViewPager();
        searchTextView.setOnEditorActionListener(searchListener);
        searchTextView.addTextChangedListener(watcher);
    }

    private void initAds() {
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8360076444411384~4615082156");
        AdRequest adRequest = presenter.getAdSetup();
        adView.loadAd(adRequest);
        adView.setVisibility(View.GONE);
    }

    @Override
    protected void createComponent(AppComponent appcomponent) {
        activityModule = new ActivityModule(this);
        BestMoviesApplication.getComponent()
                .createHome(new HomeModule(),activityModule)
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setupViewPager() {
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        titleStrip.setTextColor(Color.WHITE);
        titleStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_remove_ads:
                final AlertDialog dialog = new AlertDialog.Builder(this).setView(R.layout.dialog_purchase).create();
                dialog.show();
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.startPurchaseProcess(HomeActivity.this);
                    }
                });
                dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                return true;
            default:
                break;
        }
        return false;
    }
}
