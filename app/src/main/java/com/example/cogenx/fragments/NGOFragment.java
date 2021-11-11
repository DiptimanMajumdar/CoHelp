package com.example.cogenx.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cogenx.R;
import com.example.cogenx.ngo_data_setup.NGOAdapter;
import com.example.cogenx.ngo_data_setup.NGOdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NGOFragment extends Fragment {
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    List<NGOdata> ngOdataList, ngOdataList2, ngOdataList3;
    NGOAdapter ngoAdapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ngo, container, false);

        Context context = getContext();
        recyclerView = v.findViewById(R.id.recyclerView1);
        recyclerView2 = v.findViewById(R.id.recyclerView2);
        recyclerView3 = v.findViewById(R.id.recyclerView3);
        ngOdataList = new ArrayList<>();
        ngOdataList2 = new ArrayList<>();
        ngOdataList3 = new ArrayList<>();

        ngoListData(context);

        return v;
    }

    private void ngoListData(Context context) {
        String URL = "https://diptimanmajumdar.github.io/NGO.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            //data for oxygen concentrators supply NGOs
            try {
                JSONObject dataObj = response.getJSONObject("oxygen concentrators supply");
                JSONArray ngoArray = dataObj.getJSONArray("ngo");
                for (int i = 0; i < ngoArray.length(); i++) {
                    JSONObject ngoObj = ngoArray.getJSONObject(i);

                    NGOdata ngOdata = new NGOdata();
                    ngOdata.setName(ngoObj.getString("name"));
                    ngOdata.setAddress(ngoObj.getString("address"));
                    ngOdata.setPhoneNumber(ngoObj.getString("phoneumber"));
                    ngOdata.setWebsite(ngoObj.getString("website"));

                    Log.d("Correct", "onResponse: " + ngoObj.getString("name"));

                    ngOdataList.add(ngOdata);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ngoAdapter = new NGOAdapter(getContext(), ngOdataList);
            recyclerView.setAdapter(ngoAdapter);

            //data for medical supplies NGOs
            try {
                JSONObject dataObj = response.getJSONObject("medical supplies");
                JSONArray ngoArray = dataObj.getJSONArray("ngo");
                for (int i = 0; i < ngoArray.length(); i++) {
                    JSONObject ngoObj = ngoArray.getJSONObject(i);

                    NGOdata ngOdata = new NGOdata();
                    ngOdata.setName(ngoObj.getString("name"));
                    ngOdata.setAddress(ngoObj.getString("address"));
                    ngOdata.setPhoneNumber(ngoObj.getString("phoneumber"));
                    ngOdata.setWebsite(ngoObj.getString("website"));

                    Log.d("Correct", "onResponse: " + ngoObj.getString("name"));

                    ngOdataList2.add(ngOdata);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
            ngoAdapter = new NGOAdapter(getContext(), ngOdataList2);
            recyclerView2.setAdapter(ngoAdapter);

            //data for food relief and livelihood assistance NGOs
            try {
                JSONObject dataObj = response.getJSONObject("food relief and livelihood assistance");
                JSONArray ngoArray = dataObj.getJSONArray("ngo");
                for (int i = 0; i < ngoArray.length(); i++) {
                    JSONObject ngoObj = ngoArray.getJSONObject(i);

                    NGOdata ngOdata = new NGOdata();
                    ngOdata.setName(ngoObj.getString("name"));
                    ngOdata.setAddress(ngoObj.getString("address"));
                    ngOdata.setPhoneNumber(ngoObj.getString("phoneumber"));
                    ngOdata.setWebsite(ngoObj.getString("website"));

                    Log.d("Correct", "onResponse: " + ngoObj.getString("name"));

                    ngOdataList3.add(ngOdata);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
            ngoAdapter = new NGOAdapter(getContext(), ngOdataList3);
            recyclerView3.setAdapter(ngoAdapter);
        }, error -> Toast.makeText(context, "Failed to get data! No Internet", Toast.LENGTH_LONG).show());
        queue.add(request);
    }
}
