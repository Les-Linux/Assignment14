package com.elbicon.coderscampus;

import java.util.Locale;

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
    public int compareTo(Person that) {
        if (this.getRole().compareTo(that.getRole()) == 0) {    // if roles are equal, sort by next criteria - Username
            //System.out.println("'THIS' Role=" + "\"" + this.getRole() + "\"" + " email=" + this.getUsername() +  "\t'THAT' Role=" + "\"" + that.getRole() + "\"" + " email=" + that.getUsername());
            return this.getUsername().toLowerCase().compareTo(that.getUsername().toLowerCase());
        } else{
            //return that.getUsername().toLowerCase().compareTo(this.getUsername().toLowerCase());
            return that.getRole().compareTo(this.getRole());
        }
    }
}
