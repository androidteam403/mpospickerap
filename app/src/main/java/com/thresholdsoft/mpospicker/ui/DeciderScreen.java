package com.thresholdsoft.mpospicker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.DeciderScreenBinding;
import com.thresholdsoft.mpospicker.ui.loginScreenActivity.DeciderScreen2;

public class DeciderScreen extends AppCompatActivity {
    DeciderScreenBinding deciderScreenBinding;

    public static Intent getStartActivity(Context context) {
        return new Intent(context, DeciderScreen.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deciderScreenBinding = DataBindingUtil.setContentView(this, R.layout.decider_screen);
        callBacks();
    }

    private void callBacks() {
        deciderScreenBinding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeciderScreen.this, DeciderScreen2.class);
                startActivity(i);
            }
        });

    }
}
