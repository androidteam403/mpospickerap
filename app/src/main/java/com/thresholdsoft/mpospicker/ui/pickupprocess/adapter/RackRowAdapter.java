package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterRackRowBinding;

import java.util.List;

public class RackRowAdapter extends RecyclerView.Adapter<RackRowAdapter.ViewHolder> {
    private Context context;
    private List<RackRowModel> rackRowModelList;

    public RackRowAdapter(Context context, List<RackRowModel> rackRowModelList) {
        this.context = context;
        this.rackRowModelList = rackRowModelList;
    }

    @NonNull
    @Override
    public RackRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRackRowBinding rackRowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_rack_row, parent, false);
        return new RackRowAdapter.ViewHolder(rackRowBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull RackRowAdapter.ViewHolder holder, int position) {
        RackRowModel rackRowModel = rackRowModelList.get(position);
        holder.rackRowBinding.rackRowId.setText(rackRowModel.getRackRowNo());
        holder.rackRowBinding.rackRowNo.setText(rackRowModel.getRackRowId());
    }

    @Override
    public int getItemCount() {
        return rackRowModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterRackRowBinding rackRowBinding;

        public ViewHolder(@NonNull AdapterRackRowBinding rackRowBinding) {
            super(rackRowBinding.getRoot());
            this.rackRowBinding = rackRowBinding;
        }
    }

    public static class RackRowModel {
        private String rackRowId;
        private String rackRowNo;

        public String getRackRowId() {
            return rackRowId;
        }

        public void setRackRowId(String rackRowId) {
            this.rackRowId = rackRowId;
        }

        public String getRackRowNo() {
            return rackRowNo;
        }

        public void setRackRowNo(String rackRowNo) {
            this.rackRowNo = rackRowNo;
        }
    }
}
