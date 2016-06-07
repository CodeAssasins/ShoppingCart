package com.sam.shoppingcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Model> mListData;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list);


        mListData = new ArrayList<>();

        mListData.add(new Model("Beef Manhattan", "50"));
        mListData.add(new Model("Chicken Fried Steak", "90"));
        mListData.add(new Model("Corned Beef", "100"));
        mListData.add(new Model("Domesticated Turkey", "80"));
        mListData.add(new Model("Eggs Benedict", "10"));
        mListData.add(new Model("French Dip", "20"));
        mListData.add(new Model("Green Bean Casserole", "30"));
        mListData.add(new Model("Potato Salad", "40"));
        mListData.add(new Model("Pumpkin Pie", "60"));
        mListData.add(new Model("Salisbury Steak", "70"));

        adapter = new CustomListAdapter(this, R.layout.listrow, mListData);
        listView.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action) {

            startActivity(new Intent(MainActivity.this, NextActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}