package com.routetitian.locationclustring.webclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OsrmPointRequest {
    private String latitude;
    private String longitude;
}
