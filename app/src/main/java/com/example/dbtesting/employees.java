package com.example.dbtesting;

public class employees {
    int ID;
    String Name,Desigation,Location;

    public employees(int ID, String name, String desigation, String location) {
        this.ID = ID;
        Name = name;
        Desigation = desigation;
        Location = location;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getDesigation() {
        return Desigation;
    }

    public String getLocation() {
        return Location;
    }
}
