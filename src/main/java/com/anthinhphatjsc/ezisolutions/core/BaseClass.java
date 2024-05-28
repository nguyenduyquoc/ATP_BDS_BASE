package com.anthinhphatjsc.ezisolutions.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseClass {

    public Object getAttributeByName(String fieldName, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        return this.getAttribute(field, type);
    }
    public Object getMethodByName(String fieldName, Class<?> type) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        Method method = this.getClass().getDeclaredMethod(fieldName);
        return type.cast(method.getDefaultValue());
    }

    public Object getAttribute(Field field, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
        return type.cast(field.get(this));
    }

    public void setAttributeByName(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        this.setAttribute(field, value);
    }

    public void setAttribute(Field field, Object value) throws NoSuchFieldException, IllegalAccessException {
        if (field.getType() == Character.TYPE) {
            field.set(getClass(), value.toString().charAt(0));
            return;
        }
        if (field.getType() == Short.TYPE) {
            field.set(getClass(), Short.parseShort(value.toString()));
            return;
        }
        if (field.getType() == Integer.TYPE) {
            field.set(getClass(), Integer.parseInt(value.toString()));
            return;
        }
        if (field.getType() == Long.TYPE) {
            field.set(getClass(), Long.parseLong(value.toString()));
            return;
        }
        if (field.getType() == Float.TYPE) {
            field.set(getClass(), Float.parseFloat(value.toString()));
            return;
        }
        if (field.getType() == Double.TYPE) {
            field.set(getClass(), Double.parseDouble(value.toString()));
            return;
        }
        if (field.getType() == Byte.TYPE) {
            field.set(getClass(), Byte.parseByte(value.toString()));
            return;
        }
        if (field.getType() == Boolean.TYPE) {
            field.set(getClass(), Boolean.parseBoolean(value.toString()));
            return;
        }
        field.set(getClass(), value);
    }
}
