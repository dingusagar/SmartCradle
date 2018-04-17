package com.example.dingu.smartcradle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dingu on 6/4/18.
 */

public class ScanListAdapter extends RecyclerView.Adapter<ScanListAdapter.MyViewHolder>{

    ArrayList<Device> devices;
    public ScanListAdapter(ArrayList<Device> devices) {
        this.devices = devices;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scan_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Device device = devices.get(position);
        holder.setDetails(device.getIPAddress(),device.getDeviceName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ipText , deviceNameText;


        MyViewHolder(View itemView) {
            super(itemView);
            ipText = itemView.findViewById(R.id.PrimaryText);
            deviceNameText = itemView.findViewById(R.id.SecondaryText);
        }


        void setDetails(String ipAddress, String deviceName) {
            ipText.setText(ipAddress);
            deviceNameText.setText(deviceName);
        }
    }

    
}
