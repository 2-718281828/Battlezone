package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;
public class Enemy1 extends Entity {
    public ID id = ID.Enemy1;
    public Enemy1(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
    }
    public void logic () {
        position.add(velocity);
        model.move(velocity);
    }
    Random random = new Random();
    public void AI() {
    	
    }
}
