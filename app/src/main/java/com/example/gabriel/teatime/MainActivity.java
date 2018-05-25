package com.example.gabriel.teatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gabriel.teatime.IdlingResource.SimpleIdlingResource;
import com.example.gabriel.teatime.model.Tea;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageDownloader.DelayerCallback {
    public static final String EXTRA_TEA_NAME = "com.example.gabriel.teatime.extra.EXTRA_TEA_NAME";

    @Nullable
    private SimpleIdlingResource mIdlingResource;
    private Intent mIntent;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }

        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.menu_title));

        getIdlingResource();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageDownloader.downloadImage(this, MainActivity.this, mIdlingResource);
    }

    @Override
    public void onDone(ArrayList<Tea> teas) {
        GridView gridView = findViewById(R.id.grid_teas_list);
        gridView.setAdapter(new MenuAdapter(this, R.layout.grid_item_layout, teas));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tea tea = (Tea) parent.getItemAtPosition(position);

                mIntent = new Intent(MainActivity.this, OrderActivity.class);
                mIntent.putExtra(EXTRA_TEA_NAME, tea.getName());

                startActivity(mIntent);
            }
        });
    }
}