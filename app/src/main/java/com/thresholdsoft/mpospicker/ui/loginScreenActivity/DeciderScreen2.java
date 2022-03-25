package com.thresholdsoft.mpospicker.ui.loginScreenActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityDeciderScreen2Binding;
import com.thresholdsoft.mpospicker.ui.navigationActivity3.NavigationActivity3;

public class DeciderScreen2 extends AppCompatActivity {
    ActivityDeciderScreen2Binding activityDeciderScreen2Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDeciderScreen2Binding = DataBindingUtil.setContentView(this, R.layout.activity_decider_screen2);
        callBacks();

    }

    private void callBacks(){
        activityDeciderScreen2Binding.packerUnselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityDeciderScreen2Binding.packerUnselected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.packerSelected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.billerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.billerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.sealerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.sealerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.adminSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.adminUnselected.setVisibility(View.VISIBLE);


            }
        });

        activityDeciderScreen2Binding.billerUnselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityDeciderScreen2Binding.billerUnselected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.billerSelected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.packerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.packerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.sealerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.sealerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.adminSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.adminUnselected.setVisibility(View.VISIBLE);
            }
        });

        activityDeciderScreen2Binding.sealerUnselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityDeciderScreen2Binding.sealerUnselected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.sealerSelected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.packerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.packerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.billerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.billerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.adminSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.adminUnselected.setVisibility(View.VISIBLE);

            }
        });

        activityDeciderScreen2Binding.adminUnselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityDeciderScreen2Binding.adminUnselected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.adminSelected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.packerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.packerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.billerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.billerUnselected.setVisibility(View.VISIBLE);
                activityDeciderScreen2Binding.sealerSelected.setVisibility(View.GONE);
                activityDeciderScreen2Binding.sealerUnselected.setVisibility(View.VISIBLE);
            }
        });

        activityDeciderScreen2Binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeciderScreen2.this, NavigationActivity3.class);
                startActivity(i);
            }
        });
    }
    }



