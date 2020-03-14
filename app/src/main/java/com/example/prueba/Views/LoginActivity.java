package com.example.prueba.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prueba.R;

public class LoginActivity extends AppCompatActivity {

    EditText txt_usuario, txt_contraseña;
    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        getSupportActionBar().hide();

        txt_usuario = (EditText) findViewById(R.id.txtUsuario);
        txt_contraseña = (EditText) findViewById(R.id.txtContraseña);

        btn_ingresar = (Button) findViewById(R.id.btnIngresar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_usuario.getText().toString().trim().isEmpty()) {
                    txt_usuario.setError("Llene este campo por favor");
                    txt_usuario.requestFocus();
                    return;
                }
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("idasesor", txt_usuario.getText().toString());

                startActivity(i);

            }
        });

    }


}
