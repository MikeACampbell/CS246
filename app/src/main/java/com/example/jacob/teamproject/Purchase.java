package com.example.jacob.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class Address{
    String street;
    String city;
    String state;
    String zip_code;
    String country;
    String phone;
}
public class Purchase extends AppCompatActivity {
    Vector<Address> listShipping = new Vector<>();
    List <Address> listBilling = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
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
    }

    public void openProduct(View view){
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);
    }


    public  void testBoth(View view){
        test("test1");
        test("test2");
    }
    public void test(String select_test){
        String test_street;
        String test_city;
        String test_state;
        String test_zip_code;
        String test_country;
        String test_phone;
        if (select_test.equals("test1")) {
            test_street = "123 main street";
            test_city = "Sugar city";
            test_state = "Idaho";
            test_zip_code = "83123";
            test_country = "US";
            test_phone = "208-529-3657";
        }
        else
        {
            test_street = "370 w 3rd s";
            test_city = "Rexburg";
            test_state = "Idaho";
            test_zip_code = "83445";
            test_country = "US";
            test_phone = "208-123-4657";
        }
        EditText ET_street = (EditText) findViewById(R.id.ET_street);
        EditText ET_city = (EditText) findViewById(R.id.ET_city);
        EditText ET_state = (EditText) findViewById(R.id.ET_state);
        EditText ET_zip = (EditText) findViewById(R.id.ET_zip);
        EditText ET_country = (EditText) findViewById(R.id.ET_country);
        EditText ET_phone = (EditText) findViewById(R.id.ET_phone);

         ET_street.setText(test_street);
         ET_city.setText(test_city);
         ET_state.setText(test_state);
         ET_zip.setText(test_zip_code);
         ET_country.setText(test_country);
         ET_phone.setText(test_phone);

        Address temp = new Address();
        temp.street = test_street;
        temp.city = test_city;
        temp.state = test_state;
        temp.zip_code = test_zip_code;
        temp.country = test_country;
        temp.phone = test_phone;

        try {
            validate(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validate(Address address) throws Exception {
        String test_street = address.street;
        String test_city = address.city;
        String test_state = address.state;
        String test_zip_code = address.zip_code;
        String test_country = address.country;
        String test_phone = address.phone;

        EditText ET_street = (EditText) findViewById(R.id.ET_street);
        EditText ET_city = (EditText) findViewById(R.id.ET_city);
        EditText ET_state = (EditText) findViewById(R.id.ET_state);
        EditText ET_zip = (EditText) findViewById(R.id.ET_zip);
        EditText ET_country = (EditText) findViewById(R.id.ET_country);
        EditText ET_phone = (EditText) findViewById(R.id.ET_phone);

        Address temp = new Address();
        temp.street = ET_street.getText().toString();
        temp.city = ET_city.getText().toString();
        temp.state = ET_state.getText().toString();
        temp.zip_code = ET_zip.getText().toString();
        temp.country = ET_country.getText().toString();
        temp.phone = ET_phone.getText().toString();

        if (!listShipping.lastElement().street.equals(test_street))
        {
            throw new Exception("Street is not the same");
        }
        if (!listShipping.lastElement().city.equals(test_city))
        {
            throw new Exception("City is not the same");
        }
        if (!listShipping.lastElement().state.equals(test_state))
        {
            throw new Exception("State is not the same");
        }
        if (!listShipping.lastElement().zip_code.equals(test_zip_code))
        {
            throw new Exception("Zip code is not the same");
        }
        if (!listShipping.lastElement().country.equals(test_country))
        {
            throw new Exception("Country is not the same");
        }
        if (!listShipping.lastElement().phone.equals(test_phone))
        {
            throw new Exception("Phone is not the same");
        }
    }
}
