package NewEntities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Frederik
 */
public class City {
    int id;
    String cityName;
    String latitude;
    String longtitude;

    public City(int id, String cityName, String latitude, String longtitude) {
        this.id = id;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public City() {
        
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
    
    
}
