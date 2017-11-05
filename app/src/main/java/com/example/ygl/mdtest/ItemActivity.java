package com.example.ygl.mdtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by YGL on 2017/3/16.
 */

public class ItemActivity extends AppCompatActivity {
    public static final String name="1";
    public static final String imageid="2";

    @Override
    protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.item_activity_layout);

        Intent intent=getIntent();
        String string=intent.getStringExtra(name);
        int i=intent.getIntExtra(imageid,-1);

        Toolbar toolbar=(Toolbar) findViewById(R.id.itemToolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_layout);
        ImageView imageView=(ImageView)findViewById(R.id.toolbar_image);
        TextView textView=(TextView)findViewById(R.id.tfString);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(string);
        Glide.with(this).load(i).into(imageView);
        String broing=todobroing(string);
        textView.setText(broing);
    }
    private String todobroing(String string){
        String string1="";
        for(int i=0;i<1000;i++){
            string1=string1+string;
        }
        return string1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
