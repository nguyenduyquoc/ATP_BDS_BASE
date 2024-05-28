package com.anthinhphatjsc.ezisolutions.utils;

public class Constants {

    public static final String REQUEST_MAPPING_PREFIX = "/api/modules";
    public static final String VERSION_API_V1 = "/v1";
    public static final String VERSION_API_V2 = "/v2";

    public static final String ADMIN_MODULE_PREFIX = "/admin";
    public static final String CHAT_MODULE_PREFIX = "/chat";
    public static final String PASSPORT_MODULE_PREFIX = "/passport";


    public interface ROLE {
        short ADMIN = 0;
        short EMPLOYEE = 1;
    }

    public interface CUSTOMER_PROPERTY_TYPE {
        short TEXT = 0;
        short NUMBER = 1;
        short HTML = 2;
    }

    public interface CUSTOMER_INFO_TYPE {
        short TEXT = 0;
        short NUMBER = 1;
        short HTML = 2;
    }

    public interface STATUS_lAND {
    //  short COMING_SOON = 0;
        short IN_PROGRESS = 0;
        short LOCKING = 1;
        short LOCKED = 2;
    }



    public interface STATUS_TRANSACTION {
        short WAIT_FOR_CONFIRMATION = 0;
        short PAYMENT_SUCCESS = 1;
        short PAYMENT_FAILED = 2;
    }

}
