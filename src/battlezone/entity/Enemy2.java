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
    double delta=0;
    double beta=alfa; //kąt względem którego obiekt porusza sie wzgłedem osi x
    public Enemy2(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(1, -alfa);
        model.scale(0.2);
        this.camera = camera;

    }
    Random random = new Random();
    long time = 0;
    public void logic() {
        time++;
        position.add(velocity);
        model.move(velocity);
	Vector3 dst = new Vector3(position.x - camera.position.x, 0, position.z-camera.position.z);
	if (dst.magnitude() < 9){ //to "25" to odległość od gracza ^2
            //określa odległość Enemy2 od gracza, dzieli logikę na części "walka z graczem" i "jazda losowa"
	model.rotate(1, -beta);
	   beta = Math.atan2(dst.z, dst.x);
	  model.rotate(1, beta); 
        }
        else{
            if (time/60>=5){
                alfa=random.nextDouble(0.25*Math.PI);
                model.rotate(1,alfa);
                time=0;
                beta+=alfa;
            }
	}
	velocity.x=Math.cos(beta)*speed;
	velocity.z=Math.sin(beta)*speed;

    }
}
