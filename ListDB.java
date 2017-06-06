package com.example.user.test;

import io.realm.RealmObject;

/**
 * Created by user on 2017-01-25.
 */

public class ListDB extends RealmObject {

    private String country;
    private String city;

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

}
