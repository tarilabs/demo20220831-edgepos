package org.drools.hackfest2022;

import java.util.stream.Stream;

public class Utils {

    public static Number sum(Number... values) {
        return Stream.of(values).mapToDouble(Number::doubleValue).sum();
    }
    
    private Utils() {
        // only static methods
    }
}
