package com.example.switch2025.lld;

import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class DeepCopyCreator {

    public static void main(String[] args) {
        Address address = new Address("Amritsar");
        Person p1 = new Person("Abhi", address);

        Person p2 = p1.clone();
        // mutate the address object
        address.setCity("Bangalore");

        System.out.println("Person1 City: " + p1.getAddress().getCity());
        System.out.println("Person2 City: " + p2.getAddress().getCity());
    }


}

class Person implements Serializable, Cloneable {
    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public Person clone() {
        try {
            Person clone = (Person) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.address = this.address.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

class Address implements Serializable, Cloneable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }


    @Override
    public Address clone() {
        try {
            Address clone = (Address) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
