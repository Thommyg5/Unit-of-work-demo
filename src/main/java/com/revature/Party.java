package com.revature;

import com.revature.industry.*;

import java.util.Set;

public class Party {

    public static void main(String[] args) {
        // Team Rocket decides to order pizza
        // from a new pizza joint called Brock's in town
        System.out.println(brocks);
        Order order = teamRocket[0].call(brocks);
        // the caller passes the phone to each member for their order
        for (int i = 1; i < teamRocket.length; i++) {
            teamRocket[i].tell(order);
        }
        // After the whole team has ordered
        // an awkward silence prompts the attendant to finalize the order
        try {
            brocks.commit(order);
        }
        catch(ExasperatedException e) {
            System.out.println("The attendant, insulted by team rocket's choices, hangs up in disgust");
        }
        System.out.println(brocks);
    }

    private static Person[] teamRocket = new Person[] {
            new Person(
                    "Jessie",
                    Pizza.PEPPERONI,
                    Pizza.SAUSAGE
            ),
            new Person(
                    "James",
                    Pizza.CHEESE,
                    Pizza.PEPPERONI
            ),
            new Person(
                    "Meowth",
                    Pizza.SUPREME
            )
    };

    private static Restaurant brocks = new Restaurant(
            "Brock's",
            Set.of(
                    Pizza.PEPPERONI,
                    Pizza.CHEESE,
                    Pizza.SAUSAGE,
                    Pizza.SUPREME
            ),
            Set.of(Pizza.PINEAPPLE)
    );

}
