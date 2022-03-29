package com.thresholdsoft.mpospicker.ui.batchlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterBatchlistBinding;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListActivity;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListMvpView;

import java.util.List;

public class BatchListAdapter extends RecyclerView.Adapter<BatchListAdapter.ViewHolder> {
    private Context mContext;
    private List<BatchListActivity.BatchListModel> batchListModelList;
    private BatchListMvpView mvpView;

    public BatchListAdapter(Context mContext, List<BatchListActivity.BatchListModel> batchListModelList, BatchListMvpView mvpView) {
        this.mContext = mContext;
        this.batchListModelList = batchListModelList;
        this.mvpView = mvpView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterBatchlistBinding adapterBatchlistBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.adapter_batchlist, parent, false);
        return new BatchListAdapter.ViewHolder(adapterBatchlistBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BatchListActivity.BatchListModel batchListModel = batchListModelList.get(position);
        holder.adapterBatchlistBinding.batchId.setText(batchListModel.getBatchId());
        if (batchListModel.isBatchidSelect())
            Glide.with(mContext).load(R.drawable.ic_circle_tick).into(holder.adapterBatchlistBinding.batchIdSelectIcon);
        else
            Glide.with(mContext).load(R.drawable.ic_circle_stroke).into(holder.adapterBatchlistBinding.batchIdSelectIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mvpView != null)
                    mvpView.onClickItem(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return batchListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterBatchlistBinding adapterBatchlistBinding;

        public ViewHolder(@NonNull AdapterBatchlistBinding adapterBatchlistBinding) {
            super(adapterBatchlistBinding.getRoot());
            this.adapterBatchlistBinding = adapterBatchlistBinding;
        }
    }
}
