package com.yms.enigma.graph.web.rest.dto;

import com.yms.enigma.graph.domain.Road;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Set;

/**
 * Created by Razvan.Simion on 4/19/2016.
 */
public class PointDTO {
    Long nodeId;
    List<RoadDTO> roads;
    String name;
    Long queryId;
    double lat;
    double lng;

    public PointDTO() {
    }

    public PointDTO(String name, Long nodeId, Long queryId, double lat, double lng, List<RoadDTO> roads) {
        this.name = name;
        this.nodeId = nodeId;
        this.queryId = queryId;
        this.roads = roads;
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getQueryId() {
        return queryId;
    }

    public void setQueryId(Long queryId) {
        this.queryId = queryId;
    }

    public List<RoadDTO> getRoads() {
        return roads;
    }

    public void setRoads(List<RoadDTO> roads) {
        this.roads = roads;
    }
}
