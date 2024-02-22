package com.routetitian.locationclustring.service;

import com.routetitian.locationclustring.model.ClusteredLocation;
import com.routetitian.locationclustring.model.Location;
import com.routetitian.locationclustring.webclient.OsrmClient;
import com.routetitian.locationclustring.webclient.OsrmPointRequest;
import org.springframework.stereotype.Service;
import smile.clustering.CLARANS;
import smile.clustering.PartitionClustering;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ClusteringService {

    private final OsrmClient osrmClient;
    List<OsrmPointRequest> points = new ArrayList<OsrmPointRequest>();
    List<List<Double>> distances;
    private final Integer  NUMBER_OF_CLUSTERS = 7;

    public ClusteringService(OsrmClient osrmClient) {
        this.osrmClient = osrmClient;
    }


    public List<ClusteredLocation> getClusters(List<Location> locations) {
        //Todo: exception Handling: length of location is less than number of clusters;
        distances = getDistanceMatrix(locations);
        var clusters = getClusteredLocation();
        var clusteredPoints = mapClusteredPoints(clusters, locations);

        //Todo:SaveImage(clusteredPoints);

        return clusteredPoints;
    }

    private List<ClusteredLocation> mapClusteredPoints(List<Integer> clusters,
                                                       List<Location> locations) {
        List<ClusteredLocation> clusteredLocations = new ArrayList<>();
        for(int i = 0; i < clusters.size(); i++) {
            clusteredLocations.add(new ClusteredLocation(
                    locations.get(i),
                    clusters.get(i).toString())
            );
        }
        return clusteredLocations;
    }

    private List<List<Double>> getDistanceMatrix(List<Location> locations) {
        locations.forEach(location ->
                points.add(new OsrmPointRequest(
                        location.getLatitude(),location.getLongitude()))
        );
        return osrmClient.getDistanceMatrix(points).getDistances();
    }

    private List<Integer> getClusteredLocation(){
        var observers = IntStream.range(0,points.size());
        Integer[] observerArray = observers.boxed().toArray(Integer[]::new);

        var clusters = PartitionClustering
                .run(20, () ->
                        CLARANS.fit(observerArray, (Integer x, Integer y) ->
                                distances.get(x).get(y) , NUMBER_OF_CLUSTERS,2));

        return Arrays.stream(clusters.y).boxed().collect(Collectors.toList());
    }
}
