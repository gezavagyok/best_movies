package org.tek.geza.bestmovies.view;


public interface MvpView {

    void showError(Throwable t);

    void showMessage(String message);

    void showProgress(String message);

    void hideProgress();
}
