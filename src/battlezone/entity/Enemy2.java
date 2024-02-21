package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

import java.util.Random;

public class Enemy2 extends Entity {
    public ID id = ID.Enemy2;
    double t = 0;
    double speed = 0.03; //ustalamy domyślną prędkość obiektu
    double alfa=0; //kąt względem którego obiekt porusza sie wzgłedem osi x
    double beta=0; //kąt względem którego obiekt porusza sie wzgłedem osi x
    public Enemy2(Model model, Vector3 position, EntityHandler entityHandler) {
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(1, -alfa);
        model.scale(0.2);
    }

    Random random = new Random();
    double time = System.currentTimeMillis()/1000d;
    public void logic() {
        position.add(velocity);
        model.move(velocity);
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
