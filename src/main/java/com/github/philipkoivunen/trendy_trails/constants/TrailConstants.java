package com.github.philipkoivunen.trendy_trails.constants;

public enum TrailConstants {
        BUBBLE_POP,
        CAMPFIRE_COSY_SMOKE,
        COMPOSTER,
        CLOUD,
        CRIT,
        CRIT_MAGIC,
        DOLPHIN,
        DRAGON_BREATH,
        DRIP_LAVA,
        ENCHANTMENT_TABLE,
        END_ROD,
        FALLING_WATER,
        FALLING_LAVA,
        FALLING_HONEY,
        FIREWORKS_SPARK,
        FLAME,
        HEART,
        LANDING_LAVA,
        LAVA,
        NAUTILUS,
        NOTE,
        PORTAL,
        REDSTONE,
        SNEEZE,
        SPELL,
        SPIT,
        SQUID_INK,
        WHITE_ASH,
        FALLING_OBSIDIAN_TEAR,
        WARPED_SPORE,
        CRIMSON_SPORE;

    public static TrailConstants fromString(String string) {
        for(TrailConstants state : values()) {
            if(state.name().equalsIgnoreCase(string)) {
                return state;
            }
        }
        return null;
    }

}
