package com.example.user.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import io.realm.Realm;

/**
 * Created by user on 2017-01-25.
 */

public class AddListActivity extends AppCompatActivity {
    private Realm realm;
    Toolbar t;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //  뒤로 가기 버튼 생성

        getIntent();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addlist, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();

        switch (curId) {
            case R.id.menu_check:

                EditText t1 = (EditText)findViewById(R.id.countryname);
                String countryname = t1.getText().toString();
                EditText t2 = (EditText)findViewById(R.id.cityname);
                String cityname = t2.getText().toString();

                Realm.init(this);
                realm = Realm.getDefaultInstance();

                // DB에 저장 부분
                realm.beginTransaction();
                ListDB list = realm.createObject(ListDB.class);
                list.setCountry(countryname);
                list.setCity(cityname);
                realm.commitTransaction();

                finish();

                break;

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
}
