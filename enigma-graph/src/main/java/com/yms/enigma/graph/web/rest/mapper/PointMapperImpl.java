package com.yms.enigma.graph.web.rest.mapper;

import com.yms.enigma.graph.domain.Point;
import com.yms.enigma.graph.domain.Road;
import com.yms.enigma.graph.web.rest.dto.PointDTO;
import com.yms.enigma.graph.web.rest.dto.RoadDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
public class PointMapperImpl {

    public PointDTO pointToPointDTO(Point point) {
        return null;
    }

    public List<RoadDTO> roadsToRoadsDTOs(List<Road> roads) {
        List<RoadDTO>  roadDTOs = new ArrayList<>();
        for(Road road : roads) {
            RoadDTO roadDTO = new RoadDTO(road);
            roadDTOs.add(roadDTO);

        }
        return roadDTOs;
    }

    //@Override
    public List<PointDTO> pointsToPointsDTOs(List<Point> points) {
        List<PointDTO> pointDtos = new ArrayList<>();
        for(Point point : points) {
            List roads = new ArrayList<>();
            if(point.getRoads()!=null)
                roads.addAll(point.getRoads());
            PointDTO pointDto = new PointDTO(point.getName(), point.getNodeId(), point.getQueryId(), point.getLat(), point.getLng(), roadsToRoadsDTOs(roads));

            pointDtos.add(pointDto);
        }
        return pointDtos;
    }

   // @Override
    public Point pointDTOToPoint(PointDTO pointDTO) {
        return null;
    }

   // @Override
    public List<Point> pointsDTOsToPoints(List<PointDTO> pointDTOs) {
        return null;
    }
}
