package battlezone.entity;

import battlezone.main.KeyHandler;
import battlezone.main.MainRenderer;
import com.sun.source.doctree.AttributeTree;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Enemy1 extends Entity {
    public ID id = ID.Enemy1;
    double lifetime = 0;
    boolean life = false;
    double speed = 0.1; //ustalamy domyślną prędkość obiektu
    Camera camera;
    public Enemy1(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        //model.rotate(1, 0.5*Math.PI);
        this.camera = camera;
        velocity = new Vector3(Math.sin(-camera.rotation.x)*speed, 0, Math.cos(-camera.rotation.x)*speed);
        model.rotate(1, -camera.rotation.x);
    }

    public void logic() {
        lifetime++;
        if (lifetime >= 60 * 5) {
            entityHandler.entities.remove(this);
            model.remove(((MainRenderer) camera.renderer).triangles);
            life = false;
            lifetime = 0;
        }
        else{
            life= true;
        }
        position.add(velocity);
        model.move(velocity);
    }
}
