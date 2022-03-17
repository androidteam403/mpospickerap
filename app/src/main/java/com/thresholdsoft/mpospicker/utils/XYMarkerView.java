
package com.thresholdsoft.mpospicker.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.CustomMarkerViewBinding;

import java.text.DecimalFormat;


/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class XYMarkerView extends MarkerView {

    // private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;
    private CustomMarkerViewBinding customMarkerViewBinding;

    private DecimalFormat format;

    public XYMarkerView(Context context, IAxisValueFormatter xAxisValueFormatter) {
        super(context, R.layout.custom_marker_view);
        customMarkerViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_marker_view, null, false);
//        ButterKnife.bind(this);
        this.xAxisValueFormatter = xAxisValueFormatter;
        format = new DecimalFormat("###.0");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
//        if (e instanceof CandleEntry) {
//            CandleEntry ce = (CandleEntry) e;
//            Log.e("Marker", "Values : " + Utils.formatNumber(ce.getHigh(), 0, true) + ", High value: " + ce.getHigh());
//            tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
//        } else {
//            Log.e("Marker", "Values of Y : " + Utils.formatNumber(e.getY(), 0, true) + ", High value: " + e.getY());
//            tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true));
//        }
        customMarkerViewBinding.tvContent.setText(CommonUtils.convertSpannableStrings("Total Orders:",
                "" + (int) e.getY(), 1.0f, 1.1f, R.color.black, R.color.yellow));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
