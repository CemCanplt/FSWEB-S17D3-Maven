package com.workintech.zoo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kangaroo {
    private int id;
    private String name;
    private double weight;
    private double height;
    private String gender;
    private boolean isAggressive;

    public boolean getIsAggressive() {
        return isAggressive;
    }

    public void setIsAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

}
