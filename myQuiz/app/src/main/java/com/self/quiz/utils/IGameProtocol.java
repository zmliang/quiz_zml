package com.self.quiz.utils;

/**
 * Created by zmliang on 2018/7/19.
 */

public interface IGameProtocol {
    String SOCKET_OPEN = "open";
    String SOCKET_CLOSE = "close";
    String SOCKET_MSG = "message";

    String PK_REQUEST = "pk_request";
    String PK_RESPONSE_SUCCESS = "pk_response_success";
    String ERROR = "error";
    String PK_END = "pk_end";
    String UNKNOWN = "unknown";
    String PK_END_CONFIRM = "pk_end_confirm";
}
