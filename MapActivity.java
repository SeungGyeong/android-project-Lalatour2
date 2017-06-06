package com.example.user.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user on 2017-01-17.
 */

public class MapActivity extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    GoogleMap mMap;
    MapView mapView;
    View view;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.activity_first_main,container,false);
            mapView =(MapView) view.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }
        return view;
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }


    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();

        for(MarkerDB marker : realm.where(MarkerDB.class).findAll()) {
            double latt = marker.getLat();
            double lngg = marker.getLng();
            LatLng latLngg = new LatLng(latt, lngg);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLngg);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.star));
            mMap.addMarker(markerOptions).showInfoWindow(); //마커 생성
        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {                     // 길게 클릭시 마커 지정
        public void onMapLongClick(LatLng latLng) {
            MarkerOptions markerOptions = new MarkerOptions();
            //마커위치설정
            markerOptions.position(latLng);

            realm.beginTransaction();
            MarkerDB marker = realm.createObject(MarkerDB.class);
            marker.setLat(latLng.latitude);
            marker.setLng(latLng.longitude);
            realm.commitTransaction();

            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.star));
            mMap.addMarker(markerOptions).showInfoWindow(); //마커 생성
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));   // 마커생성위치로 이동
        }
    });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                LatLng x =marker.getPosition();
                double lattt = x.latitude;
                double lnggg = x.longitude;

                realm.beginTransaction();
                RealmResults<MarkerDB> rows = realm.where(MarkerDB.class).equalTo("lat",lattt).equalTo("lng",lnggg).findAll();
                rows.deleteAllFromRealm();
                realm.commitTransaction();

                mMap.clear();
                for(MarkerDB markerr : realm.where(MarkerDB.class).findAll()) {
                    double latt = markerr.getLat();
                    double lngg = markerr.getLng();
                    LatLng latLngg = new LatLng(latt, lngg);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLngg);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.star));
                    mMap.addMarker(markerOptions).showInfoWindow(); //마커 생성
                }

                return  true;
            }
        });
    }
}
