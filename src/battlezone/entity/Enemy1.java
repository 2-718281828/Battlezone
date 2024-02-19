package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;
import java.util.Random;

public class Enemy1 extends Entity {
    public ID id = ID.Enemy1;
    public Enemy1(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(2, Math.PI);
        model.rotate(1, Math.PI/2);

    }
    double t = 0;
    public void logic () {
        position.add(velocity);
        model.move(velocity);
        velocity.x = Math.sin(t)/5;
        velocity.z = Math.cos(t)/5;
        model.rotate(1, 0.01);
        t= t+0.01;

    }
    Random random = new Random();
    public void AI() {
    	
    }
}
