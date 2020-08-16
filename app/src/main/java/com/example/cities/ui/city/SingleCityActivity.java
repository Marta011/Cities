package com.example.cities.ui.city;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cities.R;
import com.example.cities.db.database.DatabaseClient;
import com.example.cities.db.entity.City;
import com.example.cities.db.entity.Location;
import com.example.cities.db.entity.Population;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class SingleCityActivity extends AppCompatActivity {
    TextView textViewName, textViewCountry, textViewProvince, textViewLicensePlate, textViewCityLaw,
            textViewCreationDate, textViewDensity, textViewInhabitantsNumber, textViewLatitude, textViewLongitude,
            textViewPopulation, textViewLocation;
    Button btnSendSms;
    ImageView imageView;
    int cityId;
    City city;
    final private int REQUEST_INTERNET = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_city);
        attributeWidgets();
        getValueFromIntent();
        viewSingleCityActivity();
        setUpRollingPopulationTextView();
        setUpRollingLocationTextView();
    }

    private void getValueFromIntent() {
        Intent myIntent = getIntent();
        cityId = Integer.parseInt(myIntent.getStringExtra("cityId"));
        Log.e("xxx", "cityId2 " + cityId);
    }

    private void setUpRollingPopulationTextView() {
        textViewPopulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewInhabitantsNumber.getVisibility() == View.GONE) {
                    textViewInhabitantsNumber.setVisibility(View.VISIBLE);
                    textViewDensity.setVisibility(View.VISIBLE);
                } else {
                    textViewInhabitantsNumber.setVisibility(View.GONE);
                    textViewDensity.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setUpRollingLocationTextView() {
        textViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewLatitude.getVisibility() == View.GONE) {
                    textViewLatitude.setVisibility(View.VISIBLE);
                    textViewLongitude.setVisibility(View.VISIBLE);
                } else {
                    textViewLatitude.setVisibility(View.GONE);
                    textViewLongitude.setVisibility(View.GONE);
                }
            }
        });
    }

    private void attributeWidgets() {
        textViewName = findViewById(R.id.textView_name);
        textViewCountry = findViewById(R.id.textView_country);
        textViewProvince = findViewById(R.id.textView_province);
        textViewLicensePlate = findViewById(R.id.textView_licensePlate);
        textViewCityLaw = findViewById(R.id.textView_cityLaw);
        textViewCreationDate = findViewById(R.id.textView_creationDate);
        textViewDensity = findViewById(R.id.textView_density);
        textViewInhabitantsNumber = findViewById(R.id.textView_inhabitantsNumber);
        textViewLatitude = findViewById(R.id.textView_laitude);
        textViewLongitude = findViewById(R.id.textView_longitude);
        textViewPopulation = findViewById(R.id.textView_population);
        textViewLocation = findViewById(R.id.textView_location);
        btnSendSms = findViewById(R.id.btn_sendElementViaSMS);
        imageView = findViewById(R.id.imageView_image);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_INTERNET:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new DownloadImageTask().execute(city.getImageUrl());
                } else {
                    Toast.makeText(SingleCityActivity.this, "Permission Danied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an Http connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            Log.e("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connectiong");
        }
        return in;
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(url);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e1) {
            Log.e("Networking Activity", e1.getLocalizedMessage());
        }
        return bitmap;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            return downloadImage(strings[0]);
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    private void viewSingleCityActivity() {
        class ViewSingleCity extends AsyncTask<Void, Void, City> {
            Location location;
            Population population;

            @Override
            protected City doInBackground(Void... voids) {
                location = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .locationDao()
                        .findLocationById(cityId);
                population = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .populationDao()
                        .findPopulationById(cityId);
                return DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .cityDao()
                        .findCityById(cityId);
            }

            @Override
            protected void onPostExecute(City city) {
                super.onPostExecute(city);
                fillWidgetsByValues(city);
                displayImageFromUrl(city);
            }

            private void fillWidgetsByValues(City city) {
                textViewName.setText(city.getName());
                textViewCountry.setText(city.getCountry());
                textViewProvince.setText(city.getProvince());
                textViewLicensePlate.setText(city.getLicensePlate());
                textViewCityLaw.setText(city.getCityLaw());
                textViewCreationDate.setText(city.getCreationDate());
                textViewDensity.setText("Gęstość: " + population.getDensity());
                textViewInhabitantsNumber.setText("Ilość mieszkańców: " + population.getInhabitantsNumber());
                textViewLatitude.setText("Szerokość: " + location.getLatitude());
                textViewLongitude.setText("Wysokość: " + location.getLongitude());
            }

            private void displayImageFromUrl(City city) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(SingleCityActivity.this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET);
                else
                    new DownloadImageTask().execute(city.getImageUrl());
            }
        }
        new ViewSingleCity().execute();
    }
}