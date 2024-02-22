package com.routetitian.locationclustring.webclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@Component
@EnableConfigurationProperties
@ConfigurationProperties(value="route.titan", ignoreInvalidFields = false)
public class OsrmClient {

    private String osrmHostUrl;
    private final RestTemplate restTemplate;
    private final String OSRM_TABLE_SERVICE_PATH = "/table/v1/driving";

    public OsrmClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setOsrmHostUrl(String osrmHostUrl) {
        this.osrmHostUrl = osrmHostUrl;
    }

    public OsrmResponse getDistanceMatrix(List<OsrmPointRequest> points){
        //Todo: OSRM_Table doesn't accept a lot of number of points in url it should be modified
        return restTemplate.getForObject(urlBuilder(points), OsrmResponse.class);
    }

    private String urlBuilder(List<OsrmPointRequest> points) {
        String pathVariable = points.stream()
                .map(point -> point.getLatitude() + "," + point.getLongitude())
                .collect(Collectors.joining(";"));

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(osrmHostUrl + OSRM_TABLE_SERVICE_PATH + "/" + pathVariable)
                .queryParam("annotations", OsrmAnnotation.distance.name());
        return uriBuilder.toUriString();
    }

}
