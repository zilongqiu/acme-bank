package com.acmebank.account.dto.response;

import com.acmebank.account.util.DateUtils;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Response<T> {

    private Status status;
    private T data;
    private Object errors;

    public enum Status {
        OK, BAD_REQUEST, EXCEPTION, NOT_FOUND
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(Status.OK);

        return response;
    }

    public static <T> Response<T> badRequest() {
        Response<T> response = new Response<>();
        response.setStatus(Status.BAD_REQUEST);

        return response;
    }

    public static <T> Response<T> exception() {
        Response<T> response = new Response<>();
        response.setStatus(Status.EXCEPTION);

        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(Status.NOT_FOUND);

        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ResponseError error = new ResponseError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(DateUtils.today());
        setErrors(error);
    }
}
