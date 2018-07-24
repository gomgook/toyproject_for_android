package com.stewhouse.gresturlconnection;

/**
 * Created by Gomguk on 2016-07-10.
 */
public class GException extends Exception {
    ErrorType mErrorType = null;

    public enum ErrorType {
        RESPONSE_CODE_ERROR, SCHEME_NOT_SUPPORTED, REQUEST_TYPE_NOT_SUPPORTED, TIMEOUT_VALUE_INVALID, LISTENER_NULL_POINTER, UNKNOWN_ERROR
    }

    public static GException makeGException(ErrorType errorType) {
        Throwable t = new Throwable(getErrorMessage(errorType));

        return new GException(errorType, t);
    }

    public GException(ErrorType errorType, Throwable throwable) {
        super(errorType.toString(), throwable);

        mErrorType = errorType;
    }

    private static String getErrorMessage(ErrorType errorType) {
        switch (errorType) {
            case LISTENER_NULL_POINTER:
                return "GRESTURLConnectionListener is null.\nGRESTURLConnectionListener should be allocated before GRESTURLConnection is used.";
            case SCHEME_NOT_SUPPORTED:
                return "Not supported scheme. We support HTTP or HTTPS only.";
            case REQUEST_TYPE_NOT_SUPPORTED:
                return "Not supported REST request type. We support GET, POST, PUT, DELETE only.";
            case RESPONSE_CODE_ERROR:
                return "The connection response code is not 200.";
            case TIMEOUT_VALUE_INVALID:
                return "The timeout value is not invalid. The timeout value should be over 0.";
            default:
                return "Unknown error occurred.";
        }
    }
}
