package com.anthinhphatjsc.ezisolutions.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum AppErrors {
    SUCCESS(200, "msg.success", HttpStatus.OK),
    BAD_REQUEST(400, "msg.bad.request", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_PATH(400, "msg.bad.request.path", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(401, "msg.unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "msg.access.you-do-not-have-permission", HttpStatus.FORBIDDEN),
    INTERNAL_SERVER(500, "msg.internal.server", HttpStatus.INTERNAL_SERVER_ERROR),

    BANK_NAME_EXISTED(1000, "msg.bank.bank-name-existed", HttpStatus.BAD_REQUEST),
    BANK_NAME_DUPLICATE(1001, "msg.bank.bank-name-duplicate", HttpStatus.BAD_REQUEST),
    BANK_CODE_EXISTED(1002, "msg.bank.bank-code-existed", HttpStatus.BAD_REQUEST),
    BANK_NOT_FOUND(1003, "msg.bank.bank-not-found", HttpStatus.BAD_REQUEST),
    CAN_NOT_DELETE_BANK(1004, "msg.bank.can-not-delete-bank", HttpStatus.BAD_REQUEST),

    DISTRICT_NOT_FOUND(2000,"district.district-not-found", HttpStatus.BAD_REQUEST),
    PROVINCE_NOT_FOUND(2001,"province.province-not-found", HttpStatus.BAD_REQUEST),

    USER_EXISTED(3000, "msg.user-existed",HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(3001, "msg.sign-up.user-existed", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_EXISTED(3002, "auth.phone-number-existed", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(3003, "auth.email-existed", HttpStatus.BAD_REQUEST),
    LOGIN_FAILED(3005,"user.use-name-or-password-incorrect!", HttpStatus.BAD_REQUEST),
    CREATE_TOKEN_FAIL(3006, "token.generate-token-fail", HttpStatus.BAD_REQUEST),

    PROJECT_TYPE_NOT_FOUND(4000, "project-type.project-type-not-found", HttpStatus.BAD_REQUEST),
    PROJECT_TYPE_NAME_EXISTED(4001, "project-type.project-type-name-existed", HttpStatus.BAD_REQUEST),
    CAN_NOT_DELETE_PROJECT_TYPE(4002, "project-type.project-type-existed", HttpStatus.BAD_REQUEST),

    PROJECT_NOT_FOUND(4003, "project.project-not-found", HttpStatus.BAD_REQUEST),
    PROJECT_EXISTED(4100, "project.project-existed", HttpStatus.BAD_REQUEST),
    DUPLICATE_PROJECT_NAME(4100, "project.project-name-duplicate", HttpStatus.BAD_REQUEST),
    CAN_NOT_DELETE_PROJECT(4100, "project.can-not-delete", HttpStatus.BAD_REQUEST),

    AREA_NOT_FOUND(4200, "area-not-found", HttpStatus.BAD_REQUEST),
    DUPLICATE_AREA_NAME(4201, "area.area-duplicate", HttpStatus.BAD_REQUEST),

    LAND_NOT_FOUND(4300, "land.land-not-found", HttpStatus.BAD_REQUEST),
    LAND_EXISTED(4301, "land.land-existed", HttpStatus.BAD_REQUEST),
    DUPLICATE_LAND_NAME(4302, "land.land-duplicate", HttpStatus.BAD_REQUEST),
    STATUS_LAND_NOT_FOUND(4303, "land.status-land-not-found", HttpStatus.BAD_REQUEST),
    CAN_NOT_BUY_LAND(4304, "land.can-not-buy-this-land", HttpStatus.BAD_REQUEST),

    INVESTOR_NOT_FOUND(4400, "investor.investor-not-found", HttpStatus.BAD_REQUEST),

    TRANSACTION_NOT_FOUND(4500, "transaction.transaction-not-found", HttpStatus.BAD_REQUEST),

    OTP_INCORRECT(9000, "otp.otp-incorrect", HttpStatus.BAD_REQUEST),
    UPLOAD_FAIL(9100,"upload-file.upload-file-fail", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9200, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    RESPONSE_NOT_FOUND(10404, "Response not found", HttpStatus.BAD_REQUEST),
    ID_NOT_MATCH(10400, "Id not match", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String description;
    private final HttpStatusCode statusCode;

    AppErrors(int code, String description, HttpStatusCode statusCode) {
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }

}
