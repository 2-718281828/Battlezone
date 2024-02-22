package battlezone.entity;

import battlezone.main.Main;
import battlezone.main.MainLogic;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;
import renderer.Renderer;

import java.util.Random;

public class Enemy2 extends Entity {
    public ID id = ID.Enemy2;
    double t = 0;
    Camera camera;
    double speed = 0.03; //ustalamy domyślną prędkość obiektu
    double alfa=0; //kąt względem którego obiekt porusza sie wzgłedem osi x
    double beta=0; //kąt względem którego obiekt porusza sie wzgłedem osi x
    public Enemy2(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(1, -alfa);
        model.scale(0.2);
        this.camera = camera;

    }
    Random random = new Random();
    double time = System.currentTimeMillis()/1000d;
    public void logic() {
        position.add(velocity);
        model.move(velocity);
        if ((position.x-camera.position.x)*(position.x-camera.position.x)+(position.z-camera.position.z)*(position.z-camera.position.z)<64){ //to "25" to odległość od gracza ^2
            //określa odległość Enemy2 od gracza, dzieli logikę na części "walka z graczem" i "jazda losowa"

            velocity.x = 0;
            velocity.z =0;
        }
        else {
            velocity.x = speed * Math.cos(beta);
            velocity.z = speed * Math.sin(beta);
            if (System.currentTimeMillis() / 1000d - time >= 5) {
                alfa = random.nextDouble(0.25*Math.PI);
                time = System.currentTimeMillis()/1000d;
                model.rotate(1, -alfa);
                beta =beta+alfa;
            }
        }
    }
}
