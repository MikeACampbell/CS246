package com.example.jacob.teamproject;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 11/10/2015.
 */
public class Product__Controller {

    private CSVFile inventory;

    public Product__Controller(InputStream inStream){
        inventory = new CSVFile(inStream);
    }

    public List items(){
        //Parcelable state = listView.onSaveInstanceState();
        List<String[]> productList = inventory.read();

        List<String> items = new ArrayList<>();
        // list items: name, price, stock number
        for(int i = 1; i < productList.size(); i++) {
            items.add(productList.get(i)[2] + ";"
                    + productList.get(i)[10] + ";"
                    + productList.get(i)[25]);
        }
        return items;
    }
}


class CSVFile {
    private InputStream inputStream;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(){
        List<String[]> resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}

