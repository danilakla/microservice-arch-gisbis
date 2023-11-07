package com.task.renderservice.service;

import com.task.renderservice.entity.ImageEntity;
import reactor.core.publisher.Flux;

public interface DatabaseService {

    Flux<ImageEntity> getObjectsInBoundingBox(double minLat, double minLon, double maxLat, double maxLon);
}
