package com.example.m_7el.training.country;

public interface ApiCallback<Response, Error> {
    void onSuccess(Response response);
    void onError(Error error);
}
