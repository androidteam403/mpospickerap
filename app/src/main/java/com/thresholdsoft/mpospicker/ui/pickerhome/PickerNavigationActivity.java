package com.thresholdsoft.mpospicker.ui.pickerhome;

import android.os.Bundle;
import android.view.Menu;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityNavigation3Binding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsActivity;

import javax.inject.Inject;

public class PickerNavigationActivity extends BaseActivity implements PickerNavigationMvpView {

    @Inject
    PickerNavigationMvpPresenter<PickerNavigationMvpView> mPresenter;
    ActivityNavigation3Binding activityNavigation3Binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNavigation3Binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation3);
        getActivityComponent().inject(this);
        mPresenter.onAttach(PickerNavigationActivity.this);
        setUp();


    }

    @Override
    protected void setUp() {

//        activityNavigation3Binding.setCallback(mPresenter);
        setSupportActionBar(activityNavigation3Binding.appBarMain.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_dashboard)
                .setDrawerLayout(activityNavigation3Binding.drawerLayout)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(activityNavigation3Binding.navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}