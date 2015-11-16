package com.example.jacob.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

class Tie {
    float cost;
    String name;
    int stock;

    Tie(String tieName, float price, int quantity) {
        name = tieName;
        cost = price;
        stock = quantity;
    }
}

class Sell {
    Vector<Tie> myTies = new Vector<Tie>();
    float total;

    Sell() {
        total = 0;
    }

    float calculateTotal() {
        for(int i = 0; i < myTies.size(); i++) {
            total += myTies.get(i).cost;
        }

        return total;
    }

    boolean unit_Test() {
        float p1 = 50;
        float p2 = (float) 54.23;
        float p3 = (float) 43.12;
        Tie t1 = new Tie(p1);
        myTies.add(t1);
        Tie t2 = new Tie(p2);
        myTies.add(t2);
        Tie t3 = new Tie(p3);
        myTies.add(t3);

        float myTotal = calculateTotal();

        if(myTotal == (p1 + p2 + p3))
            return true;
        else
            return false;
    }
}


public class Product extends AppCompatActivity {

    ImageView largeView;
    Product__Controller controller;
    Sell sell = new Sell();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //set largeview to our invisible image view
        largeView = (ImageView)findViewById(R.id.largeView);
        //read our CSV file and pass it to the controller
        InputStream inputStream = getResources().openRawResource(R.raw.products);
        controller = new Product__Controller(inputStream);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<String> myList = controller.items();
        for(String item : myList){

        }
    }

    public void openPurchase(View view){
        Intent intent = new Intent(this, Purchase.class);
        //intent.putExtra(Sell.myTies, Sell.calculateTotal());
        startActivity(intent);
    }
    public void openGallery(View view){
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);
    }

    public void showImage(View view){
        largeView.setVisibility(View.VISIBLE);
        findViewById(R.id.background).setBackgroundColor(0xff444444);
        switch(view.getId()){
            case R.id.imageView:
            largeView.setImageResource(R.drawable.tie);
        }
    }
    public void hideImage(View view){
        largeView.setVisibility(View.INVISIBLE);
        findViewById(R.id.background).setBackgroundColor(0x00000000);
    }
    public void showList(View view){
        ArrayAdapter<String> arrayAdapter;
        ListView listView = (ListView) findViewById(R.id.listView);
        List<String> myList = controller.items();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);
        //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
    }

    /*void addToCart(View view)
    {
        if(view .getid().stock < 1)
            Toast.makeText(getApplicationContext(), "This product is out of Stock", Toast.LENGTH_LONG).show();
            return;
        else {
            view.getid().stock--;
            sell.myTies.add(view.getid());
            sell.calculateTotal();
        }
    }
*/
}
