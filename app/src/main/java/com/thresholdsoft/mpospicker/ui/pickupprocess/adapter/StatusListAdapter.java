package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterOrderBinding;
import com.thresholdsoft.mpospicker.databinding.AdapterStatusListBinding;

import java.util.List;

public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.ViewHolder> {
    private Context mContext;
    private List<String> statusList;
    private StatusListAdapterCallback mListener;
    private AdapterOrderBinding adapterOrderBinding;
    private BottomSheetDialog itemStatusDropdownDialog;

    public StatusListAdapter(Context mContext, List<String> statusList, StatusListAdapterCallback mListener, AdapterOrderBinding adapterOrderBinding, BottomSheetDialog itemStatusDropdownDialog) {
        this.mContext = mContext;
        this.statusList = statusList;
        this.mListener = mListener;
        this.adapterOrderBinding = adapterOrderBinding;
        this.itemStatusDropdownDialog = itemStatusDropdownDialog;
    }

    @NonNull
    @Override
    public StatusListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterStatusListBinding adapterStatusListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.adapter_status_list, parent, false);
        return new StatusListAdapter.ViewHolder(adapterStatusListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusListAdapter.ViewHolder holder, int position) {
        String status = statusList.get(position);
        holder.adapterStatusListBinding.setStatus(status);
        holder.itemView.setOnClickListener(view -> {
            if (mListener != null)
                mListener.onClickStatusListItem(status, adapterOrderBinding, itemStatusDropdownDialog);
        });
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterStatusListBinding adapterStatusListBinding;

        public ViewHolder(@NonNull AdapterStatusListBinding adapterStatusListBinding) {
            super(adapterStatusListBinding.getRoot());
            this.adapterStatusListBinding = adapterStatusListBinding;
        }
    }

    interface StatusListAdapterCallback {
        void onClickStatusListItem(String status, AdapterOrderBinding adapterOrderBinding, BottomSheetDialog itemStatusDropdownDialog);
    }
}
