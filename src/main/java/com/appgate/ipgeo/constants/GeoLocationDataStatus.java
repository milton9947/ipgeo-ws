package com.appgate.ipgeo.constants;

public enum GeoLocationDataStatus {
    LOADED('L'), VALIDATED('V');
    private char status;

    GeoLocationDataStatus(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }
}
