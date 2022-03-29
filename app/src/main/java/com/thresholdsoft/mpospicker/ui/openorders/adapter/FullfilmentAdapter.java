package com.thresholdsoft.mpospicker.ui.openorders.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterFullfilmentBinding;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.io.Serializable;
import java.util.List;

public class FullfilmentAdapter extends RecyclerView.Adapter<FullfilmentAdapter.ViewHolder> {
    private final Context context;
    private final List<FullfilmentModel> fullfilmentModelList;
    private final OpenOrdersMvpView mvpView;
    List<RackAdapter.RackBoxModel.ProductData> listOfList;
    private RacksDataResponse racksDataResponse;
    private boolean firstAccessCheck;

    public FullfilmentAdapter(Context context, List<FullfilmentModel> fullfilmentModelList, OpenOrdersMvpView mvpView,   List<RackAdapter.RackBoxModel.ProductData> listOfList, RacksDataResponse racksDataResponse) {
        this.context = context;
        this.fullfilmentModelList = fullfilmentModelList;
        this.mvpView = mvpView;
        this.listOfList=listOfList;
        this.racksDataResponse=racksDataResponse;
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
        holder.fullfilmentBinding.productItems.setText(""+ fullfilmentModel.totalItems);
        switch (fullfilmentModel.getExpandStatus()) {

            case 0:
//                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.lite_grey));
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            case 1:
//                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.fullfilmentBinding.orderChildLayout.setVisibility(View.VISIBLE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.yellow_stroke_bg));
                break;
            case 3:
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;

            case 4:

                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            case 5:
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            case 6:
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            case 7:
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            case 8:
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            case 9:
                holder.fullfilmentBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.fullfilmentBinding.rackChild2Layout.setBackground(null);
                break;
            default:
        }




        if (fullfilmentModel.isSelected) {
            holder.fullfilmentBinding.fullfillmentSelectIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
//            holder.fullfilmentBinding.fullfillmentParentLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
        } else {
            holder.fullfilmentBinding.fullfillmentSelectIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_stroke));
//            holder.fullfilmentBinding.fullfillmentParentLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));

        }


        FulfilmentDetailsAdapter productListAdapter=new FulfilmentDetailsAdapter(context, racksDataResponse.getFullfillmentDetails().get(position).getProducts(),mvpView, position);
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.fullfilmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.fullfilmentBinding.recyclerView.setAdapter(productListAdapter);
        holder.itemView.setOnClickListener(v -> {
            if (mvpView != null)
                mvpView.onFullfillmentItemClick(position);

        });


        holder.fullfilmentBinding.fullfillmentSelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(mvpView!=null)
                    mvpView.onRightArrowClickedContinue(position);
            }
        });



        holder.fullfilmentBinding.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstAccessCheck = true;
//                holder.fullfilmentBinding.rightArrow.setVisibility(View.GONE);

                if (fullfilmentModel.getExpandStatus()==0){
                    fullfilmentModel.setExpandStatus(1);
                    holder.fullfilmentBinding.rightArrow.setVisibility(View.GONE);
                    holder.fullfilmentBinding.downArrow.setVisibility(View.VISIBLE);

                    notifyDataSetChanged();


                }

            }
        });

        holder.fullfilmentBinding.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullfilmentModel.getExpandStatus()==1){
                    fullfilmentModel.setExpandStatus(0);
                    holder.fullfilmentBinding.downArrow.setVisibility(View.GONE);
                    holder.fullfilmentBinding.rightArrow.setVisibility(View.VISIBLE);

                    notifyDataSetChanged();

                }
            }
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

    public static class FullfilmentModel implements Serializable {
        private String fullfilmentId;
        private String totalItems;
        private boolean isSelected;
        private int expandStatus = 0;

        public int getExpandStatus() {
            return expandStatus;
        }

        public void setExpandStatus(int expandStatus) {
            this.expandStatus = expandStatus;
        }

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
