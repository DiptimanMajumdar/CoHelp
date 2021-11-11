package com.example.cogenx.ngo_data_setup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.example.cogenx.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NGOAdapter extends RecyclerView.Adapter<NGOAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<NGOdata> ngOdata;

    public NGOAdapter(Context context, List<NGOdata> ngOdata) {
        this.inflater = LayoutInflater.from(context);
        this.ngOdata = ngOdata;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ngoName,ngoAddress,ngoPhoneNumber,ngoWebsite;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ngoName=itemView.findViewById(R.id.ngoName);
            ngoAddress=itemView.findViewById(R.id.ngoAddress);
            ngoPhoneNumber=itemView.findViewById(R.id.ngoPhoneNumber);
            ngoWebsite=itemView.findViewById(R.id.ngoWebsite);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),"Do Something With this Click", Toast.LENGTH_LONG).show();
//                }
//            });
        }
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.ngo_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NGOAdapter.ViewHolder holder, int position) {
        holder.ngoName.setText(ngOdata.get(position).getName());
        holder.ngoAddress.setText(ngOdata.get(position).getAddress());
        holder.ngoPhoneNumber.setText(ngOdata.get(position).getPhoneNumber());
        holder.ngoWebsite.setText(ngOdata.get(position).getWebsite());
    }

    @Override
    public int getItemCount() {
        return ngOdata.size();
    }
}
