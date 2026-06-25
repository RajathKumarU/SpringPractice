package com.myspring.practice.SpringLearning.intw.omnissa.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Item {
     private String name;
     @Setter
     private int qty;
     private double price;

}
