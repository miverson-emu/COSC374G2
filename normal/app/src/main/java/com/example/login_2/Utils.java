package com.example.login_2;

import java.util.Base64;
import java.util.List;

public class Utils {
    public static String b64encrypt(String s){
        return Base64.getEncoder().encodeToString(s.getBytes());
    }
    public static String b64decrypt(String s) {
        return new String(Base64.getDecoder().decode(s));

    }


}
