package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
public class Point extends Entity {
    public ID id = ID.Point;
    public Point(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.scale(0.1);
    }
    double t = 0;
    public void logic () {
        position.add(velocity);
        model.move(velocity);
    }
}