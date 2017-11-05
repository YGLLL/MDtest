package com.example.ygl.mdtest;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ygl.mdtest.model.Tf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.ygl.mdtest.R.attr.colorPrimary;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private List<Tf> tfList=new ArrayList<>();
    private TfAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Tf[] tfs={
            new Tf(R.drawable.a1),
            new Tf(R.drawable.a2),
            new Tf(R.drawable.a3),
            new Tf(R.drawable.a4),
            new Tf(R.drawable.a5),
            new Tf(R.drawable.a6),
            new Tf(R.drawable.a7),
            new Tf(R.drawable.a8),
            new Tf(R.drawable.a9),
            new Tf(R.drawable.a10),
            new Tf(R.drawable.a11),
            new Tf(R.drawable.a12),
            new Tf(R.drawable.a13),
            new Tf(R.drawable.a14),
            new Tf(R.drawable.a15),
            new Tf(R.drawable.a16),
            new Tf(R.drawable.a17),
            new Tf(R.drawable.a18),
            new Tf(R.drawable.a19),
            new Tf(R.drawable.a20),
            new Tf(R.drawable.a21),
            new Tf(R.drawable.a22),
            new Tf(R.drawable.a23),
            new Tf(R.drawable.a24),};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,MusicService.class);
        startService(intent);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                drawerLayout.closeDrawers();
                return true;
            }
        });
        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"hahaha",Snackbar.LENGTH_SHORT)
                        .setAction("lalala", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "12345", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new TfAdapter(tfList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        init();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void init(){
        tfList.clear();
        for (int i=0;i<50;i++){
            Random random=new Random();
            int a=random.nextInt(tfs.length);
            tfList.add(tfs[a]);
        }
    }

    //此函数是专门用于在Bar上添加菜单的
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                Toast.makeText(this, "hahaha", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Intent intent=new Intent(this,MusicService.class);
        stopService(intent);
    }
}
