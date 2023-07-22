package com.sinangenc.utils;

/**
 * This class provides methods for converting objects to boolean, double, and string types.
 * In our implementation, we used Object parent type as a return type of the methods in PslTreeVisitor class. Thus,
 * before converting a value, we need to check whether it is one of the allowed types.
 */
public final class Converter {
    /**
     * Converts an object to a boolean value.
     * Throws a runtime exception if the object is not of boolean type.
     */
    public static Boolean getBoolean(Object o){
        if(o instanceof Boolean boolValue){
            return boolValue;
        }

        throw new IllegalArgumentException("A non-boolean value is used where a boolean is expected!");
    }

    /**
     * Converts an object to a double value.
     * Throws a runtime exception if the object is not of double type.
     */
    public static Double getDouble(Object o){
        if(o instanceof Double d){
            return d;
        }

        throw new IllegalArgumentException("A non-double value is used where a double is expected!");
    }

    /**
     * Converts an object to string representation.
     * Throws a runtime exception if the type of the object is not one of the allowed types.
     */
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