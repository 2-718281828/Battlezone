package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Rock extends Entity {
    public ID id= ID.Rock;
    public Rock(Model model, Vector3 vector3, EntityHandler entityHandler) {
        super(model, vector3, entityHandler);
    }
    public void logic() {

    }
}
