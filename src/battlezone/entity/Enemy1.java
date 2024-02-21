package battlezone.entity;

import battlezone.main.MainRenderer;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

import java.util.Random;

public class Enemy1 extends Entity {
    public ID id = ID.Enemy1;
    double lifetime = 0;
    double speed = 0.1; //ustalamy domyślną prędkość obiektu
    Camera camera;
    public Enemy1(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        model.scale(0.2);
        this.camera = camera;
        velocity = new Vector3(Math.cos(camera.rotation.x)*speed, Math.sin(camera.rotation.x)*speed, 0);
    }

    Random random = new Random();
    double time = System.currentTimeMillis()/1000 ;
    public void logic() {
        lifetime++;
        if (lifetime >= 60 * 5) {
            entityHandler.entities.remove(this);
            model.remove(((MainRenderer) camera.renderer).triangles);
        }
        position.add(velocity);
        model.move(velocity);
    }
}
