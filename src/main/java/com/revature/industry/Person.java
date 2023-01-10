package com.revature.industry;

// are the clients in this scenario

public class Person {

    private String name;
    private Pizza[] favorites;

    public Person(String name, Pizza ...favorites) {
        this.name = name;
        this.favorites = favorites;
    }

    public Order call(Restaurant location) {
        return location.openOrder(name, favorites);
    }

    public void tell(Order order) {
        order.addAll(favorites);
    }

}
