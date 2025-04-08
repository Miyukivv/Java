package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SvgScene scene = SvgScene.getInstance();

        Ellipse sun = new Ellipse(
                new Vec2(400, 120),
                60,
                60,
                new Style("#FFD700", "#FFA500", 3)
        );

        GradientFillShapeDecorator.Builder sunGradBuilder = new GradientFillShapeDecorator.Builder()
                .stopAdd(0.0, "#FFFACD")    // jasnożółty na początku
                .stopAdd(0.8, "#FFD700");  // złoty dalej
        sunGradBuilder.build();

        Shape sunWithGradient = new GradientFillShapeDecorator(sun);

        Shape sunShadowed = new DropShadowDecorator(sunWithGradient);
        scene.addShape(sunShadowed);


        ArrayList<Vec2> mountainPoints = new ArrayList<>();
        mountainPoints.add(new Vec2(50, 350));
        mountainPoints.add(new Vec2(200, 100));
        mountainPoints.add(new Vec2(350, 350));
        Polygon mountains = new Polygon(mountainPoints, new Style("#8B4513", "#654321", 3));

        TransformationDecorator.Builder mountainTransformBuilder = new TransformationDecorator.Builder()
                .scale(new Vec2(1, 2))
                .rotate(5, new Vec2(200, 200));

        Shape transformedMountains = mountainTransformBuilder.build(mountains);
        Shape mountainsWithShadow = new DropShadowDecorator(transformedMountains);
        scene.addShape(mountainsWithShadow);


        ArrayList<Vec2> housePoints = new ArrayList<>();
        housePoints.add(new Vec2(500, 300));
        housePoints.add(new Vec2(500, 200));
        housePoints.add(new Vec2(400, 200));
        housePoints.add(new Vec2(400, 300));
        Polygon house = new Polygon(housePoints, new Style("#FFFAF0", "#708090", 2));

        ArrayList<Vec2> roofPoints = new ArrayList<>();
        roofPoints.add(new Vec2(400, 200));
        roofPoints.add(new Vec2(450, 140));
        roofPoints.add(new Vec2(500, 200));
        Polygon roof = new Polygon(roofPoints, new Style("#8B0000", "#2F4F4F", 2));

        Shape houseShadowed = new DropShadowDecorator(house);
        Shape roofShadowed = new DropShadowDecorator(roof);

        scene.addShape(houseShadowed);
        scene.addShape(roofShadowed);


        Segment path = new Segment(
                new Style("#000000", 4),
                new Vec2(450, 300),
                new Vec2(450, 370)
        );
        scene.addShape(path);

        /*
         */
        scene.saveToFile("Scene.svg");
        scene.saveHtml("Scene.html");

        System.out.println("Scena została zapisana do plików Scene.svg oraz Scene.html");
    }
}
