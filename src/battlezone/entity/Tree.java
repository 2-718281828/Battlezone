package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Tree extends Entity {
    public ID id= ID.Tree;
    public Tree(Model model, Vector3 vector3, EntityHandler entityHandler) {
        super(model, vector3, entityHandler);
        model.rotate(0, Math.PI);
        model.scale(0.2);
    }
    public void logic() {

    }
}
