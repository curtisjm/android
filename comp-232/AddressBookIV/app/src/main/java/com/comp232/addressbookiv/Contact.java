package com.comp232.addressbookiv;

public class Contact implements Comparable<Contact> {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public Contact() {
        firstName = "- undefined -";
        lastName = "- undefined -";
        address = "- undefined -";
        phoneNumber = "- undefined -";
    }

    public Contact(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Contact) {
            Contact otherBook = (Contact) obj;
            return firstName.equalsIgnoreCase(otherBook.firstName) &&
                    lastName.equalsIgnoreCase(otherBook.lastName) &&
                    address.equalsIgnoreCase(otherBook.address) &&
                    phoneNumber.equalsIgnoreCase(otherBook.phoneNumber);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Contact otherContact) {
        if(firstName.equalsIgnoreCase(otherContact.firstName)) {
            return lastName.compareToIgnoreCase(otherContact.lastName);
        } else {
            return firstName.compareToIgnoreCase(otherContact.firstName);
        }
    }
}
