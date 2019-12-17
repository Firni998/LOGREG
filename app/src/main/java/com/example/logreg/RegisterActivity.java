package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    com.example.firni.zado.Adatbazis db;
    EditText Nev,Jelszo,TeljesNev,Email;
    Button Reg,Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Nev = (EditText) findViewById(R.id.edit_nev);
        Jelszo = (EditText) findViewById(R.id.edit_jelszo);
        TeljesNev = (EditText) findViewById(R.id.edit_teljes_nev);
        Email = (EditText) findViewById(R.id.edit_e_mail);
        Reg = (Button) findViewById(R.id.btn_regisztracio);
        Log = (Button) findViewById(R.id.btn_bejelentkezes);
        db = new com.example.firni.zado.Adatbazis(this);

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nev = Nev.getText().toString();
                String jelszo = Jelszo.getText().toString();
                String email = Email.getText().toString();
                String teljesnev = TeljesNev.getText().toString();

                Boolean ellenorzes = db.ellenorzesNevEmail(nev,email);
                Boolean beolvas = db.beolvas(nev,jelszo,email,teljesnev);


                if(nev.equals("") || jelszo.equals("") || email.equals("") || teljesnev.equals("")) {

                    Toast.makeText(getApplicationContext(),"Üres mező",Toast.LENGTH_SHORT).show();
                }
                else if(ellenorzes == true)
                {
                    if(beolvas == true)
                    {
                      Toast.makeText(getApplicationContext(),"Sikeres regisztráció!",Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(RegisterActivity.this, LoggedInActivity.class);
                      intent.putExtra("TeljesNev", teljesnev);
                      startActivity(intent);
                      finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"A felhasználó név vagy e-mail már foglalt!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


}
