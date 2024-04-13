package com.duelly.enums;

public enum ChallengeType {
    MEMBER,
    SPONSOR;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
