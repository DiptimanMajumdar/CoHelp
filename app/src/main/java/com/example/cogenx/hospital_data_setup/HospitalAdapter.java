package com.example.cogenx.hospital_data_setup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cogenx.R;
import com.example.cogenx.ngo_data_setup.NGOAdapter;
import com.example.cogenx.ngo_data_setup.NGOdata;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<HospitalData> hospitalData;

    public HospitalAdapter(Context context, List<HospitalData> hospitalData) {
        this.inflater = LayoutInflater.from(context);
        this.hospitalData = hospitalData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView hospitalName,hospitalAddress,hospitalPhoneNumber,hospitalState;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            hospitalName=itemView.findViewById(R.id.hospitalName);
            hospitalAddress=itemView.findViewById(R.id.hospitalAddress);
            hospitalPhoneNumber=itemView.findViewById(R.id.hospitalContact);
            hospitalState=itemView.findViewById(R.id.hospitalState);

        }
    }

    @NonNull
    @NotNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.hospital_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HospitalAdapter.ViewHolder holder, int position) {
        holder.hospitalName.setText(hospitalData.get(position).getName());
        holder.hospitalAddress.setText(hospitalData.get(position).getAddress());
        holder.hospitalPhoneNumber.setText(hospitalData.get(position).getPhoneNumber());
        holder.hospitalState.setText(hospitalData.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return hospitalData.size();
    }
}
