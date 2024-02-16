package battlezone.entity;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;

public class Enemy2 extends Entity{
    public ID id = ID.Enemy2;
    public Enemy2(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
    }
    double t = 0;
    public void logic () {
        velocity.x =(Math.sin(t))/5;
        position.add(velocity);
        model.move(velocity);
        t = t+0.1;
    }
}

