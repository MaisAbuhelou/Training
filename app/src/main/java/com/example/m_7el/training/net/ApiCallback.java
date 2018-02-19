package com.example.m_7el.training.net;

public interface ApiCallback<Response, Error> {
    void onSuccess(Response response);
    void onError(Error error);
}
