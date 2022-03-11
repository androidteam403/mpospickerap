package com.thresholdsoft.mpospicker.ui.openorders.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterFullfilmentBinding;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;

import java.util.List;

public class FullfilmentAdapter extends RecyclerView.Adapter<FullfilmentAdapter.ViewHolder> {
    private final Context context;
    private final List<FullfilmentModel> fullfilmentModelList;
    private final OpenOrdersMvpView mvpView;

    public FullfilmentAdapter(Context context, List<FullfilmentModel> fullfilmentModelList, OpenOrdersMvpView mvpView) {
        this.context = context;
        this.fullfilmentModelList = fullfilmentModelList;
        this.mvpView = mvpView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterFullfilmentBinding fullfilmentBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_fullfilment, parent, false);
        return new ViewHolder(fullfilmentBinding);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FullfilmentModel fullfilmentModel = fullfilmentModelList.get(position);
        holder.fullfilmentBinding.fullfilmentId.setText(context.getResources().getString(R.string.label_space) + fullfilmentModel.getFullfilmentId());
        holder.fullfilmentBinding.items.setText(context.getResources().getString(R.string.label_space) + fullfilmentModel.getTotalItems());
        if (fullfilmentModel.isSelected) {
            holder.fullfilmentBinding.fullfillmentSelectIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
            holder.fullfilmentBinding.fullfillmentParentLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
        } else {
            holder.fullfilmentBinding.fullfillmentSelectIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_stroke));
            holder.fullfilmentBinding.fullfillmentParentLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));

        }
        holder.itemView.setOnClickListener(v -> {
            if (mvpView != null)
                mvpView.onFullfillmentItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return fullfilmentModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterFullfilmentBinding fullfilmentBinding;

        public ViewHolder(@NonNull AdapterFullfilmentBinding fullfilmentBinding) {
            super(fullfilmentBinding.getRoot());
            this.fullfilmentBinding = fullfilmentBinding;
        }
    }

    public static class FullfilmentModel {
        private String fullfilmentId;
        private String totalItems;
        private boolean isSelected;

        public String getFullfilmentId() {
            return fullfilmentId;
        }

        public void setFullfilmentId(String fullfilmentId) {
            this.fullfilmentId = fullfilmentId;
        }

        public String getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(String totalItems) {
            this.totalItems = totalItems;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
