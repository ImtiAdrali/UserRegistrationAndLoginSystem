package com.imti.UserRegistrationAndLoginSystem.enums;

public enum AccountStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    PENDING_VERIFICATION("Pending Verification"),
    SUSPENDED("Suspended"),
    LOCKED("Locked"),
    BANNED("Banned"),
    DELETED("Deleted"),
    EXPIRED("Expired");

    private final String displayName;

    AccountStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
