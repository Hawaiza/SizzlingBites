package com.example.microproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class PracticalExam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_exam);
        CheckBox pizza = (CheckBox) findViewById(R.id.pizza);
        CheckBox coffee = (CheckBox) findViewById(R.id.coffee);
        CheckBox burger =(CheckBox) findViewById(R.id.burger);
        Button button =(Button) findViewById(R.id.order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "";
                Integer total = 0;
                if(pizza.isChecked()){
                    str += "Pizza: 500rs\n";
                    total += 500;
                }
                if(coffee.isChecked()){
                    str += "Coffee: 300rs\n";
                    total += 300;
                }
                if(burger.isChecked()){
                    str += "Burger: 200rs\n";
                    total += 200;
                }
                str += "Total: " + total + "rs\n";
                Toast.makeText(PracticalExam.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
