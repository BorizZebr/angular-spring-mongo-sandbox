package com.whitesoft.pinmap.tests;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Factory class for test data.
 *
 * @author brzzbr
 */
public final class TestDataFactory {

    private static double rangeMinX = -180.0;
    private static double rangeMaxX = 180.0;

    private static double rangeMinY = -90.0;
    private static double rangeMaxY = 90.0;

    public static String getTestApiUrl(){
        return "http://localhost:7788/api";
    }

    public static User getValidTestUser(){

        User result = new User();
        result.setUsername("testLogin");
        result.setPassword("827ccb0eea8a706c4c34a16891f84e7b");

        return result;
    }

    public static User getValidTestUser_2(){

        User result = new User();
        result.setUsername("testLogin_2");
        result.setPassword("827ccb0eea8a706c4c34a16891f84e7b");

        return result;
    }

    /**
     * user-1 is subscribed on user-2. In other words user-2 is author,
     * user-1 is subscriber
     * @return correct test usb
     */
    public static Sub getValidSub(User author, User subscriber){

        Sub result = new Sub();
        result.setAuthor(author);
        result.setSubscriber(subscriber);
        result.setSince(new Date());

        return result;
    }

    public static List<Pin> getTestPins(User user, int count){

        List<Pin> result = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            Pin pin = new Pin();
            pin.setDescription("testDescription");
            pin.setUser(user);
            pin.setUsername(user.getUsername());
            pin.setLocation(getRandomLocation());
            pin.setCreated(new Date());
            pin.setName("testName" + 1);
            result.add(pin);
        }

        return result;
    }

    public static String getCorrectLogin(){

        return "tzarivan";
    }

    public static String getWrongLogin(){

        return "qwerty";
    }

    public static String getCorrectPassword(){

        return "12345";
    }

    public static String getWronPassword(){

        return "11111";
    }

    public static GeoJsonPoint getRandomLocation(){

        Random r = new Random();

        double x = rangeMinX + (rangeMaxX - rangeMinX) * r.nextDouble();
        double y = rangeMinY + (rangeMaxY - rangeMinY) * r.nextDouble();

        return new GeoJsonPoint(x, y);
    }
}
