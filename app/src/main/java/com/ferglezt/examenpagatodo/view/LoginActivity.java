package com.ferglezt.examenpagatodo.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.ferglezt.examenpagatodo.R;
import com.ferglezt.examenpagatodo.interactor.LoginInteractorImp;
import com.ferglezt.examenpagatodo.presenter.LoginPresenter;
import com.ferglezt.examenpagatodo.presenter.LoginPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fernando on 26/01/18.
 */

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginView {
    @BindView(R.id.txt_user) EditText userEditText;
    @BindView(R.id.txt_pass) EditText passEditText;

    ProgressDialog dialog;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        try {
            getSupportActionBar().hide();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImp(new LoginInteractorImp(this));
        loginPresenter.setLoginView(this);

    }

    @OnClick(R.id.btn_login)
    public void login() {
        loginPresenter.login(userEditText.getText().toString(), passEditText.getText().toString());
    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.processing));
        dialog.setCancelable(false);
        dialog.setMax(100);
    }

    @Override
    public void showProgressDialog() {
        if(dialog == null) {
            initProgressDialog();
        }
        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if(dialog != null) {
            dialog.hide();
        }
    }

    @Override
    public void showHttpError(int statusCode) {
        Toast.makeText(this, String.valueOf(statusCode), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCredentialsError() {
        Toast.makeText(this, R.string.credentials_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void passToNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
