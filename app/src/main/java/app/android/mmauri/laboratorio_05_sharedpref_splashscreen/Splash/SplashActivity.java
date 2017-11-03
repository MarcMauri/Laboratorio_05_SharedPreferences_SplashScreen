package app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Activities.LoginActivity;
import app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Activities.MainActivity;
import app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Login", Context.MODE_PRIVATE);

        /* Mock time 1.5s */
        SystemClock.sleep(1500);

        Intent i;
        if (!TextUtils.isEmpty(Util.getUserEmailFromSharedPrefs(prefs)) &&
                !TextUtils.isEmpty(Util.getUserPwdFromSharedPrefs(prefs))) {
            i = new Intent(this, MainActivity.class);
        } else {
            i = new Intent(this, LoginActivity.class);
        }
        startActivity(i);
        finish();
    }
}
