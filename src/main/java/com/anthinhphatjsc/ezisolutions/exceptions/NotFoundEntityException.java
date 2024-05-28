package com.anthinhphatjsc.ezisolutions.exceptions;

public class NotFoundEntityException extends Exception {

    public NotFoundEntityException(String objectName, Long id) {
        super(String.format("%s với ID %d không tìm thấy", objectName, id));
    }
}
