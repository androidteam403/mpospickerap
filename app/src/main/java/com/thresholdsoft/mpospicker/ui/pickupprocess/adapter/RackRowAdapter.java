package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterRackRowBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public class RackRowAdapter extends RecyclerView.Adapter<RackRowAdapter.ViewHolder> {
    private Context context;
    private List<RackAdapter.RackBoxModel> rackRowModelList;

    public RackRowAdapter(Context context, List<RackAdapter.RackBoxModel> rackRowModelList) {
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
        RackAdapter.RackBoxModel rackRowModel = rackRowModelList.get(position);
        holder.rackRowBinding.rackBoxId.setText(rackRowModel.getRackBoxId());
        holder.rackRowBinding.rackProCount.setText(String.valueOf(rackRowModel.getProductsCuont()));
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
}
