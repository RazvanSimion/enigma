package com.yms.enigma.graph.web.rest.mapper;

import com.yms.enigma.graph.domain.Point;
import com.yms.enigma.graph.web.rest.dto.PointDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PointMapper {


    PointDTO pointToPointDTO(Point point);

    List<PointDTO> pointsToPointsDTOs(List<Point> points);

    //@Mapping(target = "points", ignore = true)
    Point pointDTOToPoint(PointDTO pointDTO);

    List<Point> pointsDTOsToPoints(List<PointDTO> pointDTOs);
}
