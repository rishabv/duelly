package com.restaurant.enums;

public enum ChallengeType {
    MEMBER,
    SPONSOR;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
