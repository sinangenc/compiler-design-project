package com.sinangenc.utils;

public final class Converter {
    public static Boolean getBoolean(Object o){
        if(o instanceof Boolean boolValue){
            return boolValue;
        }

        throw new IllegalArgumentException("A non-boolean value is used where a boolean is expected!");
    }

    public static Double getDouble(Object o){
        if(o instanceof Double d){
            return d;
        }

        throw new IllegalArgumentException("A non-double value is used where a double is expected!");
    }

    public static String getString(Object o){
        if(o instanceof String s){
            return s;
        }
        else if (o instanceof Double d){
            if(d.intValue() == d){
                return String.valueOf(d.intValue());
            }
            return String.valueOf(d);
        }
        else if (o instanceof Boolean b){
            return String.valueOf(b);
        }

        throw new IllegalArgumentException("Unexpected data type!");
    }
}