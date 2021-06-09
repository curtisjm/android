package com.comp232.addressbook3;

import androidx.annotation.NonNull;

public class Contact {

    private String firstName;

    public Contact(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    @Override
    public String toString() {
        return "First Name: " + firstName;
    }

}
