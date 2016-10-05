package com.mvLab.account.utils;

import com.mvLab.account.WindowManager;

import java.lang.reflect.Method;

public class Lab_Helper {
    public static Object castValue(Object value, Class type) {
        Object returnValue = value;
        if (value.getClass() == type) {
            returnValue = value;
        }
        else if (value instanceof String) {
            Method[] typeMethods = type.getDeclaredMethods();
            for (Method parserMethod : typeMethods) {
                if (parserMethod.getName().contains("parse") && parserMethod.getParameterTypes().length == 1 && parserMethod.getParameterTypes()[0] == String.class) {
                    try {
                        returnValue =  parserMethod.invoke(null, value);
                        break;
                    }
                    catch (Exception e) {
                        //TODO handle exception
                        WindowManager.openErrorWindow(e.toString());
                    }
                }
                else if (parserMethod.getName().contains("fromString") && parserMethod.getParameterTypes().length == 1 && parserMethod.getParameterTypes()[0] == String.class) {
                    try {
                        returnValue =  parserMethod.invoke(null, value);
                        break;
                    }
                    catch (Exception e) {
                        //TODO handle exception
                        WindowManager.openErrorWindow(e.toString());
                    }
                }
            }
        }

        return returnValue;
    }
}
