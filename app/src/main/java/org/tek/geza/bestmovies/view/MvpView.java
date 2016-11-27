package org.tek.geza.bestmovies.view;

/**
 * Created by gezacsorba on 26/11/2016.
 */

public interface MvpView {

    void showError(Throwable t);

    void showMessage(String message);

    void showProgress(String message);

    void hideProgress();
}
