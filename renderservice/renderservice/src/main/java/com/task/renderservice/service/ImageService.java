package com.task.renderservice.service;

import reactor.core.publisher.Mono;

public interface ImageService {

    Mono<byte[]> generateImage(int width, int height, double minLat, double minLon, double maxLat, double maxLon);
}
