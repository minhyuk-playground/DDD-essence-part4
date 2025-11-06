package org.eternity.phone.tracking.domain;

public enum CallStatus {
    IN_PROGRESS,        // 진행중
    NORMAL_CLEARING,    // 통화가 정상적으로 종료됨
    BUSY,               // 수신자가 통화 중
    CALL_REJECTED,      // 수신자가 통화 거부
    NO_ANSWER,          // 수신자가 응답하지 않음
    NETWORK_FAILURE,    // 네트워크 장애
}
