package com.example.uas2;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Global {
    private static ArrayList<User> users = new ArrayList<>();

    private static User logged = null;

    private static Item[] testItem;;

    public static void initiateTestList(){
        if (users.isEmpty()) {
            testItem = new Item[2];
            testItem[0] = new Cloth("Kaos", "Putih", Cloth.CLOTHSIZE_XL, 50000);
            testItem[1] = new Shoes("Sepatu Kulit", "Coklat", 40, 500000);

            User newUser = new User("Daniel",
                    "example@email.com",
                    "aaa");

            Order order1 = new Order(1000, testItem[0], 2);
            order1.setStatus(Order.ORDERSTATUS_SENT);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2022);
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DATE, 30);
            Order order2 = new Order(2401, testItem[1], 1, cal.getTime());
            order2.setStatus(Order.ORDERSTATUS_CANCELLED);

            newUser.addOrder(order1);
            newUser.addOrder(order2);
            users.add(newUser);
        }
    }

    public static void logUserIn(String email, String pw) {
        User curr;
        for (int i=0; i< users.size(); i++){
            curr = users.get(i);
            if (curr.getEmail().equals(email)){
                if (curr.checkPw(pw)){
                    logged = curr;
                }
            }
        }
    }
    public static void logUserOut(){
        logged = null;
    }

    public static User getLogged() {
        return logged;
    }

    public static void isLogged(Activity a){
        if(Global.logged != null){
            return;
        }

        Intent login = new Intent(a, LoginActivity.class);
        a.startActivity(login);
        a.finish();
    }

    public static void setLogged(User logged) {
        Global.logged = logged;
    }

    public static void setUsers(ArrayList<User> users) {
        Global.users = users;
    }
}
