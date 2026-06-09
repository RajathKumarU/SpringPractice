package com.myspring.practice.SpringLearning.intw.trainstn;

public class TrainExecute {

    public static void main(String[] args) {
        TrainSystem t = new TrainSystem();

        t.checkIn(45, "San Francisco", 3);
        t.checkIn(32, "San Mateo", 8);
        t.checkIn(27, "San Francisco", 10);

        t.checkOut(45, "Palo Alto", 15);
        t.checkOut(27, "Palo Alto", 20);
        t.checkOut(32, "Mountain View", 22);

        t.getAverageTime("San Mateo", "Mountain View");
        t.getAverageTime("San Francisco", "Palo Alto");
    }
}
