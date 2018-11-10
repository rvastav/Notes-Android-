package com.example.notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class add_sub_notes extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editsub, editnotes, editmarks;
    Button editbtn,showdata;

    ImageView editimage;
    Double latitude;
    Double longitude;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(com.example.balwindersingh.noteslayout.R.layout.add_sub_notes);


        mydb = new DatabaseHelper(this);
        editimage = (ImageView)findViewById(com.example.balwindersingh.noteslayout.R.id.img1);
        editsub = findViewById(com.example.balwindersingh.noteslayout.R.id.subject);
        editnotes = findViewById(com.example.balwindersingh.noteslayout.R.id.notes);
        //editmarks = findViewById(R.id.marks);
        editbtn = findViewById(com.example.balwindersingh.noteslayout.R.id.addnotes);
        showdata = findViewById(com.example.balwindersingh.noteslayout.R.id.addnotes);
        // Intent activityThatcalled =getIntent();
        //String previousActivity = activityThatcalled.getExtras().getString("callingActivity");
        // TextView callingActivitymassage  = (TextView) findViewById(R.id.calling_activity_info_text_masaage);

        //callingActivitymassage.append(" "+ previousActivity);
        // adddata();
       // showdata();
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
                10, mLocationListener);
    }
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            //Toast.makeText(getApplicationContext(),latitude + " ",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.example.balwindersingh.noteslayout.R.menu.menu2, menu);

        return true;

    }


//swiching activity
//MenuItem i

    public void adddata(MenuItem item) {
        editimage.getDrawable();

        BitmapDrawable bitmapDrawable = ((BitmapDrawable) editimage.getDrawable());
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();


        if (editsub.length() != 0) {


            boolean inserted = mydb.insertdata(editsub.getText().toString(), editnotes.getText().toString(), imageInByte,latitude,longitude);
            if (inserted == true) {
                //Toast.makeText(add_sub_notes.this, "data inserted", Toast.LENGTH_LONG).show();

                editsub.setText(" ");
                editnotes.setText(" ");
                editimage.setImageBitmap(null);
                addnotes();

            }
               // Toast.makeText(add_sub_notes.this, "data not inserted", Toast.LENGTH_LONG).show();

        }
        else {
            //Toast.makeText(add_sub_notes.this, " subject is empty", Toast.LENGTH_LONG).show();

        }
    }

    public void addnotes(){
        Intent myIntent = new Intent(this, add_sub_notes.class);
        this.startActivity(myIntent);
    }
    public void showdata(MenuItem item) {
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
        //this.finish();



    }
    public void handleInsertData(View view){
        Intent intent = ImagePicker.getPickImageIntent(getApplicationContext());
        startActivityForResult(intent,1);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:                     //Image ID
                try {
                    Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);    //Bitmap data from activity result
                    bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true);         //Scaling image to 500x500 pixels
                    editimage.setImageBitmap(bitmap);                                  //sets image to xml view
                    break;
                }
                catch (Exception e){
                    break;                                                      //in case image is not selected
                }
        }
    }


}


    //menu bar
//    public void showdata(MenuItem i) {
//
//
//                        Intent intent = new Intent(add_sub_notes.this, ViewListcontents.class);
//                        startActivity(intent);
//                    }
//
//
//
//    }


    /*
    //menu bar
    public void onOptionVacuum(MenuItem i) {
        Toast.makeText(this,"pannu",Toast.LENGTH_LONG).show();
    }
    public void onSenderuserName(View view) {

        //EditText userNameEt =(EditText) findViewById(R.id.user_name_edit_text);
        //String username =String.valueOf(userNameEt.getText());
        // Intent goinBack =new Intent();
        // goinBack.putExtra("username",username);
        // setResult(RESULT_OK,goinBack);
        // RESULT_CANCELED
//finish();
    }
    */


