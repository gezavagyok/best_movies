package org.tek.geza.bestmovies.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tek.geza.bestmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class ContentListFragment extends Fragment {

    public static final CharSequence MOVIE_TITLE = "Movies";
    public static final CharSequence TVSHOW_TITLE = "TV Shows";
    public static final CharSequence STAR_TITLE = "Stars";
    public static final String TAG = "CLFragment";
    public static final int TYPE_MOVIE = 0;
    public static final int TYPE_TVSHOW = 1;
    public static final int TYPE_STAR = 2;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    CompositeSubscription subscription;

    public static Fragment newInstance(int type) {
        switch (type) {
            case TYPE_MOVIE:
                return new MovieFragment();
            case TYPE_TVSHOW:
                return new TvShowFragment();
            case TYPE_STAR:
                return new StarFragment();
            default:
                Log.d(TAG, "Failed to create ContentListFragment, value = " + type);
                return null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        subscription.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        inject();
        subscription = new CompositeSubscription();
        ButterKnife.bind(this, view);
        onInit();
        setupRecyclerView();
        return view;
    }

    abstract void onInit();

    abstract void setupRecyclerView();

    abstract protected void inject();

}
