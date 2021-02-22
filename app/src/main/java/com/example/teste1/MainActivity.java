package com.example.teste1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private Button button1;
    private ListView nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        ListView listview = (ListView) findViewById(R.id.nome);
        String[] dados = new String[] { "Chevrolet", "Volkswagen", "Fiat", "Toyota", "Hyundai",
                "Jeep", "Honda", "Nissan",
                "Citroën", "Mitsubishi", "Peugeot", "Chery", "BMW",  };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);
        listview.setAdapter(adapter);
    }

}