package com.task.renderservice.repository;

import com.task.renderservice.entity.ImageEntity;
import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ObjectRepository extends ReactiveCrudRepository<ImageEntity, Long> {

    Flux<ImageEntity> findByLocationWithin(Polygon location);
}
