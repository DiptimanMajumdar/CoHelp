package com.example.cogenx.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cogenx.R;
import com.example.cogenx.swipeView.CustomSwipeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private TextView indiaCases, indiaRecovered, indiaDeaths, indiaActiveCases;
    private TextView stateName, stateCases, stateRecovered, stateDeaths;
    private Spinner spinner;

    private ViewPager viewPager;
    private CustomSwipeAdapter adapter;
    private Timer timer;
    private int current_position = 0;
    private LinearLayout dotsLayout;
    private int custom_position = 0;

    @Nullable
    @org.jetbrains.annotations.Nullable
//    @Override


    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //id for live cases data for India
        indiaCases = v.findViewById(R.id.IndiaCases);
        indiaRecovered = v.findViewById(R.id.IndiaRecovered);
        indiaDeaths = v.findViewById(R.id.IndiaDeaths);
        indiaActiveCases = v.findViewById(R.id.IndiaActiveCases);

        //id for live cases data for state
        stateName = v.findViewById(R.id.stateName);
        stateCases = v.findViewById(R.id.StateCases);
        stateRecovered = v.findViewById(R.id.StateRecovered);
        stateDeaths = v.findViewById(R.id.StateDeaths);

        spinner = v.findViewById(R.id.spinner);

        Context mContext = getContext();

        dotsLayout = v.findViewById(R.id.dotsContainer);
        viewPager = v.findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(mContext);
        viewPager.setAdapter(adapter);
        prepareDots(custom_position++, mContext);
        createSlide();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (custom_position > 5)
                    custom_position = 0;
                prepareDots(custom_position++, mContext);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //for getting to start the website of CoHelp through the provided link
        TextView linkText=v.findViewById(R.id.linkText);
        linkText.setMovementMethod(LinkMovementMethod.getInstance());

        //for getting live data for cases in India
        getIndiaRecord(mContext);

        sliderAdd(mContext);

        return v;
    }

    private void createSlide() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == Integer.MAX_VALUE)
                    current_position = 0;
                viewPager.setCurrentItem(current_position++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 250, 3500);
    }

    private void prepareDots(int currentSlidePosition, Context mContext) {
        if (dotsLayout.getChildCount() > 0)
            dotsLayout.removeAllViews();

        ImageView[] dots = new ImageView[6];

        for (int i = 0; i < 6; i++) {
            dots[i] = new ImageView(mContext);
            if (i == currentSlidePosition)
                dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));
            else
                dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.inactive_dot));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 10, 0);
            dotsLayout.addView(dots[i], layoutParams);
        }
    }

    //JSON parsed live data of covid in India
    private void getIndiaRecord(Context mContext) {
        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject dataObj = response.getJSONObject("data");

                //json array taken separately for active cases
                JSONArray jsonArray = dataObj.getJSONArray("unofficial-summary");

                //json obj for cases, recovered and death
                JSONObject summary = dataObj.getJSONObject("summary");
                String cases = summary.getString("confirmedCasesIndian");
                String recovered = summary.getString("discharged");
                String deaths = summary.getString("deaths");

                //active cases data
                String active = "";
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    active = jsonObject.optString("active");
                }

                //Log.d("diptiman", "onResponse: " + active);

                indiaCases.setText(cases);
                indiaRecovered.setText(recovered);
                indiaDeaths.setText(deaths);
                indiaActiveCases.setText(active);
            } catch (JSONException e) {
                e.printStackTrace();
                //Log.d("diptiman", "onResponse: "+e);
            }
        }, error -> {
            Toast.makeText(mContext, "Failed to get data! No Internet", Toast.LENGTH_LONG).show();
            // Log.d("Cases", "No data found");
        });
        queue.add(request);
    }

    //function to set slider
    public void sliderAdd(Context mContext) {
        String[] stateNames = {"Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Daman and Diu", "Delhi"
                , "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Ladakh", "Lakshadweep", "Madhya Pradesh"
                , "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Puducherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand"
                , "Uttar Pradesh", "West Bengal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                R.layout.my_selected_item, stateNames);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String states = (String) parent.getSelectedItem();
                int index = Arrays.binarySearch(stateNames, states);
                stateData(index, mContext);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String states = (String) parent.getItemAtPosition(0);
            }
        });
    }

    //function to set data for states
    public void stateData(int index, Context mContext) {
        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject dataObj = response.getJSONObject("data");
                JSONArray regionalArray = dataObj.getJSONArray("regional");
                for (int i = 0; i < regionalArray.length(); i++) {
                    if (index == i) {
                        JSONObject regionalObj = regionalArray.getJSONObject(i);
                        String name = regionalObj.getString("loc");
                        String cases = regionalObj.getString("confirmedCasesIndian");
                        String recovered = regionalObj.getString("discharged");
                        String deaths = regionalObj.getString("deaths");

                        stateName.setText(name);
                        stateCases.setText(cases);
                        stateRecovered.setText(recovered);
                        stateDeaths.setText(deaths);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(mContext, "Failed to get data! No Internet", Toast.LENGTH_LONG).show();
        });
        queue.add(request);
    }

}
