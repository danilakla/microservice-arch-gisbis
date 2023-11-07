package com.task.renderservice.service;

import com.task.renderservice.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements  ImageService {

    @Autowired
    private DatabaseService databaseService;

    public Mono<byte[]> generateImage(int width, int height, double minLat, double minLon, double maxLat, double maxLon) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        Flux<ImageEntity> objectsInBbox = databaseService.getObjectsInBoundingBox(minLat, minLon, maxLat, maxLon);

        objectsInBbox.subscribe(object -> {
            Point location = object.getLocation();
            Color color = Color.decode(object.getColor());

            int x = (int) ((location.getX() - minLon) / (maxLon - minLon) * width);
            int y = (int) ((maxLat - location.getY()) / (maxLat - minLat) * height);

            graphics.setColor(color);
            graphics.fillRect(x, y, 5, 5);

        });

        graphics.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = baos.toByteArray();

        return Mono.just(imageBytes);
    }
}
