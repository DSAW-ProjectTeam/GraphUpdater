package com.gu.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    boolean isNullOrEmpty(String string){
        if (null == string){
            return true;
        }
        if (string.trim().equals("")){
            return true;
        }
        return string.isEmpty() || string.isBlank();
    }
}
