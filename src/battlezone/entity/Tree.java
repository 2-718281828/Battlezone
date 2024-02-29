package battlezone.entity;

import battlezone.main.MainLogic;
import battlezone.main.MainRenderer;
import battlezone.main.Sounds;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;
import renderer.Model;
import util.Console;

import java.awt.*;
import java.io.File;

public class Tree extends Entity {
    Camera camera;
    public ID id= ID.Tree;
    Vector3 pos;
    public Tree(Model model, Vector3 vector3, EntityHandler entityHandler, Camera camera ) {
        super(model, vector3, entityHandler);
        model.rotate(0, Math.PI);
        model.scale(0.2);
        this.camera=camera;
    }
    public void logic() {
        updateHitbox();
        if (!(Math.abs(camera.position.x- position.x)<1&&Math.abs(camera.position.z- position.z)<1)){
            pos =new Vector3(camera.position);
        }
        else {
            Console.log("działą");
            camera.position =new Vector3(pos);
        }
        for (int i = 0; i < entityHandler.entities.size(); i++) {
            if (entityHandler.entities.get(i) != this) {
                if (collision(entityHandler.entities.get(i).hitbox)) {
                    if(entityHandler.entities.get(i).getClass()==Bullet1.class) {
                        util.Console.log("Kolizja z pociskiem");
                        entityHandler.entities.get(i).model.remove(((MainRenderer)camera.renderer).triangles);
                        entityHandler.entities.remove(entityHandler.entities.get(i));
                        Sounds.play("/boom.wav");
                        model.remove(((MainRenderer)camera.renderer).triangles);
                        entityHandler.entities.remove(this);
                    }
                }
            }
        }
    }
}
