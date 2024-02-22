package com.routetitian.locationclustring.controller;

import com.routetitian.locationclustring.model.ClusteredLocation;
import com.routetitian.locationclustring.model.Location;
import com.routetitian.locationclustring.service.ClusteringService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cluster")
public class ClusterController {

    private final ClusteringService clusteringService;

    public ClusterController(ClusteringService clusteringService) {
        this.clusteringService = clusteringService;
    }

    //Todo: DTOs should be seperated from domain --> solutions: define DTO objects and map it using MapStruct
    //Todo: Implement Overall ExceptionHandling and return correct HTTP status code
    //Todo: create Response class for handling resultCode and resultDescription without stack trace and default spring response object
    @PostMapping
    public List<ClusteredLocation> getClusters(@RequestBody List<Location> locations) {
        return clusteringService.getClusters(locations);
    }

}
