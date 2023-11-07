package com.task.renderservice.controller;
import com.task.renderservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/render", produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<byte[]> renderImage(
            @RequestParam int width,
            @RequestParam int height,
            @RequestParam double minLat,
            @RequestParam double minLon,
            @RequestParam double maxLat,
            @RequestParam double maxLon
    ) {
        return imageService.generateImage(width, height, minLat, minLon, maxLat, maxLon);
    }
}
