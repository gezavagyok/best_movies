package org.tek.geza.bestmovies.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.tek.geza.bestmovies.view.fragment.ContentListFragment;

public class MovieDbPager extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 3;

    public MovieDbPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ContentListFragment.newInstance(ContentListFragment.TYPE_MOVIE);
            case 1:
                return ContentListFragment.newInstance(ContentListFragment.TYPE_TVSHOW);
            case 2:
                return ContentListFragment.newInstance(ContentListFragment.TYPE_STAR);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ContentListFragment.MOVIE_TITLE;
            case 1:
                return ContentListFragment.TVSHOW_TITLE;
            case 2:
                return ContentListFragment.STAR_TITLE;
            default:
                return null;
        }
    }
}
