package com.example.ahmedel_diasty.mas;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class ManualAttendance extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private RecyclerView recyclerView;
    private ArrayList<String> data;
    private StudentRowAdapter adapter;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Search View
        searchView = (MaterialSearchView)findViewById(R.id.searchview);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                recyclerView.setAdapter(adapter);

            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    ArrayList<String> search = new ArrayList<>();
                    for(String s : data){
                        if(s.contains(newText)||s.toLowerCase().contains(newText))
                            search.add(s);
                    }
                    StudentRowAdapter adapter1 = new StudentRowAdapter(getApplicationContext(),search);
                    recyclerView.setAdapter(adapter1);
                }
                else{
                    recyclerView.setAdapter(adapter);
                }
                return true;
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data = new ArrayList<>();
        adapter = new StudentRowAdapter(getApplicationContext(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        String []name = {"Ahmed","Mohamed","Mahomoud","Ali","Sara","Eman","Amera","Nabil","Aya","Medhat","Esraa","Ebrahim","Adel","Samir","Hamed"};
        for(String s : name) {
            data.add(s);
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        searchView.setMenuItem(menuItem);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}