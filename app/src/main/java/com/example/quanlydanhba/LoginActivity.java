package com.example.quanlydanhba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String FIXED_USER = "admin";
    private static final String FIXED_PASS = "123456";

    private EditText edtUsername, edtPassword;
    private TextView tvLoginError;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvLoginError = findViewById(R.id.tvLoginError);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String user = edtUsername.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            tvLoginError.setText("Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu");
            tvLoginError.setVisibility(View.VISIBLE);
            return;
        }

        if (user.equals(FIXED_USER) && pass.equals(FIXED_PASS)) {
            tvLoginError.setVisibility(View.GONE);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            tvLoginError.setText("Sai tên đăng nhập hoặc mật khẩu");
            tvLoginError.setVisibility(View.VISIBLE);
        }
    }
}