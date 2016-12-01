package org.tek.geza.bestmovies.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.tek.geza.bestmovies.BestMoviesApplication;
import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.HomeModule;
import org.tek.geza.bestmovies.util.OnTextClearedWatcher;
import org.tek.geza.bestmovies.util.SearchListener;
import org.tek.geza.bestmovies.view.adapter.MovieDbPager;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

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

    @Inject
    MovieDbPager pagerAdapter;

    @Inject
    SearchListener searchListener;

    @Inject
    OnTextClearedWatcher watcher;

    ActivityModule activityModule;

    public ActivityModule getActivityModule() {
        return activityModule;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setupViewPager();
        searchTextView.setOnEditorActionListener(searchListener);
        searchTextView.addTextChangedListener(watcher);
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
            case R.id.menu_about:
                // show about
                return true;
            case R.id.menu_remove_ads:
                return true;
            default:
                break;
        }
        return false;
    }
}
