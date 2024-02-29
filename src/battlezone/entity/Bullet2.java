package battlezone.entity;

import battlezone.main.MainLogic;
import battlezone.main.MainRenderer;
import battlezone.main.Sounds;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;

public class Bullet2 extends Entity {
    public ID id = ID.Bullet2;
    double lifetime = 0;
    static double speed = 1; //ustalamy domyślną prędkość obiektu
	Camera camera;

    public Bullet2(Model model, Vector3 position, EntityHandler entityHandler, Vector3 velocity, Camera camera) {
        super(model, position, entityHandler);
	this.velocity = velocity;
	this.camera = camera;
        Vector3 dst = new Vector3(camera.position);
        dst.subtract(position);
        velocity.x = (dst.x/dst.magnitude())*speed;
        velocity.z = (dst.z/dst.magnitude())*speed;
        model.rotate(1, -Math.atan2(dst.z, dst.x)+0.5*Math.PI);
        Sounds.play("/fire.wav");
    }

    Vector3 dst = new Vector3(0, 0, 0);
    public void logic() {
        lifetime++;
        if (lifetime >= 60 * 5) {
            entityHandler.entities.remove(this);
            model.remove(((MainRenderer) camera.renderer).triangles);
            lifetime = 0;
        }
        position.add(velocity);
        model.move(velocity);
	dst.x = position.x;
	dst.z = position.z;
	dst.subtract(camera.position);
	if (dst.magnitude() < 0.5) { // gdy jest zbyt blisko gracza, rejestrujemy to jako kolizję
		lifetime += 60*5; // dodajemy czas tak aby przy następnym update został usunięty	
	}
    if (Math.abs(position.x-camera.position.x)<0.5&&Math.abs(position.z-camera.position.z)<0.5){
        MainLogic.health--;
        Sounds.play("/health.wav");
        System.out.println(MainLogic.health);
    }
    }
}
