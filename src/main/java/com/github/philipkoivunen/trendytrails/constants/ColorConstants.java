package com.github.philipkoivunen.trendytrails.constants;

public enum ColorConstants {
    AQUA,
    BLACK,
    BLUE,
    CYAN,
    DARK_AQUA,
    DARK_BLUE,
    DARK_RED,
    GOLD,
    GREEN,
    GREY,
    MAGENTA,
    PEACH,
    PINK,
    PUMPKIN,
    RED,
    WHITE,
    YELLOW
    ;

    public static ColorConstants fromString(String string) {
        for(ColorConstants state : values()) {
            if(state.name().equalsIgnoreCase(string)) {
                return state;
            }
        }
        return null;
    }

}
