package com.example.apputnsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario, txtClave;
    Switch swRecordarUsuario;
    SharedPreferences config;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUsuario = findViewById(R.id.txtUsuario);
        txtClave = findViewById(R.id.txtClave);
        swRecordarUsuario = findViewById(R.id.swRecordarUsuario);
        config = getSharedPreferences("data.cfg", Context.MODE_PRIVATE);
        editor = config.edit();

        // lectura de datos desde config.
        txtUsuario.setText( config.getString("userName", ""));
        txtClave.setText( config.getString("password", ""));
        if (config.getBoolean("recordarDatos", false) == true)
        {
            swRecordarUsuario.setChecked(true);
        }
        else
        {
            swRecordarUsuario.setChecked(false);
        }
    }

    public void cmdAcceder_onClick(View v)
    {
        if (swRecordarUsuario.isChecked())
        {
            editor.putString("userName", txtUsuario.getText().toString());
            editor.putString("password", txtClave.getText().toString());
        }
        else
        {
            editor.putString("userName", "");
            editor.putString("password", "");
        }
        editor.putBoolean("recordarDatos", swRecordarUsuario.isChecked());
        editor.commit();

        Toast.makeText(this, "Acceso permitido", Toast.LENGTH_LONG ).show();
    }
}