package org.tek.geza.bestmovies.view.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.TextView;

import org.tek.geza.bestmovies.R;

import butterknife.BindView;

public abstract class DetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_original_title)
    TextView tvOriginalTitle;

    @BindView(R.id.tv_year)
    TextView tvYear;

    @BindView(R.id.tv_rating)
    TextView tvRating;

    @BindView(R.id.tv_genre)
    TextView tvGenre;

    @BindView(R.id.tv_budget)
    TextView tvBudget;

    @BindView(R.id.tv_country)
    TextView tvCountry;

    @BindView(R.id.tv_overview)
    TextView tvOverview;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData(getIntent().getIntExtra("ID", -1));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    protected abstract void bindData(int id);
}

