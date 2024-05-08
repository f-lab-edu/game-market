package com.gamemarket.user.utils;

public class UserConst {

    public static final String SESSION_USER_KEY = "user";
    public static final String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static final String NICKNAME_REGEXP = "^[가-힣a-zA-Z]+$";
    public static final String PASSWORD_REGEXP = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}";

}
