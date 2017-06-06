package com.example.user.test;

import io.realm.RealmObject;

/**
 * Created by user on 2016-12-27.
 */

public class MarkerDB extends RealmObject {
    //private int Id;
    private double lat;
    private double lng;

    public double getLat() {return lat;}
    public void setLat(double lat) {this.lat = lat;}
    public double getLng() {return lng;}
    public void setLng(double lng) {this.lng=lng;}
}
