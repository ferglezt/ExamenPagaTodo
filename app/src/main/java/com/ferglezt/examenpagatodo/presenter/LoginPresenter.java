package com.ferglezt.examenpagatodo.presenter;

/**
 * Created by fernando on 26/01/18.
 */

public interface LoginPresenter {

    void login(String user, String password);
    void setLoginView(LoginView loginView);

    interface LoginView {
        void showProgressDialog();
        void hideProgressDialog();
        void showHttpError(int statusCode);
        void showCredentialsError();
        void passToNextActivity();
    }
}
