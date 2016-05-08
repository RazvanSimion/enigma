package com.yms.enigma.graph.web.rest.mapper;

import com.yms.enigma.graph.domain.Road;
import com.yms.enigma.graph.web.rest.dto.RoadDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoadMapper {


    RoadDTO roadToRoadDTO(Road road);

    List<RoadDTO> roadsToRoadsDTOs(List<Road> roads);

    //@Mapping(target = "roads", ignore = true)
    Road roadDTOToRoad(RoadDTO roadDTO);

    List<Road> roadsDTOsToRoads(List<RoadDTO> roadDTOs);
}
