package com.example.myapplication.classTest;

public class StringReverser {
    public String reverse(String string){
        if(string == null){
            return null;
        }
        if(string.length() == 1){
            return string;
        }
        char[] sc = string.toCharArray();
        for(int i = 0, j = sc.length-1; i<j ; i++, j--){
            char c = sc[i];
            sc[i] = sc[j];
            sc[j] = c;
        }
        return  new String(sc);
    }


}
