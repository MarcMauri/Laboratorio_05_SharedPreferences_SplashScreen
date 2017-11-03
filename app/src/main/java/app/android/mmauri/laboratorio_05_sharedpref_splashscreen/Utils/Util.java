package app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Utils;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;


/**
 * Created by marc on 10/27/17.
 */

public class Util {

    public static void addCredentialsToSharedPrefs(SharedPreferences prefs, String email, String password) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public static String getUserEmailFromSharedPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static String getUserPwdFromSharedPrefs(SharedPreferences preferences) {
        return preferences.getString("password", "");
    }

    public static void removeCredentialsFromSharedPrefs(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }


    /* Extra por comidad y limpieza de codigo */
    public static void showIconOnActionBar(ActionBar actionBar, int iconResource) {
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(iconResource);
    }
}
