package org.example;

import java.awt.*;

public class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public String toSvg() {
        return decoratedShape.toSvg();
    }

}