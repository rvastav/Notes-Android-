package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter adapter;

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

  adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,thelist);
        showdata();
     listView.setAdapter(adapter);



    }

    public void showdata(){
        thelist.clear();
        final Cursor data = mybd.getListContents();
        if (data.getCount() == 0) {
            //Toast.makeText(this, "database empty", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                thelist.add(data.getString(1));
                //thelist1.add(data.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);
                listView.setAdapter(listAdapter);

            }
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(listView.getContext(),show_existed_notes.class);

                String ID = thelist.get(position);
                //Toast.makeText(getApplicationContext(),ID,Toast.LENGTH_LONG).show();
                intent.putExtra("Name",ID);
              //  Intent myIntent = new Intent(this, show_existed_notes.class);

             listView.getContext().startActivity(intent);



               // this,thelist.get(position), Toast.LENGTH_LONG
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showdata();
//        Cursor data = mybd.getListContents();
//        thelist.clear();
//        if (data.getCount() == 0) {
//            Toast.makeText(this, "database empty", Toast.LENGTH_LONG).show();
//        } else {
//            while (data.moveToNext()) {
//                thelist.add(data.getString(1));
//                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);
//                listView.setAdapter(listAdapter);
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.example.balwindersingh.noteslayout.R.menu.menu, menu);

        MenuItem item = menu.findItem(com.example.balwindersingh.noteslayout.R.id.menuSearch);
        SearchView searchView =(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                thelist.clear();
                final Cursor data = mybd.getListContentsSearch(newText);
                if (data.getCount() == 0) {
                    //Toast.makeText(getApplicationContext(), "database empty", Toast.LENGTH_LONG).show();
                } else {
                    while (data.moveToNext()) {
                        thelist.add(data.getString(1));
                        //thelist1.add(data.getString(2));
                        ListAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, thelist);
                        listView.setAdapter(listAdapter);
                    }
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void addNotes(MenuItem item) {
        Intent myIntent = new Intent(this, add_sub_notes.class);
        this.startActivity(myIntent);

    }



//    public void onOptionScrub(MenuItem item) {
//    }

    public void onOptionVacuum(MenuItem item) {
    }

    public void onOptionScrub(MenuItem item) {
    }
}