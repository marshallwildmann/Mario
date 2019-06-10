package com.company;

public enum BoxState {
    FREE,
    ABOVE,
    BELOW,
    TOTHERIGHT,
    TOTHELEFT;


    public String toString() {
        switch (this){
            case FREE: return "Free";
            case ABOVE: return "Above";
            case BELOW: return "Below";
            case TOTHERIGHT: return "Totheright";
            case TOTHELEFT: return "Totheleft";
            default: return null;
        }
    }
}