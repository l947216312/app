package com.example.slothapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    //private Button first;
    //private Button sec;
    //private Button third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.user);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(MenuItem item){
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        findViewById(R.id.four).setOnClickListener(new View.OnClickListener(){
           //跳转到登录页面
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,LogActivity.class));
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    /* first.setOnTouchListener(new Button.OnTouchListener(){

        public boolean onTouch(View v, MotionEvent event)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                v.setBackgroundResource(R.drawable.btn_down);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {v.setBackgroundResource(R.drawable.btn_up);
            }
            return false;
        }
    });*/

    }

