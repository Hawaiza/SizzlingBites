package com.example.microproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class cart extends AppCompatActivity {
    public ListView productlist;
    SimpleCursorAdapter simpleCursorAdapter;
    cartDBHandler DB;
    TextView products, quantity;
    public Integer quan=1;
    ImageButton deleteicon;
    TextView price;
    ImageButton back;
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        plus=(ImageButton) findViewById(R.id.addquantity);
//        minus=(ImageButton) findViewById(R.id.subquantity);
        quantity=(TextView) findViewById(R.id.quantity);
        price=(TextView) findViewById(R.id.price);
        productlist = findViewById(R.id.productlist);
        deleteicon = findViewById(R.id.deleteicon);
        Button placeorder = findViewById(R.id.placeorder);
        back = (ImageButton)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(cart.this,Pizza1.class);
                startActivity(c);
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cart.this, "Order Placed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


     //   DB = new cartDBHandler(getApplicationContext());

        deleteall();

        DB = new cartDBHandler(this);
        try {
            setproductsadapter();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }





    }


    public void setproductsadapter()
    {
        simpleCursorAdapter=DB.getproductdata();
        productlist.setAdapter(simpleCursorAdapter);
    }



    @Override
    public void onResume() {
        super.onResume();
        simpleCursorAdapter=DB.getproductdata();
        productlist.setAdapter(simpleCursorAdapter);
    }

    public void deleteall() {
        deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a = new AlertDialog.Builder(cart.this);
                a.setMessage("Do you want to delete all products?");
                a.setTitle("Delete products");
                a.setIcon(R.drawable.deleteicon2);
                a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DB.deleteall();
                        Toast.makeText(cart.this, "Products deleted", Toast.LENGTH_SHORT).show();
                        finish();   //to go back on the list activity

                    }
                });
                a.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog b = a.create();
                a.show();
            }
        });

    }


}
