package com.task.renderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

@Table(schema = "objects")
@Builder
public class ImageEntity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Id
    private Long id;
    private Point location;
    private String color;


}
