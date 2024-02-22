package com.routetitian.locationclustring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String id;
    private String city;
    private String zip;
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lng")
    private String longitude;


}
