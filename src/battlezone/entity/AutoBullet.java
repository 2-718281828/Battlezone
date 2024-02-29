package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

public class AutoBullet extends Entity {
    Camera camera;
    public AutoBullet(Model model, Vector3 vector3, EntityHandler entityHandler, Camera camera) {
        super(model, vector3, entityHandler);
        this.camera=camera;
    }
    double beta;
    double speed= 0.1;
    public void logic(){
        position.add(velocity);
        model.move(velocity);
        // odległość do kamery
        Vector3 dst = new Vector3(camera.position);
        dst.subtract(position);
        dst.y = 0;
        velocity.x = (dst.x / dst.magnitude()) * speed;
        velocity.z = (dst.z / dst.magnitude()) * speed; // dzielimy przez wartość wektora aby otrzymać składową wektora jednostkowego skierowanego w stronę kamery a następnie mnożymy tę składową razy prędkość
        model.rotate(1, -beta); // obracamy obiekt tak, aby był obrócony domyślnie
        beta = -Math.atan2(dst.z, dst.x); // kąt pod jakim ma się poruszać czołg aby szedł do kamery
        model.rotate(1, beta); // obracamy obiekt o ten nowy kąt
    }
}
