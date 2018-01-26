package com.ferglezt.examenpagatodo.interactor;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ferglezt.examenpagatodo.Config;
import com.ferglezt.examenpagatodo.sharedpreferences.MySharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fernando on 26/01/18.
 */

public class LoginInteractorImp implements LoginInteractor {
    private static final String TAG = "LoginInteractor";

    private Context context;
    private LoginCallback loginCallback;

    public LoginInteractorImp(Context context) {
        this.context = context;
    }


    @Override
    public void login(String user, String password) {
        Map<String, String> map = new HashMap<>();
        map.put(Config.USER, user);
        map.put(Config.PASS, password);


        JSONObject credentials = new JSONObject(map);
        JSONObject params = new JSONObject();

        try {
            params.put("data", credentials);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, params.toString());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Config.LOGIN_URL,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        String token = null;
                        String agente = null;
                        int id_user = -1;
                        int error = -1;

                        try {
                            token = response.getString(Config.TOKEN);
                            id_user = response.getInt(Config.ID_USER);
                            agente = response.getString(Config.AGENTE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            error = response.getJSONObject("error").getInt("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(loginCallback != null) {
                            if(token != null && error == -1) {
                                //We save the response values before calling onLoginSuccess
                                MySharedPreferences.getInstance(context)
                                        .setAgente(agente)
                                        .setToken(token)
                                        .setIdUser(id_user);

                                loginCallback.onLoginSuccess();
                            } else {
                                //We delete any previous stored values
                                MySharedPreferences.getInstance(context)
                                        .getSharedPreferences()
                                        .edit()
                                        .clear()
                                        .commit();

                                loginCallback.onLoginError(error);
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        int statusCode = -1;

                        NetworkResponse networkResponse = error.networkResponse;

                        if(networkResponse != null) {
                            statusCode = networkResponse.statusCode;
                        }

                        if(loginCallback != null) {
                            loginCallback.onLoginError(statusCode);
                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("SO", "Android");
                params.put("Version", "2.5.2");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);
    }

    @Override
    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
}
