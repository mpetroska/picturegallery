package com.example.mp.picturegallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urls = getListFromFile();
        if (urls==null) {

            android.os.Process.killProcess(android.os.Process.myPid());

        } else {

            GridView gridView = (GridView) findViewById(R.id.gridView);

            gridView.setAdapter(new ImageAdapter(this));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), FullImageActivity.class);
                    intent.putExtra("id", position);
                    startActivity(intent);
                }
            });
        }
    }

    List<String> getListFromFile() {

        List<String> listOfPicturesUrl= new ArrayList<>();

        try {
            InputStream is = getAssets().open("dog_urls.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, "UTF-8");

            String jsonObject = new JSONObject(jsonString).toString();
            Gson gson = new Gson();
            PicLinks links = gson.fromJson(jsonObject, PicLinks.class);
            listOfPicturesUrl.addAll(Arrays.asList(links.getUrls()));

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
    }
        return listOfPicturesUrl;
    }
}


