package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firni.zado.Adatbazis;

public class MainActivity extends AppCompatActivity {

    Button btn_reg,btn_log;
    EditText Felhnev,Jelszo;
    com.example.firni.zado.Adatbazis db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_log = (Button) findViewById(R.id.btn_bejelentkezes);
        btn_reg = (Button) findViewById(R.id.btn_regisztracio);
        Felhnev = (EditText) findViewById(R.id.edit_nev);
        Jelszo = (EditText) findViewById(R.id.edit_jelszo);

        db = new Adatbazis(this);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nev = Felhnev.getText().toString();
                String jelszo = Jelszo.getText().toString();
                boolean adatok = db.felhasznaloesjelszo(nev,jelszo);

                if (nev.equals("") || jelszo.equals("")) {

                    Toast.makeText(getApplicationContext(), "Üres mező!", Toast.LENGTH_SHORT).show();
                } else
                {
                    if (adatok == true) {

                        Toast.makeText(getApplicationContext(), "Sikeres belépés!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Rossz felhasználó vagy jelszó!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
