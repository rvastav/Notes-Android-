package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class show_existed_notes  extends AppCompatActivity {
    DatabaseHelper mydb;
    ListView listView;
   // ArrayList<String> thelist;
    TextView  showname,shownotes;
    ImageView showpicture;
    String Name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.balwindersingh.noteslayout.R.layout.show_existed_notes);
        Intent intent = getIntent();
        Name = intent.getStringExtra("Name");
        mydb = new DatabaseHelper(this);

         Cursor data = mydb.getListContentsSingle(Name);

         data.moveToFirst();
       // showname.setText("hello");

       //showpicture = (ImageView) findViewById(R.id.show_img);
        showname = (TextView) findViewById(com.example.balwindersingh.noteslayout.R.id.showname);
        shownotes = (TextView) findViewById(com.example.balwindersingh.noteslayout.R.id.shownotes);

        showname.setText(data.getString(1));
       shownotes.setText(data.getString(2));


        Bitmap bmp = BitmapFactory.decodeByteArray( data.getBlob(3), 0, data.getBlob(3).length);
        ImageView image = (ImageView) findViewById(com.example.balwindersingh.noteslayout.R.id.show_img);

        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, 500,
                500, false));



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.example.balwindersingh.noteslayout.R.menu.menu_for_show_data, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void back(MenuItem item) {
        this.finish();

    }
    public void delete(MenuItem item) {
        Intent intent = getIntent();
        mydb = new DatabaseHelper(this);
        String Name = intent.getStringExtra("Name");
        Cursor data = mydb.deletesinglecontent(Name);
        data.moveToFirst();
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
          //this.finish();



    }

    public void showmap(MenuItem item){
        Intent myIntent = new Intent(this, MapsActivity.class);
        myIntent.putExtra("Name",Name);
        this.startActivity(myIntent);
    }




}
