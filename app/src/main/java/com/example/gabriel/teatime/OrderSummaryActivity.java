package com.example.gabriel.teatime;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class OrderSummaryActivity extends AppCompatActivity {
    private TextView mTvTeaName;
    private TextView mTvQuantity;
    private TextView mTvTeaSize;
    private TextView mTvMilk;
    private TextView mTvSugar;
    private TextView mTvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Toolbar toolbar = findViewById(R.id.order_summary_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.order_summary_title));

        mTvTeaName = findViewById(R.id.tv_summary_tea_name);
        mTvQuantity = findViewById(R.id.tv_summary_quantity);
        mTvTeaSize = findViewById(R.id.tv_summary_tea_size);
        mTvMilk = findViewById(R.id.tv_summary_milk);
        mTvSugar = findViewById(R.id.tv_summary_sugar);
        mTvTotal = findViewById(R.id.tv_summary_total);

        setupDisplaySummary(getIntent());
    }

    public void onSendEmailClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_message));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void setupDisplaySummary(Intent intent) {
        String name = intent.getStringExtra(OrderActivity.EXTRA_TEA_NAME);
        String quantity = intent.getStringExtra(OrderActivity.EXTRA_QUANTITY);
        String size = intent.getStringExtra(OrderActivity.EXTRA_SIZE);
        String milk = intent.getStringExtra(OrderActivity.EXTRA_MILK_TYPE);
        String sugar = intent.getStringExtra(OrderActivity.EXTRA_SUGAR_TYPE);
        String total = intent.getStringExtra(OrderActivity.EXTRA_TOTAL_PRICE);

        mTvTeaName.setText(name);
        mTvQuantity.setText(quantity);
        mTvTeaSize.setText(size);
        mTvMilk.setText(milk);
        mTvSugar.setText(sugar);
        mTvTotal.setText(total);
    }
}
