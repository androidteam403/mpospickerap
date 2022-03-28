package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessMvpView;

public class StatusSpinnerAdapter extends BaseAdapter {
    Activity activity;
    String[] reasonList;
    LayoutInflater inflter;
    private SelectedOrderPickupProcessMvpView mListener;

    public StatusSpinnerAdapter(Activity activity, String[] reasonList, SelectedOrderPickupProcessMvpView mListener) {
        this.activity = activity;
        this.reasonList = reasonList;
        inflter = (LayoutInflater.from(activity));
        this.mListener = mListener;
    }


    @Override
    public int getCount() {
        return reasonList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflter.inflate(R.layout.adapter_status_spinner, null);
        TextView names = view.findViewById(R.id.reason_id);
        names.setText(reasonList[position]);
        if (mListener != null) {
            mListener.statusSpinnerCallback(position);
        }
        return view;
    }
}
