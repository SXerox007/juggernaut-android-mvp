package com.skeleton.mvpbase.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by cl-macmini-01 on 11/23/17.
 * The Common Response model class
 */

public class CommonResponse {

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Object data;


    /**
     * Get message from api response
     *
     * @return message message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get status code of api response
     *
     * @return statusCode status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return statusCode + " " + message + "\n" + data;
    }

    /**
     * Parses the response into an instance of provided class
     *
     * @param classRef the class reference
     * @param <T>      the class type
     * @return the parsed response object
     */
    public <T> T toResponseModel(final Class<T> classRef) {
        return new Gson().fromJson(new Gson().toJson(data), classRef);
    }


    /**
     * Parses the response into list of the provided class type
     *
     * @param classRef the class reference
     * @param <T>      the class type
     * @return the parsed response list
     */
    public <T> List<T> toResponseArrayModel(final Class<T> classRef) {
        TypeToken<List<T>> token = new TypeToken<List<T>>() {
        };
        return new Gson().fromJson(new Gson().toJson(data), token.getType());
    }

}
