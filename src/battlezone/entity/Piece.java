package battlezone.entity;

import battlezone.main.MainRenderer;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

import java.util.Random;

public class Piece extends Entity {
    public ID id = ID.Piece;
    double alfa = 0.25*Math.PI;
    double speed = 0.075;
    Camera camera;
    public Piece(Model model, Vector3 vector3, EntityHandler entityHandler, Camera camera) {
        super(model, vector3, entityHandler);
        this.camera=camera;
    }
    Random random = new Random();
    double beta = random.nextDouble(2*Math.PI);
    public void logic() {
        velocity.x=Math.sin(beta)*0.1;
        velocity.z=Math.cos(beta)*0.1;
        velocity.y=Math.sin(alfa)*0.1;
        model.rotate(0, 0.1);
        model.rotate(1, 0.1);
        model.rotate(2, 0.1);
        position.add(velocity);
        model.move(velocity);
        alfa -= 0.5*Math.PI/120;
        if (alfa<=-0.25*Math.PI){
            model.remove(((MainRenderer) camera.renderer).triangles);
            entityHandler.entities.remove(this);
        }
    }
}
