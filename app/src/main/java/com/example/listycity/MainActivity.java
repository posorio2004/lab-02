package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;


    LinearLayout text_entry_layout;

    Button addCity_button;
    Button removeCity_button;

    EditText newCity_TextEntry;
    Button confirmAddCity_button;

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;


    String selected_city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Associate vars with elements
        cityList = findViewById(R.id.city_list);
        text_entry_layout = findViewById(R.id.Text_Entry_Layout);
        addCity_button = findViewById(R.id.Add_City_Button);
        removeCity_button = findViewById(R.id.Remove_City_Button);
        newCity_TextEntry = findViewById(R.id.City_Text_Entry);
        confirmAddCity_button = findViewById(R.id.Confirm_City_Text_Entry);






        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.context, dataList);
        cityList.setAdapter(cityAdapter);



        //Setup Listeners
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_city = cityAdapter.getItem(position);
            }
        });

        addCity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                text_entry_layout.setVisibility(View.VISIBLE);


            }
        });


        removeCity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dataList.remove(selected_city);
                cityAdapter.notifyDataSetChanged();
                selected_city = "";

            }
        });

        confirmAddCity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.add(newCity_TextEntry.getText().toString());
                cityAdapter.notifyDataSetChanged();
                text_entry_layout.setVisibility(View.GONE);
                selected_city = "";

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}