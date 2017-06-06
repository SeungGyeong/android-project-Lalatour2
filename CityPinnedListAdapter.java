package com.example.user.test;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by user on 2017-01-26.
 */

public class CityPinnedListAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    Context context;
    Map<String, List<String>> map;
    LayoutInflater inflater;

    public CityPinnedListAdapter(Context context, Map<String, List<String>> map){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.map = map;
    }

    protected void prepareSections(int sectionsNumber) { }
    protected void onSectionAdded(ClipData.Item section, int sectionPosition) { }

    /*
    position => city의 현재위치
    tmp => 초기값 : 0,  array list에서 키값 (country 값)의 위치
    keycnt => 초기값 : 0, 키의 개수 -1
    key => 현재 key 값
    cnt => 초기값 : 0, city 개수
    keys => map에 있는 모든 키들을 담음.
     */

    @Override
    public Map<String,String> getItem(int position) {
        Map<String,String> returnmap = new HashMap<>();
        Iterator<String> keys = map.keySet().iterator();
        int keycnt = 0 ;
        int cnt = 0, tmp = 0 ;
        while( keys.hasNext() ) {
            String key = keys.next();
            tmp = cnt;
            cnt += map.get(key).size();

            if(position - tmp -keycnt == 0){
                returnmap.put("type","Country");
                returnmap.put("txt",key.toString());
                return returnmap;
            }//리턴값이 국가인경우

            else if(cnt + keycnt >= position){
                returnmap.put("type","City");
                ///Log.e("CPA : ",position+"/"+tmp+ "/"+keycnt+"/"+key+"/"+map.get(key).size());
                returnmap.put("txt",map.get(key).get(position - tmp -keycnt-1));
                return returnmap;
            }// 리턴값이 도시인경우
            keycnt++;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        Iterator<String> keys = map.keySet().iterator();
        int cnt = 0 ;
        while( keys.hasNext() ) {
            String key = keys.next();
            cnt += map.get(key).size()+1;
        }return cnt;
    }                                   ///////////

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.listadapter,null);
        }

        Map<String,String> data = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(data.get("txt"));
        if(data.get("type").toString().equals("Country"))   {
            convertView.setBackgroundColor(Color.BLUE);
        }

        return convertView;
    }
}
