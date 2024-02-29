package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;
import util.Console;

public class Rock extends Entity {
    public ID id= ID.Rock;
    Vector3 pos;
    Camera camera;
    public Rock(Model model, Vector3 vector3, EntityHandler entityHandler, Camera camera) {
        super(model, vector3, entityHandler);
        this.camera=camera;
    }
    public void logic() {
        if (!(Math.abs(camera.position.x- position.x)<1&&Math.abs(camera.position.z- position.z)<1)){
            pos =new Vector3(camera.position);
        }
        else {
            Console.log("działą");
            camera.position =new Vector3(pos);
        }
    }
}
