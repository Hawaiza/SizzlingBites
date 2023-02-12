package com.example.microproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pizza1 extends AppCompatActivity {
    public ListView productlist;
    SimpleCursorAdapter simpleCursorAdapter;


    private cartDBHandler cartDBHandler;
    public int quan=1;
    TextView price;
    ImageButton plus, minus;
    TextView name,quantity,cost,category;
    Button add;
    CheckBox topping, cheese;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza1);

        add=(Button) findViewById(R.id.addtocart);
        productlist=findViewById(R.id.productlist);

        category=(TextView) findViewById(R.id.category);
        name=(TextView) findViewById(R.id.pizzaNameInfo);
        price=(TextView) findViewById(R.id.pizzaPrice);
        cost=(TextView) findViewById(R.id.price);
        quantity=(TextView) findViewById(R.id.quantity);
        plus=(ImageButton) findViewById(R.id.addquantity);
        minus=(ImageButton) findViewById(R.id.subquantity);
        back = (ImageButton)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent P = new Intent(Pizza1.this,MainActivity.class);
                startActivity(P);
            }
        });

        cartDBHandler = new cartDBHandler(Pizza1.this);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    quan++;



                //     price.setText(String.valueOf(pizzaPrice));
                displayQuantity();

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quan!=1)
                quan--;


                //     price.setText(String.valueOf(pizzaPrice));
                displayQuantity();

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pname=name.getText().toString();
                String pquantity=quantity.getText().toString();
                String pcost=cost.getText().toString();
                String pcategory=category.getText().toString();

//                Cursor c= cartDBHandler.checkproductid(pname);
//                if(c.getCount()>0) {
//                    Toast.makeText(Pizza1.this, "ID already exist", Toast.LENGTH_SHORT).show();
//                }
//                else {

                 cartDBHandler.addnewproducts(pname, pquantity, pcategory, pcost);

                    Toast.makeText(Pizza1.this, "product has been added to cart", Toast.LENGTH_SHORT).show();


                    Intent i1 = new Intent(getApplicationContext(), cart.class);
                    startActivity(i1);
       //         }
            }
        });

    }


    private void displayQuantity() {
        quantity.setText(String.valueOf(quan));
        int baseprice = 300;

        int newprice = baseprice * quan;
        String setfinalprice = String.valueOf(newprice);
        price.setText(setfinalprice);

        int newprice1 = baseprice * quan;
        String setfinalprice1 = String.valueOf(newprice);
        cost.setText(setfinalprice);


    }


}