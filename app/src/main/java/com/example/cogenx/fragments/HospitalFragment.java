package com.example.cogenx.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cogenx.R;
import com.example.cogenx.hospital_data_setup.HospitalAdapter;
import com.example.cogenx.hospital_data_setup.HospitalData;
import com.example.cogenx.ngo_data_setup.NGOAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HospitalFragment extends Fragment {
    RecyclerView recyclerView;
    List<HospitalData> hospitalDataList;
    HospitalAdapter hospitalAdapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hospital, container, false);

        Context context = getContext();
        recyclerView = v.findViewById(R.id.hospitalRecyclerView);
        hospitalDataList = new ArrayList<>();

        hospitalListData(context);

        return v;
    }

    private void hospitalListData(Context context) {
        String URL = "https://diptimanmajumdar.github.io/Hospital/HospitalsStates.json";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            //West Bengal
            try {
                JSONArray hospitalArray = response.getJSONArray("Hospitals West Bengal");
                for (int i = 0; i < hospitalArray.length(); i++) {
                    JSONObject hospitalObj = hospitalArray.getJSONObject(i);

                    HospitalData hospitalData = new HospitalData();
                    hospitalData.setName(hospitalObj.getString("Name"));
                    hospitalData.setAddress(hospitalObj.getString("Address"));
                    hospitalData.setPhoneNumber(hospitalObj.getString("Contact No"));
                    hospitalData.setState("West Bengal");

                    hospitalDataList.add(hospitalData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Bihar
            try {
                JSONArray hospitalArray = response.getJSONArray("Hospitals Bihar");
                for (int i = 0; i < hospitalArray.length(); i++) {
                    JSONObject hospitalObj = hospitalArray.getJSONObject(i);

                    HospitalData hospitalData = new HospitalData();
                    hospitalData.setName(hospitalObj.getString("Name"));
                    hospitalData.setAddress(hospitalObj.getString("Address"));
                    hospitalData.setPhoneNumber(hospitalObj.getString("Contact No"));
                    hospitalData.setState("Bihar");

                    hospitalDataList.add(hospitalData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Assam
            try {
                JSONArray hospitalArray = response.getJSONArray("Hospitals Assam");
                for (int i = 0; i < hospitalArray.length(); i++) {
                    JSONObject hospitalObj = hospitalArray.getJSONObject(i);

                    HospitalData hospitalData = new HospitalData();
                    hospitalData.setName(hospitalObj.getString("Name"));
                    hospitalData.setAddress(hospitalObj.getString("Address"));
                    hospitalData.setPhoneNumber(hospitalObj.getString("Contact No"));
                    hospitalData.setState("Assam");

                    hospitalDataList.add(hospitalData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Delhi
            try {
                JSONArray hospitalArray = response.getJSONArray("Hospitals Delhi");
                for (int i = 0; i < hospitalArray.length(); i++) {
                    JSONObject hospitalObj = hospitalArray.getJSONObject(i);

                    HospitalData hospitalData = new HospitalData();
                    hospitalData.setName(hospitalObj.getString("Name"));
                    hospitalData.setAddress(hospitalObj.getString("Address"));
                    hospitalData.setPhoneNumber(hospitalObj.getString("Contact No"));
                    hospitalData.setState("Delhi");

                    hospitalDataList.add(hospitalData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Mumbai
            try {
                JSONArray hospitalArray = response.getJSONArray("Hospitals Mumbai");
                for (int i = 0; i < hospitalArray.length(); i++) {
                    JSONObject hospitalObj = hospitalArray.getJSONObject(i);

                    HospitalData hospitalData = new HospitalData();
                    hospitalData.setName(hospitalObj.getString("Name"));
                    hospitalData.setAddress(hospitalObj.getString("Address"));
                    hospitalData.setPhoneNumber(hospitalObj.getString("Contact No"));
                    hospitalData.setState("Maharashtra");

                    hospitalDataList.add(hospitalData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Punjab, Uttarakhand, UttarPradesh, Tamilnadu, Karnataka.
            try {
                JSONArray hospitalPunjab = response.getJSONArray("Hospital Punjab");
                JSONArray hospitalUttarakhand = response.getJSONArray("Hospital Uttarakhand");
                JSONArray hospitalUttarPradesh = response.getJSONArray("Hospital UttarPradesh");
                JSONArray hospitalTamilnadu = response.getJSONArray("Hospital Tamilnadu");
                JSONArray hospitalKarnataka = response.getJSONArray("Hospitals Karnataka");

                JSONObject hospitalObj = hospitalPunjab.getJSONObject(0);

                HospitalData hospitalData = new HospitalData();
                hospitalData.setName(hospitalObj.getString("Name"));
                hospitalData.setAddress(hospitalObj.getString("Address"));
                hospitalData.setPhoneNumber(hospitalObj.getString("Contact No"));
                hospitalData.setState("Punjab");

                hospitalDataList.add(hospitalData);

                JSONObject hospitalObj1 = hospitalUttarakhand.getJSONObject(0);
                HospitalData hospitalData1 = new HospitalData();

                hospitalData1.setName(hospitalObj1.getString("Name"));
                hospitalData1.setAddress(hospitalObj1.getString("Address"));
                hospitalData1.setPhoneNumber(hospitalObj1.getString("Contact No"));
                hospitalData1.setState("Uttarakhand");

                hospitalDataList.add(hospitalData1);

                JSONObject hospitalObj2 = hospitalUttarPradesh.getJSONObject(0);
                HospitalData hospitalData2 = new HospitalData();

                hospitalData2.setName(hospitalObj2.getString("Name"));
                hospitalData2.setAddress(hospitalObj2.getString("Address"));
                hospitalData2.setPhoneNumber(hospitalObj2.getString("Contact No"));
                hospitalData2.setState("Uttar Pradesh");

                hospitalDataList.add(hospitalData2);

                JSONObject hospitalObj3 = hospitalTamilnadu.getJSONObject(0);
                HospitalData hospitalData3 = new HospitalData();

                hospitalData3.setName(hospitalObj3.getString("Name"));
                hospitalData3.setAddress(hospitalObj3.getString("Address"));
                hospitalData3.setPhoneNumber(hospitalObj3.getString("Contact No"));
                hospitalData3.setState("Tamilnadu");

                hospitalDataList.add(hospitalData3);

                JSONObject hospitalObj4 = hospitalKarnataka.getJSONObject(0);
                HospitalData hospitalData4 = new HospitalData();

                hospitalData4.setName(hospitalObj4.getString("Name"));
                hospitalData4.setAddress(hospitalObj4.getString("Address"));
                hospitalData4.setPhoneNumber(hospitalObj4.getString("Contact No"));
                hospitalData4.setState("Karnataka");

                hospitalDataList.add(hospitalData4);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            hospitalAdapter=new HospitalAdapter(getContext(),hospitalDataList);
            recyclerView.setAdapter(hospitalAdapter);

        }, error -> Toast.makeText(context, "Failed to get data! No Internet", Toast.LENGTH_LONG).show());
        requestQueue.add(request);
    }
}
