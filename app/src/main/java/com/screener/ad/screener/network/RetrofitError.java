package com.screener.ad.screener.network;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Maps RetrofitError to be used with Retrofit2
 * This is meant to be an initial step as we transition to our new architecture
 */
public class RetrofitError extends RuntimeException {
    public static RetrofitError httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitError(message, url, response, Kind.HTTP, null, retrofit);
    }

    public static RetrofitError networkError(String url, IOException exception) {
        return new RetrofitError(exception.getMessage(), url, null, Kind.NETWORK, exception, null);
    }

    public static RetrofitError unexpectedError(String url, Throwable exception) {
        return new RetrofitError(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    /** Identifies the event kind which triggered a {@link RetrofitError}. */
    public enum Kind {
        /** An {@link IOException} occurred while communicating to the server. */
        NETWORK,
        /** A non-200 HTTP status code was received from the server. */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;

    RetrofitError(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    /** The request URL which produced the error. */
    public String getUrl() {
        return url;
    }

    /** Response object containing status code, headers, body, etc. */
    public Response getResponse() {
        return response;
    }

    /** The event kind which triggered this error. */
    public Kind getKind() {
        return kind;
    }

    /** The Retrofit this request was executed on */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws RuntimeException if unable to convert the body to the specified {@code type}.
     */
    public <T> T getBodyAs(Class<T> type) {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        try {
            return converter.convert(response.errorBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMessage() {
        return "";
    }
}