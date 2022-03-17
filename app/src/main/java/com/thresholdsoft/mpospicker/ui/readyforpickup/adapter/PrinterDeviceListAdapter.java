package com.thresholdsoft.mpospicker.ui.readyforpickup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPrinterDevicesListBinding;

public class PrinterDeviceListAdapter extends RecyclerView.Adapter<PrinterDeviceListAdapter.ViewHolder> {
    private Context mContext;
    private String[] printerDeviceList;

    public PrinterDeviceListAdapter(Context mContext, String[] printerDeviceList) {
        this.mContext = mContext;
        this.printerDeviceList = printerDeviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterPrinterDevicesListBinding printerDevicesListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.adapter_printer_devices_list, parent, false);
        return new ViewHolder(printerDevicesListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = printerDeviceList[position];
        holder.printerDevicesListBinding.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return printerDeviceList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterPrinterDevicesListBinding printerDevicesListBinding;

        public ViewHolder(@NonNull AdapterPrinterDevicesListBinding printerDevicesListBinding) {
            super(printerDevicesListBinding.getRoot());
            this.printerDevicesListBinding = printerDevicesListBinding;
        }
    }
}
