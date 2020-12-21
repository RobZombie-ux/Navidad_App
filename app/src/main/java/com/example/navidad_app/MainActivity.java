package com.example.navidad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    Button buttonEntrar, buttonRegitrar;
    EditText etCorreo, etContraseña;

    //Instanciar BD
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "USUARIODB",null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonEntrar = (Button)findViewById(R.id.btnLogin);
        buttonRegitrar = (Button)findViewById(R.id.btnRegistrar);

        //BOTON LOGIN
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCorreo = (EditText)findViewById(R.id.txtId);
                etContraseña = (EditText)findViewById(R.id.txtContraseña);

                try {
                    Cursor cursor = helper.ConsultarUsuario(etCorreo.getText().toString(), etContraseña.getText().toString());
                    if(cursor.getCount()>0){
                        iniciarJuego();
                    } else{
                        Toast.makeText(getApplicationContext(),"Correo o Contraseña incorrecta",Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }

                etCorreo.setText("");
                etContraseña.setText("");
                etCorreo.findFocus();

            }
        });

        //BOTON REGISTRO
        buttonRegitrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);
            }
        });



    }

    //Iniciar juego
    private void iniciarJuego(){
        Intent i = new Intent(this, Juego.class);
        startActivity(i);
    }


}