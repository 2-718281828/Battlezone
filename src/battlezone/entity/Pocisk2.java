package battlezone.entity;

import battlezone.main.MainRenderer;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

public class Pocisk2 extends Entity {
    public ID id = ID.Pocisk2;
    double lifetime = 0;
    static double speed = 1; //ustalamy domyślną prędkość obiektu
	Camera camera;
    public Pocisk2(Model model, Vector3 position, EntityHandler entityHandler, Vector3 velocity, Camera camera) {
        super(model, position, entityHandler);
	this.velocity = velocity;
	this.camera = camera;
	model.rotate(1, camera.rotation.x);
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
    }
}
