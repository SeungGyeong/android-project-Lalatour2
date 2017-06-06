package com.example.user.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 2017-02-07.
 */

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getIntent();

        TextView tv = (TextView) findViewById (R.id.places);
        TextView tvv = (TextView) findViewById (R.id.restaurant);
        TextView tvvv = (TextView) findViewById (R.id.hotel);
        TextView tvvvv = (TextView) findViewById (R.id.car);
        TextView tvvvvv = (TextView) findViewById (R.id.reservation);

        tv.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListOfEachItemActivity.class);
                it.putExtra("whichBtn", "places");
                startActivity(it);
//                finish();
            }
        });

        tvv.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListOfEachItemActivity.class);
                it.putExtra("whichBtn", "restaurant");
                startActivity(it);
//                finish();
            }
        });

        tvvv.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListOfEachItemActivity.class);
                it.putExtra("whichBtn", "hotel");
                startActivity(it);
//                finish();
            }
        });

        tvvvv.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListOfEachItemActivity.class);
                it.putExtra("whichBtn", "car");
                startActivity(it);
//                finish();
            }
        });

        tvvvvv.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListOfEachItemActivity.class);
                it.putExtra("whichBtn", "reservation");
                startActivity(it);
//                finish();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case android.R.id.home:             //  뒤로 가기 버튼 생성
                onBackPressed();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//    public void places (View v) {
//        Intent it = new Intent(this, ListOfEachItemActivity.class);
//        it.putExtra("whichBtn","places");
//        startActivity(it);
//        finish();
//    }
//
//    public void restaurant (View v) {
//        Intent it = new Intent(this, ListOfEachItemActivity.class);
//        it.putExtra("whichBtn","restaurant");
//        startActivity(it);
//        finish();
//    }
//
//    public void hotel (View v) {
//        Intent it = new Intent(this, ListOfEachItemActivity.class);
//        it.putExtra("whichBtn","hotel");
//        startActivity(it);
//        finish();
//    }
//
//    public void reservation (View v) {
//        Intent it = new Intent(this, ListOfEachItemActivity.class);
//        it.putExtra("whichBtn","reservation");
//        startActivity(it);
//        finish();
//    }
//
//    public void food (View v) {
//        Intent it = new Intent(this, ListOfEachItemActivity.class);
//        it.putExtra("whichBtn","food");
//        startActivity(it);
//        finish();
//    }
}
