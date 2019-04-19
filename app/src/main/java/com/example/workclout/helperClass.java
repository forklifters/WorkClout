package com.example.workclout;

import android.support.v7.app.AppCompatDelegate;

public class helperClass
{
    public static String user_id;
    public static String login_type;
    public static int verification;
    public static boolean turn_on;
    public static boolean notification;
    public static boolean privacy;
    public static String email;

    public void setEmail(String email) {
        email = email;
    }

    public String getEmail() {
        return email;
    }

    public void set_notifications_on(boolean values)
    {
        notification=values;
    }

    public boolean get_notifications_on()
    {
        return notification;
    }

    public void set_user_id(String id)
    {
        user_id=id;
    }

    public String get_user_id()
    {
        return user_id;
    }

    public void set_login_type(String log)
    {
        login_type=log;
    }

    public String get_login_type()
    {
        return  login_type;
    }
    public void set_lights_on(boolean values)
    {
        turn_on=values;
    }
    public boolean get_lights_on()
    {
        return turn_on;
    }
    public void set_privacy_on(boolean values)
    {
        privacy=values;
    }
    public boolean get_privacy_on()
    {
        return privacy;
    }
    public void set_verification(int x) { verification=x; }
    public int get_verification() { return verification;}
}
