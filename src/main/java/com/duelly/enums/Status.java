package com.restaurant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    ACTIVE("active"),
    DISABLED("disabled"),
    INACTIVE("inactive");

    @Getter
    private final String status;
}
