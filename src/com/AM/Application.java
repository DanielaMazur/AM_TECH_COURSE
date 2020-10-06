package com.AM;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args){
        ArrayList<User> usersList = new ArrayList<>();

        usersList.add(new User("Ion", "Ursu", "ion.ursu@mail.com", 22, User.Status.NEW, createTimeStamp(2020,10,1)));
        usersList.add(new User("Ana", "Balan", "ana.balan@mail.com", 25, User.Status.ACTIVE, createTimeStamp(2019, 3, 23)));
        usersList.add(new User("Alina", "Busuioc", "alina.busuioc@mail.com", 27, User.Status.INACTIVE, createTimeStamp(2020, 5, 2)));
        usersList.add(new User("Radu", "Savca", "radu.savca@mail.com", 25, User.Status.NEW, createTimeStamp(2020, 9, 29)));
        usersList.add(new User("Ina", "Manciu", "ina.manciu@mail.com", 42, User.Status.NEW, createTimeStamp(2020,9,18)));
        usersList.add(new User("Arina", "Bivol", "arina.bivol@mail.com", 58, User.Status.ACTIVE, createTimeStamp(2020, 3, 13)));
        usersList.add(new User("Danu", "Preda", "danu.preda@mail.com", 36, User.Status.INACTIVE, createTimeStamp(2020, 6, 2)));
        usersList.add(new User("Marin", "Rusu", "marin.rusu@mail.com", 27, User.Status.NEW, createTimeStamp(2020, 9, 3)));


        Timestamp yesterday = new Timestamp(java.sql.Date.valueOf(LocalDate.now().minusDays(1)).getTime());

        for(User user : usersList){
            if(user.getStatus() == User.Status.NEW && user.getTimestamp().compareTo(yesterday) < 0){
                user.setStatus(User.Status.ACTIVE);
            }
        }

        System.out.println(usersList);

        blockInactiveUsers(usersList);

        System.out.println(usersList);

    }

    static private Timestamp createTimeStamp(int year, int month, int dayOfMonth){
        return  new Timestamp(java.sql.Date.valueOf(LocalDate.of(year, month, dayOfMonth)).getTime());
    }

    static private void blockInactiveUsers(ArrayList<User> usersList){
        Timestamp oneMonthAgo = new Timestamp(java.sql.Date.valueOf(LocalDate.now().minusMonths(1)).getTime());

        for(User user : usersList){
            if(user.getStatus() == User.Status.INACTIVE && user.getTimestamp().compareTo(oneMonthAgo) < 0){
                user.setStatus(User.Status.BLOCKED);
            }
        }
    }

}
