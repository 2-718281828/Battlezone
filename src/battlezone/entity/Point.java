package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;

public class Point extends Entity {
    public ID id = ID.Point;
    public Point(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.scale(0.1);
        Console.log("x: " +model.rotationAxis.x);
        Console.log("y: " + model.rotationAxis.y);
        Console.log("z: " + model.rotationAxis.z);
    }
    double t = 0;
    public void logic () {
        position.add(velocity);
        model.move(velocity);
    }
}