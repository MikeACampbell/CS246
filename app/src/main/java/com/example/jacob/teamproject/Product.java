package com.example.jacob.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class Tie {
    float cost;
    String image;
    int stock;
    public String getName(){return image;}
    public Tie(String[] features)
    {
        assert(features.length == 3);
        image = features[0];
        cost = Float.parseFloat(features[1]);
        stock = Integer.parseInt(features[2]);
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

    float unit_Test() {
        float p1 = 50;
        float p2 = (float) 54.23;
        float p3 = (float) 43.12;
        //Tie t1 = new Tie(p1);
        //myTies.add(t1);
        //Tie t2 = new Tie(p2);
        //myTies.add(t2);
        //Tie t3 = new Tie(p3);
        //myTies.add(t3);

        float myTotal = calculateTotal();

        assert myTotal == (p1 + p2 + p3);

        return myTotal;
    }
}


public class Product extends AppCompatActivity {


    ImageView largeView;
    Product__Controller controller;
    List<Tie> display_ties = new ArrayList<>();
    Sell sell = new Sell();

    //Authenticator authenticator;
        //Authenticator.setDefault(new Authenticator(){
          //  protected PasswordAuthentication getPasswordAuthentication() {
             //   return new PasswordAuthentication("myuser","mypass".toCharArray());
            //}});
        //HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        //c.setUseCaches(false);
        //c.connect();

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
            Tie temp = new Tie (item.split(";"));
            display_ties.add(temp);
        }
        if (display_ties.size() != 9){
            Log.e("ProductActivity","There are " + display_ties.size() + " ties");
        }
        List<ImageView> images = new ArrayList<>();
        List<TextView> textViews = new ArrayList<>();
        int j = -1;
        for(Tie tie : display_ties){
            j++;
            ImageView newImage = new ImageView(this);
            TextView textView = new TextView(this);
            Button button = new Button(this);
            switch (tie.getName()){
                //case "Black Tie":
                    //newImage.setImageResource(R.drawable.black);
                    //textView.setText(tie.getName() + "\n" + tie.cost);
                    //newImage.setId(j);
                    //break;
                case "Pink Tie":
                    newImage.setImageResource(R.drawable.pink);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
                case "White Tie":
                    newImage.setImageResource(R.drawable.white);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
                case "Purple Tie":
                    newImage.setImageResource(R.drawable.purple);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
                case "Red Tie":
                    newImage.setImageResource(R.drawable.red);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
                case "Blue Tie":
                    newImage.setImageResource(R.drawable.blue);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
                case "Black Blue and White":
                    newImage.setImageResource(R.drawable.blue_n_white);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
                case "Black Red and White Tie":
                    newImage.setImageResource(R.drawable.black_red_white);
                    textView.setText(tie.getName() + "\n" + tie.cost);
                    newImage.setId(j);
                    break;
            }
            //newImage.setOnClickListener(addToCart(););
            images.add(newImage);

            textViews.add(textView);
        }
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.background);
        int x = 0;
        int y = 0;
        int i = 0;
        for (ImageView image : images) {
            textViews.get(i).setX(x);
            textViews.get(i).setY(y + 470);
            image.setX(x);
            image.setY(y);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(450,450);
            image.setLayoutParams(params);

            rl.addView(image);
            rl.addView(textViews.get(i));

            if (i > 0) {
                x += 550;
                x %= 1100;

                if (i % 2 == 0)
                    y += 400;
            }
            i++;
        }
    }

    public void openPurchase(View view){
        Intent intent = new Intent(this, Purchase.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cart", sell.myTies);
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("total", sell.total);
        intent.putExtra("cart", bundle);
        intent.putExtra("total", bundle2);
        //intent.putExtra(Sell.myTies, Sell.calculateTotal());
        startActivity(intent);
    }
    public void openGallery(View view){
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);
    }

    public void showImage(View view){
        //largeView.setVisibility(View.VISIBLE);
        //findViewById(R.id.background).setBackgroundColor(0xff444444);
        //switch(view.getId()){
            //case R.id.pink:
            //findViewById(0);
            //largeView.setImageResource(R.drawable.);
            //display_ties.get(0);
        //}
    }
    public void hideImage(View view){
        //largeView.setVisibility(View.INVISIBLE);
        //findViewById(R.id.background).setBackgroundColor(0x00000000);
    }
    public void showList(View view){
        ArrayAdapter<String> arrayAdapter;
        ListView listView = (ListView) findViewById(R.id.listView);
        List<String> myList = controller.items();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);
        //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
    }

    void addToCart(View view)
    {
        this.getResources().getInteger(R.integer.pink);
        Log.i("ProductActivity", "id value: " + view.getId());
        switch (view.getId()) {
            case black:
                if (display_ties.get(0).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(0).stock--;
                    sell.myTies.add(display_ties.get(0));
                    sell.calculateTotal();
                }
            case pink:
                if (display_ties.get(1).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(1).stock--;
                    sell.myTies.add(display_ties.get(1));
                    sell.calculateTotal();
                }
            case white:
                if (display_ties.get(2).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(2).stock--;
                    sell.myTies.add(display_ties.get(2));
                    sell.calculateTotal();
                }
            case purple:
                if (display_ties.get(3).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(3).stock--;
                    sell.myTies.add(display_ties.get(3));
                    sell.calculateTotal();
                }
            case red:
                if (display_ties.get(4).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(4).stock--;
                    sell.myTies.add(display_ties.get(4));
                    sell.calculateTotal();
                }
            case blue:
                if (display_ties.get(5).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(5).stock--;
                    sell.myTies.add(display_ties.get(5));
                    sell.calculateTotal();
                }

            case blue_n_white:
                if (display_ties.get(6).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(6).stock--;
                    sell.myTies.add(display_ties.get(6));
                    sell.calculateTotal();
                }
            case black_red_white:
                if (display_ties.get(7).stock < 1)
                    Log.i("ProductActivity", "There is no stock left for this tie");
                else {
                    display_ties.get(7).stock--;
                    sell.myTies.add(display_ties.get(7));
                    sell.calculateTotal();
                }
        }
        Log.i("ProductActivity", "The total cost is $" + sell.total);
    }

    void clearCart(View view) {
        sell.myTies.clear();
        sell.calculateTotal();
    }



}
