package com.example.user.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by SeungGyeong Chung on 2016-10-28. 4th Activity. Previous Activity : Item Activity
 * 각 아이템(항목. 동그라미 버튼)에 저장된 정보의 리스트를 보여준다.
 */

public class ListOfEachItemActivity extends AppCompatActivity {
    String file_name;
    ListView list;
    static  public ArrayList<String> mDatas = new ArrayList<String>();
    String which;
    String path;
    ArrayAdapter adapter;
    //public static final int INTENTTEST = 101;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofeachitem);

        Intent it = getIntent();
        if(it.hasExtra("whichBtn")) {
            which = it.getStringExtra("whichBtn");
            if(which.equals("places")) {
                path = Environment.getExternalStorageDirectory().toString()+"/PLACES";
            } else if (which.equals("restaurant")) {
                path = Environment.getExternalStorageDirectory().toString()+"/RESTAURANT";
            } else if (which.equals("hotel")) {
                path = Environment.getExternalStorageDirectory().toString()+"/HOTEL";
            } else if (which.equals("car")) {
                path = Environment.getExternalStorageDirectory().toString()+"/CAR";
            } else if (which.equals("reservation")) {
                path = Environment.getExternalStorageDirectory().toString()+"/RESERVATION";
            }
        }
        mDatas.clear();
        try
        {
            FilenameFilter fileFilter = new FilenameFilter()  // 특정 확장자만 가지고 오고 싶을 경우 사용
            {
                public boolean accept(File dir, String name)
                {
                    return name.endsWith("png"); // 사용하고 싶은 확장자
                }
            };
            File file = new File(path);
            File[] files = file.listFiles(fileFilter);
            String [] titleList = new String [files.length];
            for(int i = 0;i < files.length;i++)
            {
                titleList[i] = files[i].getName();
                mDatas.add (titleList[i].toString());
            }//end for

        } catch( Exception e )
        {
            e.printStackTrace();
        }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDatas);
            list = (ListView) findViewById(R.id.itemlist);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {                     // 리스트뷰의 아이템 클릭시 아이템 액티비티로 넘어감.
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent itt = new Intent(getApplicationContext(), ShwCapturedImage.class);
                    file_name = list.getItemAtPosition(position).toString();
                    itt.putExtra("file_name", file_name);
                    itt.putExtra("whichBtn",which);
                    itt.putExtra("position",position);
                    startActivityForResult(itt,0);
//                    finish();
                }
            });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {             // 리스트뷰의 아이템 길게 클릭시 삭제, 수정 할 수 있다.
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                try{
                    FilenameFilter fileFilter = new FilenameFilter()  // 특정 확장자만 가지고 오고 싶을 경우 사용
                    {
                        public boolean accept(File dir, String name)
                        {
                            return name.endsWith("png"); // 사용하고 싶은 확장자
                        } //end accept
                    };
                    File file = new File(path);
                    File[] files = file.listFiles(fileFilter);
                    files[position].delete();
                    Toast.makeText(getApplicationContext(), "파일 삭제 완료 ", Toast.LENGTH_SHORT).show();

                    mDatas.clear();
                    File filee = new File(path);
                    File[] filees = file.listFiles(fileFilter);
                    String [] titleList = new String [filees.length];
                    for(int i = 0;i < filees.length;i++)
                    {
                        titleList[i] = filees[i].getName();
                        mDatas.add (titleList[i].toString());
                    }
                    adapter.notifyDataSetInvalidated();

                }catch(Exception e){Toast.makeText(getApplicationContext(), "파일 삭제 실패 ", Toast.LENGTH_SHORT).show();}
                return true;
            }
            });

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //  뒤로 가기 버튼 생성
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        if(resultcode == RESULT_OK) {
            //if(requestcode == INTENTTEST){
            //adapter.notifyDataSetChanged();

            String which = data.getStringExtra("whichBtn");

            Toast.makeText(ListOfEachItemActivity.this, which ,Toast.LENGTH_LONG).show();

            if(which.equals("places")) {
                path = Environment.getExternalStorageDirectory().toString()+"/PLACES";
            } else if (which.equals("restaurant")) {
                path = Environment.getExternalStorageDirectory().toString()+"/RESTAURANT";
            } else if (which.equals("hotel")) {
                path = Environment.getExternalStorageDirectory().toString()+"/HOTEL";
            } else if (which.equals("car")) {
                path = Environment.getExternalStorageDirectory().toString()+"/CAR";
            } else if (which.equals("reservation")) {
                path = Environment.getExternalStorageDirectory().toString()+"/RESERVATION";
            }

            mDatas.clear();
            try
            {
                FilenameFilter fileFilter = new FilenameFilter()  // 특정 확장자만 가지고 오고 싶을 경우 사용
                {
                    public boolean accept(File dir, String name)
                    {
                        return name.endsWith("png"); // 사용하고 싶은 확장자
                    }
                };
                File file = new File(path);
                File[] files = file.listFiles(fileFilter);
                String [] titleList = new String [files.length];
                for(int i = 0;i < files.length;i++)
                {
                    titleList[i] = files[i].getName();
                    mDatas.add (titleList[i].toString());
                }//end for
                adapter.notifyDataSetChanged();
            } catch( Exception e )
            {
                e.printStackTrace();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listofeachitem, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case android.R.id.home:             //  뒤로 가기 버튼 생성
//                onBackPressed();
                finish();
                break;

            case R.id.menu_add:
                Intent it3 = new Intent(getApplicationContext(), WriteMemoActivity.class);
                it3.putExtra("whichBtn",which);
                startActivityForResult(it3,0);          // onActivityForResult 가 반응하려면 이것이랑 세트로 사용되어야 함.
                break;

        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        Intent it2 = new Intent(getApplicationContext(), ItemActivity.class);
//        startActivity(it2);
//        Toast.makeText(ListOfEachItemActivity.this,"test",Toast.LENGTH_SHORT).show();
//        finish();
//
//    }

}
