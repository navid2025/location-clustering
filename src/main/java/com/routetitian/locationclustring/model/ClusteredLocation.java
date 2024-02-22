package com.routetitian.locationclustring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClusteredLocation extends Location {

    private String cluster;

    public ClusteredLocation(Location location, String cluster) {
        super(location.getId(), location.getCity(),
                location.getZip(), location.getLatitude(),
                location.getLongitude());
        this.cluster = cluster;
    }
}
