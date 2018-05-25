package com.example.gabriel.teatime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class OrderActivity extends AppCompatActivity {
    public static final String EXTRA_TOTAL_PRICE = "com.example.gabriel.teatime.extra.EXTRA_TOTAL_PRICE";
    public static final String EXTRA_TEA_NAME = "com.example.gabriel.teatime.extra.EXTRA_TEA_NAME";
    public static final String EXTRA_SIZE = "com.example.gabriel.teatime.extra.EXTRA_SIZE";
    public static final String EXTRA_MILK_TYPE = "com.example.gabriel.teatime.extra.EXTRA_MILK_TYPE";
    public static final String EXTRA_SUGAR_TYPE = "com.example.gabriel.teatime.extra.EXTRA_SUGAR_TYPE";
    public static final String EXTRA_QUANTITY = "com.example.gabriel.teatime.extra.EXTRA_QUANTITY";

    private static final int SMALL_PRICE = 5;
    private static final int MEDIUM_PRICE = 7;
    private static final int LARGE_PRICE = 10;
    private static final String TEA_SIZE_SMALL = "Small ($5/cup)";
    private static final String TEA_SIZE_MEDIUM = "Medium ($7/cup)";
    private static final String TEA_SIZE_LARGE = "Large ($10/cup)";

    private TextView mTvTeaCost;
    private TextView mTvQuantity;
    private Spinner mSpinnerSize;
    private Spinner mSpinnerMilk;
    private Spinner mSpinnerSugar;
    private Button mBtnBrewTea;

    private String mExtraTeaName;
    private String mMilkType;
    private String mSugarType;
    private String mSize;
    private int mQuantity = 0;
    private int mTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mExtraTeaName = getIntent().getStringExtra(MainActivity.EXTRA_TEA_NAME);

        Toolbar toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mExtraTeaName);

        mTvTeaCost = findViewById(R.id.tv_total);
        mTvQuantity = findViewById(R.id.tv_quantity);
        mSpinnerSize = findViewById(R.id.spinner_tea_size);
        mSpinnerMilk = findViewById(R.id.spinner_milk);
        mSpinnerSugar = findViewById(R.id.spinner_sugar);
        mBtnBrewTea = findViewById(R.id.btn_brew_tea);

        mTvTeaCost.setText(getString(R.string.initial_cost));

        setupSizeSpinner();
        setupMilkSpinner();
        setupSugarSpinner();
    }

    public void onIncrementClick(View view) {
        mQuantity++;
        mTvQuantity.setText(String.valueOf(mQuantity));
        mTvTeaCost.setText(calculatePrice());
        mBtnBrewTea.setEnabled(mQuantity > 0);
    }

    public void onDecrementClick(View view) {
        if (mQuantity > 0) {
            mQuantity--;
            mTvQuantity.setText(String.valueOf(mQuantity));
            mTvTeaCost.setText(calculatePrice());
        }
    }

    public void onBrewTeaClick(View view) {
        Intent intent = new Intent(this, OrderSummaryActivity.class);
        intent.putExtra(EXTRA_TOTAL_PRICE, String.valueOf(NumberFormat.getCurrencyInstance().format(mTotalPrice)));
        intent.putExtra(EXTRA_TEA_NAME, mExtraTeaName);
        intent.putExtra(EXTRA_SIZE, mSize);
        intent.putExtra(EXTRA_MILK_TYPE, mMilkType);
        intent.putExtra(EXTRA_SUGAR_TYPE, mSugarType);
        intent.putExtra(EXTRA_QUANTITY, String.valueOf(mQuantity));

        startActivity(intent);
    }

    private void setupSizeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_tea_size,
            android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerSize.setAdapter(adapter);
        mSpinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mSize = getString(R.string.tea_size_small);
                        break;
                    case 1:
                        mSize = getString(R.string.tea_size_medium);
                        break;
                    case 2:
                        mSize = getString(R.string.tea_size_large);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSize = getString(R.string.tea_size_small);
            }
        });
    }

    private void setupMilkSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_milk,
            android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerMilk.setAdapter(adapter);
        mSpinnerMilk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mMilkType = getString(R.string.milk_type_none);
                        break;
                    case 1:
                        mMilkType = getString(R.string.milk_type_nonfat);
                        break;
                    case 2:
                        mMilkType = getString(R.string.milk_type_1_percent);
                        break;
                    case 3:
                        mMilkType = getString(R.string.milk_type_2_percent);
                        break;
                    case 4:
                        mMilkType = getString(R.string.milk_type_whole);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mMilkType = getString(R.string.milk_type_none);
            }
        });
    }

    private void setupSugarSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_sugar,
            android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerSugar.setAdapter(adapter);
        mSpinnerSugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mSugarType = getString(R.string.sweet_type_0);
                        break;
                    case 1:
                        mSugarType = getString(R.string.sweet_type_25);
                        break;
                    case 2:
                        mSugarType = getString(R.string.sweet_type_50);
                        break;
                    case 3:
                        mSugarType = getString(R.string.sweet_type_75);
                        break;
                    case 4:
                        mSugarType = getString(R.string.sweet_type_100);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSugarType = getString(R.string.sweet_type_0);
            }
        });
    }

    private String calculatePrice() {
        switch (mSize) {
            case TEA_SIZE_SMALL:
                mTotalPrice = mQuantity * SMALL_PRICE;
                break;
            case TEA_SIZE_MEDIUM:
                mTotalPrice = mQuantity * MEDIUM_PRICE;
                break;
            case TEA_SIZE_LARGE:
                mTotalPrice = mQuantity * LARGE_PRICE;
                break;
        }

        return NumberFormat.getCurrencyInstance().format(mTotalPrice);
    }
}