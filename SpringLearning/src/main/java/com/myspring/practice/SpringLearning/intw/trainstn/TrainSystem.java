package com.myspring.practice.SpringLearning.intw.trainstn;

import java.util.HashMap;
import java.util.Map;

public class TrainSystem {

    Map<Integer, Customer> idToCustomerMap;
    Map<String, TotalTime> srcDestTimeMap;

    public TrainSystem() {
        idToCustomerMap = new HashMap<>();
        srcDestTimeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
         if(idToCustomerMap.containsKey(id)){
             System.out.println("Customer already checked-in");
             return;
         }

         idToCustomerMap.put(id, new Customer(id, stationName, t));

        System.out.println("Customer: " + id + " checked-in at stn: "
                + stationName + " at time: " + t);
    }

    public void checkOut(int id, String stationName, int t) {
        if(!idToCustomerMap.containsKey(id)){
            System.out.println("Customer has not checkedIn!! Please checkIn first.");
            return;
        }

        Customer c = idToCustomerMap.remove(id);
        String srcName = c.getName();
        int startTime = c.getTime();
        int totalTime = t-startTime;

        String key = getKey(srcName, stationName);

        srcDestTimeMap.putIfAbsent(key, new TotalTime(0,0));
        TotalTime tt = srcDestTimeMap.get(key);
        tt.totalTime += totalTime;
        tt.totalCustomers++;


        System.out.println("Customer " + c.getId() + " checked out at "
                + stationName + " totalTime: " + totalTime);
    }

    private String getKey(String srcName, String destName) {
        return srcName+"-"+destName;
    }

    public void getAverageTime(String startStation, String endStation) {
        String key = getKey(startStation, endStation);

        if(!srcDestTimeMap.containsKey(key)){
            System.out.println("No customer has travelled from "+ startStation + " to " + endStation);
            return;
        }

        TotalTime tt = srcDestTimeMap.get(key);
        double avg = (double) tt.getTotalTime() / tt.getTotalCustomers();
        System.out.println("Average time from " + startStation
                + " to " + endStation + " is: " + avg);
    }
}




