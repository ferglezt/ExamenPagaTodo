package com.ferglezt.examenpagatodo.presenter;

import android.text.TextUtils;

import com.ferglezt.examenpagatodo.interactor.LoginInteractor;

/**
 * Created by fernando on 26/01/18.
 */

public class LoginPresenterImp implements LoginPresenter, LoginInteractor.LoginCallback {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
        this.loginInteractor.setLoginCallback(this);
    }


    @Override
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String user, String password) {
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            loginView.showCredentialsError();
            return;
        }
        loginView.showProgressDialog();
        loginInteractor.login(user, password);
    }


    @Override
    public void onLoginSuccess() {
        loginView.hideProgressDialog();
        loginView.passToNextActivity();
    }

    @Override
    public void onLoginError(int statusCode) {
        loginView.hideProgressDialog();
        loginView.showHttpError(statusCode);
    }
}
