package com.sam.shoppingcart;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SAM on 6/6/2016.
 */
public class NextActivity extends AppCompatActivity {

    private TextView total;
    private ListView listView;
    private SQLiteHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        db = new SQLiteHandler(getApplicationContext());
        listView = (ListView)findViewById(R.id.selected_list);
        total = (TextView)findViewById(R.id.total);


        showlist();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Model model = (Model)parent.getItemAtPosition(position);
                db.deleteItem(model);
                Toast.makeText(getApplicationContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
                showlist();
                return false;
            }
        });



    }

    private void showlist() {

        ArrayList<Model> contactList = new ArrayList<Model>();
        contactList.clear();


        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        arrayList = db.getProfDetails();

        if (arrayList.size() != 0){
            for (int i = 0; i < arrayList.size(); i++){
                contactList.add(new Model(arrayList.get(i).get("name"), arrayList.get(i).get("price")));
            }
        }

        NewAdapter contactListAdapter = new NewAdapter(this,  R.layout.newlistrow,contactList);
        listView.setAdapter(contactListAdapter);

        // Add total price
        int totalValue = db.sumofPrice();
        total.setText(String.valueOf(totalValue));

    }
}
