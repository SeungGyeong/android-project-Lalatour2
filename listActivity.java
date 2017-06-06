package com.example.user.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.halfbit.pinnedsection.PinnedSectionListView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user on 2017-01-19.
 */

public class listActivity extends Fragment {
    View view;
    protected Realm realmm;
    public static  Map<String, List<String>> m1;
    ListView listview;
    public static CityPinnedListAdapter adapter;
    private Realm realm;

    List l;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        m1 = new HashMap<>();
        l = new ArrayList();

        if(view == null){
            view = inflater.inflate(R.layout.activity_second_main,container,false);
        }

        Realm.init(getActivity());
        realmm = Realm.getDefaultInstance();

        listview = (PinnedSectionListView) view.findViewById(R.id.citylist);

        setHasOptionsMenu(true);
        return view;
    }

    public void onResume() {
        super.onResume();

        getActivity().invalidateOptionsMenu();

        m1.clear();
        l.clear();
        for(ListDB dbList : realmm.where(ListDB.class).findAll()) {         // DB에 있는 내용 불러다 표시해주는 부분
            String countryName = dbList.getCountry();
            String cityName = dbList.getCity();
            ///Log.e("DBlist",countryName+"/"+cityName);

            if(m1.containsKey(countryName) == false) {                         // 국가가 존재하지 않는 경우
                m1.put(countryName, new ArrayList<String>());
                m1.get(countryName).add(cityName);
            }else{                                                              // 국가가 이미 존재하는 경우
                m1.get(countryName).add(cityName);
                l.add(countryName);
            }
        }
        ///Log.e("m1",m1.toString());

        adapter = new CityPinnedListAdapter(getContext(), m1);         // DB로부터 정보를 읽어와 리스트뷰에 보여줌.
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {                     // 리스트뷰의 아이템 클릭시 아이템 액티비티로 넘어감.
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itt = new Intent(getActivity(),ItemActivity.class);
                startActivity(itt);
                //finish();
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {             // 리스트뷰의 아이템 길게 클릭시 삭제 할 수 있다.
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {

                HashMap<String,String> map = (HashMap) listview.getItemAtPosition(position);
                String sstr = map.get("txt");

                Realm.init(getActivity());
                realm = Realm.getDefaultInstance();

                realm.beginTransaction();

                String st;
                for (int i=0; i<l.size(); i++) {
                    st = l.get(i).toString();
                        if(st.equals(sstr)) {
                            RealmResults<ListDB> rows = realm.where(ListDB.class).equalTo("country",sstr).findAll();
                            rows.deleteAllFromRealm();
                        } else {
                            RealmResults<ListDB> rows = realm.where(ListDB.class).equalTo("city",sstr).findAll();
                            rows.deleteAllFromRealm();
                        }
                }
                realm.commitTransaction();

                m1.clear();
                l.clear();
                for(ListDB dbList : realmm.where(ListDB.class).findAll()) {         // DB에 있는 내용 불러다 표시해주는 부분
                    String countryName = dbList.getCountry();
                    String cityName = dbList.getCity();
                    ///Log.e("DBlist",countryName+"/"+cityName);

                    if(m1.containsKey(countryName) == false) {                         // 국가가 존재하지 않는 경우
                        m1.put(countryName, new ArrayList<String>());
                        m1.get(countryName).add(cityName);
                    }else{                                                              // 국가가 이미 존재하는 경우
                        m1.get(countryName).add(cityName);
                        l.add(countryName);
                    }
                }
                adapter.notifyDataSetChanged();

                return true;
            }



        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_add:
                Intent itt = new Intent(getActivity(), AddListActivity.class);
                startActivity(itt);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
