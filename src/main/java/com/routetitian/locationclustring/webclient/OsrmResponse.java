package com.routetitian.locationclustring.webclient;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


//TODO: this model dosn't support duration in this step
@Getter
@Setter
public class OsrmResponse {
    private List<Source> sources;
    private List<Destination> destinations;
    private List<List<Double>> distances;
    private String code;

    @Getter
    @Setter
    private static class Source {
        private List<Double> locations;
        private String hint;
        private String name;
    }
    @Getter
    @Setter
    private static class Destination {
        private List<Double> locations;
        private String hint;
        private String name;
        private String distance;
    }
}
