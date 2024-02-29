package battlezone.entity;

import battlezone.main.MainRenderer;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

public class Bullet1 extends Entity {
    public ID id = ID.Bullet1;
    double lifetime = 0;
    double speed = 1; //ustalamy domyślną prędkość obiektu
    Camera camera;
    public Bullet1(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
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
            lifetime = 0;
        }
        position.add(velocity);
        model.move(velocity);
	updateHitbox();
    }
}
