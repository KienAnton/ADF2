package t2012e.com.entity;

import t2012e.com.util.DateTimeUtil;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable {
    private String rollNumber;
    private String fullName;
    private Date cakeDay;
    private int numberOfCake;

    public Teacher() {
    }

    public Teacher(String rollNumber, String fullName, Date cakeDay, int numberOfCake) {
        this.rollNumber = rollNumber;
        this.fullName = fullName;
        this.cakeDay = cakeDay;
        this.numberOfCake = numberOfCake;
    }

    public Teacher(String rollNumber, String fullName, int numberOfCake){
        this.rollNumber = rollNumber;
        this.fullName = fullName;
        this.numberOfCake = numberOfCake;
    }

    public Teacher(String rollNumber, String fullName, String strCakeDay, int numberOfCake) {
        this.rollNumber = rollNumber;
        this.fullName = fullName;
        this.cakeDay = DateTimeUtil.parseDateFromString(strCakeDay);
        this.numberOfCake = numberOfCake;
    }

    @Override
    public String toString() {
        return String.format("%10s%10s%10s | %10s%15s%10s | %5s%20s%5s | %5s%10s%5s\n",
                "", this.rollNumber, "",
                "", this.fullName, "",
                "", this.cakeDay, "", "",
                "", this.numberOfCake, "", "");
    }



    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCakeDay() {
        return cakeDay;
    }

    public void setCakeDay(Date cakeDay) {
        this.cakeDay = cakeDay;
    }

    public int getNumberOfCake() {
        return numberOfCake;
    }

    public void setNumberOfCake(int numberOfCake) {
        this.numberOfCake = numberOfCake;
    }
}
