package com.elbicon.coderscampus;

public abstract class Person implements Comparable<Person> {
    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getRole();

    public abstract void setRole(String role);

    @Override
    public int compareTo(Person that) { //compareTo is a contract method defined in the comparable interface
        if (this.getRole().compareTo(that.getRole()) == 0) {    // if heights are equal, sort by next criteria - name
            return this.getUsername().compareTo(that.getUsername());  //sort name in ASC order
        } else {
            return that.getRole().compareTo(this.getRole()); //sort height in DESC order
        }
    }
}
