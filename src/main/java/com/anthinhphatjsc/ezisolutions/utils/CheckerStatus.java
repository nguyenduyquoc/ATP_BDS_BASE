package com.anthinhphatjsc.ezisolutions.utils;

import java.util.Arrays;
import java.util.List;

public class CheckerStatus {

    public static boolean findStatusLand(Short status) {
        List<Short> validStatusLand = Arrays.asList(
                Constants.STATUS_lAND.IN_PROGRESS,
                Constants.STATUS_lAND.LOCKING,
                Constants.STATUS_lAND.LOCKED
        );
        return validStatusLand.contains(status);
    }

    public static boolean findStatusTransaction(Short status) {
        List<Short> validStatusTransactions = Arrays.asList(
                Constants.STATUS_TRANSACTION.WAIT_FOR_CONFIRMATION,
                Constants.STATUS_TRANSACTION.PAYMENT_SUCCESS,
                Constants.STATUS_TRANSACTION.PAYMENT_FAILED
        );
        return validStatusTransactions.contains(status);
    }

    public static boolean findCustomerPropertyType(Short status) {
        List<Short> validCustomerPropertyType = Arrays.asList(
                Constants.CUSTOMER_PROPERTY_TYPE.TEXT,
                Constants.CUSTOMER_PROPERTY_TYPE.NUMBER,
                Constants.CUSTOMER_PROPERTY_TYPE.HTML
        );
        return validCustomerPropertyType.contains(status);
    }

    public static boolean findCustomerInfoType(Short status) {
        List<Short> validCustomerInfoType = Arrays.asList(
                Constants.CUSTOMER_INFO_TYPE.TEXT,
                Constants.CUSTOMER_INFO_TYPE.NUMBER,
                Constants.CUSTOMER_INFO_TYPE.HTML
        );
        return validCustomerInfoType.contains(status);
    }
}
