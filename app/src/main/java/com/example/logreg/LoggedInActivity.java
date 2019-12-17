package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        Button LogOut;
        TextView Nev;
        String teljesnev;
        LogOut = (Button) findViewById(R.id.btn_kijelentkezes);
        Nev = (TextView) findViewById(R.id.text_nev);

        teljesnev = getIntent().getExtras().getString("TeljesNev");
        Nev.setText("Üdövözöllek, "+ teljesnev + "!");


        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
