package com.revature.industry;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// an order is the unit of work in this scenario
public class Order {

    private String name;
    private Set<Pizza> inventory;

    public Order(Order order) {
        this.name = order.name;
        this.inventory = new HashSet<>(order.inventory);
    }
    public Order(String name, Pizza ...pizzas) {
        this.name = name;
        this.inventory = Arrays.stream(pizzas).collect(Collectors.toSet());
    }

    public void addAll(Pizza ...pizzas) {
        inventory.addAll(Arrays.asList(pizzas));
    }

    public boolean remove(Pizza pizza) {
        return inventory.remove(pizza);
    }

    public Set<Pizza> getInventory() {
        return Collections.unmodifiableSet(inventory);
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
