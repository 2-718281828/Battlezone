package battlezone.entity;

import battlezone.main.MainRenderer;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;
public class Pocisk2 extends Entity{
    public ID id = ID.Pocisk2;
    double lifetime = 0;
    boolean life = false;
    double speed = 0.1; //ustalamy domyślną prędkość obiektu
    Camera camera;
    Enemy2 enemy;
    public Pocisk2(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        this.camera = camera;
        Vector3 dst = new Vector3(camera.position);
        dst.subtract(position);
        dst.y = 0;
        velocity.x = (dst.x/dst.magnitude())*speed;
        velocity.z = (dst.z/dst.magnitude())*speed;
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
