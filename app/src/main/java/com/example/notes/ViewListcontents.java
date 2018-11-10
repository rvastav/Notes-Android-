package com.example.notes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewListcontents extends AppCompatActivity {


    DatabaseHelper mybd;
    ListView listView;
    ArrayList<String> thelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.balwindersingh.noteslayout.R.layout.activity_main);

        listView = (ListView) findViewById(com.example.balwindersingh.noteslayout.R.id.listview);
        mybd = new DatabaseHelper(this);

       thelist = new ArrayList<>();

       Cursor data = mybd.getListContents();
        if (data.getCount() == 0) {
        } else {
            while (data.moveToNext()) {
                thelist.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);
                listView.setAdapter(listAdapter);
            }
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }



}