package com.stivinsonmartinez.medetour;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SitiosActivity extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    private  ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios);

        viewPager = (ViewPager) findViewById(R.id.pagerSitios);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setText("Laguna").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Mirador").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Jardin Botanico").setTabListener(this);
        actionBar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rumba, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i =new Intent(this,about.class);
            startActivity(i);
            return true;
        }
      if (id == R.id.action_menu) {
            Intent i =new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_laguna) {
            Intent bar = new Intent(this,MapsActivity.class);
            bar.putExtra("latitud",6.2717075);
            bar.putExtra("longitud",-75.5252798);
            bar.putExtra("lugar","Laguna");
            startActivity(bar);
            return true;
        }
        if (id == R.id.action_mirador) {
            Intent bar = new Intent(this,MapsActivity.class);
            bar.putExtra("latitud",6.2900909);
            bar.putExtra("longitud",-75.5551593);
            bar.putExtra("lugar","Mirador");
            startActivity(bar);
            return true;
        }
        if (id == R.id.action_jardin) {
            Intent bar = new Intent(this,MapsActivity.class);
            bar.putExtra("latitud",6.2710285);
            bar.putExtra("longitud",-75.5644309);
            bar.putExtra("lugar","Jardin Botanico");
            startActivity(bar);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    public class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new Laguna();
                case 1:
                    return new Mirador();
                case 2:
                    return new Jardin();
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}
