package com.first_teck.locationmarkerv2drawer;


import java.util.Date;

/**
 * Created by zyqzh_000 on 2015/11/23.
 */
public class ItemLocation extends MenuItem {
    private MyLocation myLocation = new MyLocation();

    public ItemLocation(String name, double lat, double lng, String categories, String description, Date lastVisitedDate, Date savedDate){
        this.myLocation.setLocationName(name);
        this.myLocation.setLat(lat);
        this.myLocation.setLng(lng);
        this.myLocation.setCategories(categories);
        this.myLocation.setDescription(description);
        this.myLocation.setLastVisitedDate(lastVisitedDate);
        this.myLocation.setSavedDate(savedDate);
    }
    public String getLocationName() {return myLocation.getLocationName();}

    public void setLocationName(String locationName) {this.myLocation.setLocationName(locationName);}

    public double getLat() {return myLocation.getLat();}

    public void setLat(double lat) {this.myLocation.setLat(lat);}

    public double getLng() {return myLocation.getLng();}

    public void setLng(double lng) {this.myLocation.setLat(lng);}

    public Date getSavedDate() {
        return myLocation.getSavedDate();
    }

    public void setSavedDate(Date savedDate) {
        myLocation.setSavedDate(savedDate);
    }

    public String getCategories() {
        return myLocation.getCategories();
    }

    public void setCategories(String categories) {myLocation.setCategories(categories);}

    public Date getLastVisitedDate() {
        return myLocation.getLastVisitedDate();
    }

    public void setLastVisitedDate(Date lastVisitedDate) {myLocation.setLastVisitedDate(lastVisitedDate);}

    public String getDescription() {
        return myLocation.getDescription();
    }

    public void setDescription(String description) {myLocation.setDescription(description);}
}
