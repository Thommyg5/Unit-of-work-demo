package com.revature.industry;

import java.util.*;

// is our repository or service in this scenario
public class Restaurant {

    private String name;
    private Set<Pizza> menu;
    private Set<Pizza> insults;

    private Set<Order> pending;
    private Set<Order> cooking;


    public Restaurant(String name, Set<Pizza> menu, Set<Pizza> insults) {
        this.name = name;
        this.menu = new HashSet<>(menu);
        this.insults = new HashSet<>(insults);
        this.pending = new HashSet<>();
        this.cooking = new HashSet<>();
    }

    public Set<Pizza> getMenu() {
        return Collections.unmodifiableSet(menu);
    }

    public Order openOrder(String name, Pizza ...pizzas) {
        Order order = new Order(name, pizzas);
        pending.add(order);
        return order;
    }

    public void commit(Order order) {
        if (!pending.contains(order)) throw new ExasperatedException();
        for (Pizza insult : insults) {
            if (order.getInventory().contains(insult)) {
                pending.remove(order);
                throw new ExasperatedException();
            }
        }
        for (Pizza item : order.getInventory()) {
            if (!menu.contains(item)) order.remove(item);
        }
        pending.remove(order);
        cooking.add(new Order(order));
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", cooking=" + cooking +
                '}';
    }

}
