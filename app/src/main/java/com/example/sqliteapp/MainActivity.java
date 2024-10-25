package com.example.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ImageButton btnAdd;
    DbHelper  db= new DbHelper(this);

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

        listView=findViewById(R.id.listView);
        btnAdd=findViewById(R.id.btnAdd);


        fillData();
        ItemAdapter adapter= new ItemAdapter(this,
                db.getData());
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()
                        ,AddActivity.class);
                startActivity(intent);
            }
        });

    }
    void fillData()
    {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Tom","tom@gmail.com","R.drawable.cat"),
                new Person("Sam","samm@gmail.com","R.drawable.dog"),
                new Person("Alice","alice@gmail.com","R.drawable.cat_icon"),
                new Person("Kate","kate@gmail.com","R.drawable.dog"),
                new Person("Peter","peter@gmail.com","R.drawable.cat"),
                new Person("Henry","henry@gmail.com","R.drawable.cat_icon"),
                new Person("Lee","lee@gmail.com","R.drawable.cat"),
                new Person("Anne","anne@gmail.com","R.drawable.dog"),
                new Person("Oleg","oleg@gmail.com","R.drawable.cat_icon"),
                new Person("Ken","ken@gmail.com","R.drawable.cat")
        ));
        people.forEach(x->db.insertData(x));
    }
}