package com.task.renderservice.service;

import com.task.renderservice.entity.ImageEntity;
import com.task.renderservice.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DatabaseServiceImpl  implements  DatabaseService{

    @Autowired
    private ObjectRepository objectRepository;

    public Flux<ImageEntity> getObjectsInBoundingBox(double minLat, double minLon, double maxLat, double maxLon) {
        Polygon bbox = new Polygon(
                new Point(minLat, minLon),
                new Point(maxLat, minLon),
                new Point(maxLat, maxLon),
                new Point(minLat, maxLon),
                new Point(minLat, minLon)
        );

        return objectRepository.findByLocationWithin(bbox);
    }
}
