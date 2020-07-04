package com.driversandassistantsorganizationapp.demo.controllers;

import java.util.HashMap;

public class Demo {

    public static void printResult(String input) {
        String words = input.toLowerCase();
        HashMap<Character, Integer> chareCounterMap = findNoOfRepeatChar(words);
        //print result
        for (int j = 0; j < input.length(); j++) {
            if (chareCounterMap.get(words.charAt(j)) == 1) {
                System.out.println("Charecter  " + words.charAt(j) + " count " + chareCounterMap.get(words.charAt(j)));
                break;
            }
        }

    }

    static HashMap<Character, Integer> findNoOfRepeatChar(String words) {
        int length = words.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        //create map char --> count
        for (int i = 0; i < length; i++) {
            if (map.containsKey(words.charAt(i))) {
                int count = map.get(words.charAt(i)) + 1;
                map.put(words.charAt(i), count);
            } else {
                map.put(words.charAt(i), 1);
            }
        }
        return map;
    }


}
