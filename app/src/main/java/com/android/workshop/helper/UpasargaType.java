package com.android.workshop.helper;

public class UpasargaType {

    public static <T> T getType(Object type, Class<T> classes) {
        try {
            return classes.cast(type);
        } catch (ClassCastException ex) {
            return null;
        }
    }
}
