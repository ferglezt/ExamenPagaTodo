package com.ferglezt.examenpagatodo.interactor;

/**
 * Created by fernando on 26/01/18.
 */

public interface LoginInteractor {
    void login(String user, String password);
    void setLoginCallback(LoginCallback loginCallback);

    interface LoginCallback {
        void onLoginSuccess();
        void onLoginError(int statusCode);
    }
}
