package com.stivinsonmartinez.medetour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adaptador;
    ListView listaMenu;

    private Lista_Menu[] datosmenu = new Lista_Menu[]{

            new Lista_Menu("Cine"),new Lista_Menu("Teatro")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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

        return super.onOptionsItemSelected(item);
    }

    public void Cines (View v){
        Intent cines = new Intent(this,Cines.class);
        startActivity(cines);
    }
    public void Teatros (View v){
        Intent teatro = new Intent(this,Teatros.class);
        startActivity(teatro);
    }

    public void Restaurantes (View v){
        Intent rest = new Intent(this,RestaurantesActivity.class);
        startActivity(rest);
    }

    public void Rumba (View v){
        Intent rumb = new Intent(this,RumbaActivity.class);
        startActivity(rumb);
    }

    public void Sitios (View v){
        Intent sitio = new Intent(this,SitiosActivity.class);
        startActivity(sitio);
    }
}
