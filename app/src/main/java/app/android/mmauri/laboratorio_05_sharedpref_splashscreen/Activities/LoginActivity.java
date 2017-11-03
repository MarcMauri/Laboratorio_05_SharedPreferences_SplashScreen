package app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import app.android.mmauri.laboratorio_05_sharedpref_splashscreen.R;
import app.android.mmauri.laboratorio_05_sharedpref_splashscreen.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Util.showIconOnActionBar(getSupportActionBar(), R.mipmap.ic_splash);

        bindUI();

        prefs = getSharedPreferences("Login", Context.MODE_PRIVATE);
        setCredentialsIfExist();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email, password)) {
                    updateSharedPreferences(email, password);
                    goToMainActivity();
                }
            }
        });
    }


    private void bindUI() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "The email is not correct, try again.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidPwd(password)) {
            Toast.makeText(this, "The password requires at least 4 characters.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPwd(String password) {
        return password.length() >= 4;
    }

    private void setCredentialsIfExist() {
        String email = Util.getUserEmailFromSharedPrefs(prefs);
        String password = Util.getUserPwdFromSharedPrefs(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            switchRemember.setChecked(true);
        }
    }

    private void updateSharedPreferences(String email, String password) {
        if (switchRemember.isChecked()) {
            Util.addCredentialsToSharedPrefs(prefs, email, password);
        } else {
            Util.removeCredentialsFromSharedPrefs(prefs);
        }
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
