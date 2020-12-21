package com.example.navidad_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends Activity {

    Button buttonReg, buttonCancelar;
    EditText etNom, etCorreo, etPas;

    //Instanciar la BD
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "USUARIODB", null, 1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        buttonCancelar = (Button)findViewById(R.id.btnCancelar);
        buttonReg = (Button)findViewById(R.id.btnRegistraUsuario);
        etNom = (EditText)findViewById(R.id.txtNombre);
        etCorreo = (EditText)findViewById(R.id.txtCorreo);
        etPas = (EditText)findViewById(R.id.txtPasswordRegistro);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                helper.insertarRegistro(String.valueOf(etNom.getText()), String.valueOf(etCorreo.getText()),
                        String.valueOf(etPas.getText()));
                helper.cerrar();

                Toast toast = Toast.makeText(getApplicationContext(),"Usuario registrado", Toast.LENGTH_LONG);
                toast.show();
                //Regresar al MainActivity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
