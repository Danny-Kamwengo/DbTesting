package com.example.dbtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String path;
    AdapterView categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);

        path = Routes.GetEmployees;

        List<employees> categoryModels = new ArrayList<>();

        loadEmplyees(categoryModels);
//        loadSample(categoryModels);

    }

    private void loadSample(List<employees> categoryModels) {
        for (int i = 0; i < 10; i++) {
            categoryModels.add(new employees(
                    i,
                   "Name sample"+i,
                   ""
                   ,
                   ""
            ));
            categoryAdapter = new AdapterView(MainActivity.this, categoryModels);

            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            recyclerView.setAdapter(categoryAdapter);
        }
    }

    private void loadEmplyees(List<employees> categoryModels) {
        class DBQuery extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, path,
                        response -> {
                            try {
                                JSONObject obj = new JSONObject(response);

//                                JSONArray array = obj.getJSONArray("results");
//                                for (int i = 0; i < array.length(); i++) {
//                                    JSONObject results = array.getJSONObject(i);
//
//                                    categoryModels.add(new employees(
//                                            results.getInt("ID"),
//                                            results.getString("Name"),
//                                            results.getString("Designation"),
//                                            results.getString("Location")
//                                    ));
//                                }

                                categoryModels.add(new employees(
                                        obj.getInt("ID"),
                                        obj.getString("Name"),
                                        obj.getString("Designation"),
                                        obj.getString("Location")
                                ));

                                categoryAdapter = new AdapterView(MainActivity.this, categoryModels);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                                recyclerView.setAdapter(categoryAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show());
                Volley.newRequestQueue(MainActivity.this).add(stringRequest);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }
        }
        DBQuery ul = new DBQuery();
        ul.execute();
    }

}