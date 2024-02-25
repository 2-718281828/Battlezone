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
    Camera camera;Enemy2 enemy;
    public Pocisk2(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        this.camera = camera;
        model.move(Enemy2.position);
//miejsce na celowanie na bazie linijek 19 i 20
        velocity.z = (Math.sin(camera.position.z-position.z)-Math.PI)*speed;
        velocity.x = (Math.cos(camera.position.x-position.x)-Math.PI)*speed;
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
