package com.first_teck.locationmarkerv2drawer;

import java.util.Date;

/**
 * Created by zyqzh_000 on 2015/11/23.
 */
public class MyLocation {
    private String locationName;
    private double lat;
    private double lng;
    private Date savedDate;
    private Date lastVisitedDate;
    private String categories;
    private String description;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLat() {return lat;}

    public void setLat(double lat) {this.lat = lat;}

    public double getLng() {return lng;}

    public void setLng(double lng) {this.lng = lng;}

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Date getLastVisitedDate() {
        return lastVisitedDate;
    }

    public void setLastVisitedDate(Date lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
