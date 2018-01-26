package com.ferglezt.examenpagatodo.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.ferglezt.examenpagatodo.Config;

/**
 * Created by fernando on 26/01/18.
 */

public class MySharedPreferences {
    private static MySharedPreferences instance;
    private SharedPreferences sharedPreferences;

    public static MySharedPreferences getInstance(Context context) {
        if(instance == null) {
            instance = new MySharedPreferences(context);
        }
        return instance;
    }

    private MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public MySharedPreferences setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Config.TOKEN, token);
        editor.commit();
        return this;
    }

    public MySharedPreferences setAgente(String agente) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Config.AGENTE, agente);
        editor.commit();
        return this;
    }

    public MySharedPreferences setIdUser(int id_user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Config.ID_USER, id_user);
        editor.commit();
        return this;
    }

}
