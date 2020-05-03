package com.github.philipkoivunen.trendy_trails.helpers;

import com.github.philipkoivunen.trendy_trails.constants.ColorConstants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColorHelper {
    public static int[] resolveColor(String color) {
        int[] rgb;
        switch (color){
            case "cyan": {
                rgb = new int[]{56,255,228};
                break;
            }
            case "aqua": {
                rgb = new int[]{0, 255, 255};
                break;
            }
            case "dark_aqua": {
                rgb = new int[]{0,170,170};
                break;
            }
            case "red": {
                rgb = new int[]{250, 0, 0};
                break;
            }
            case "green": {
                rgb = new int[]{0, 250, 0 };
                break;
            }
            case "dark_blue": {
                rgb = new int[]{0, 0, 250 };
                break;
            }case "blue": {
                rgb = new int[]{89,128,255};
                break;
            }
            case "white": {
                rgb = new int[]{250, 250, 250 };
                break;
            }
            case "black": {
                rgb = new int[]{0, 0, 0 };
                break;
            }
            case "pink": {
                rgb = new int[]{255,10,222};
                break;
            }
            case "peach": {
                rgb = new int[]{255,200,163};
                break;
            }
            case "grey": {
                rgb = new int[]{151,159,168};
                break;
            }
            case "yellow": {
                rgb = new int[]{255,239,92};
                break;
            }
            case "pumpkin": {
                rgb = new int[]{255,133,25};
                break;
            }
            case "magenta": {
                rgb = new int[]{204,10,185};
                break;
            }
            case "gold": {
                rgb = new int[]{255,170,0};
                break;
            }
            case "dark_red": {
                rgb = new int[]{170,0,0};
                break;
            }
            default:
                rgb = new int[]{0, 0, 0};
        }
        return rgb;
    }

    public static List<String> getAllColors() {
        return Stream.of(ColorConstants.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
