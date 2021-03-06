package com.github.philipkoivunen.trendy_trails.helpers;

import com.github.philipkoivunen.trendy_trails.constants.TrailConstants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrailHelper {
    public static List<String> getAllTrails() {
        return Stream.of(TrailConstants.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
