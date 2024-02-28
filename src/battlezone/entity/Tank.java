package battlezone.entity;

import battlezone.main.*;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;

import java.io.*;
import java.awt.*;
import renderer.*;

import java.util.Random;

public class Tank extends Entity {
    public ID id = ID.Tank;
    double t = 0;
    Camera camera;
    double speed = 0.03; //ustalamy domyślną prędkość obiektu
    double alfa = 0; // kąt pod jakim poruszał się obiekt
	double beta = alfa; //kąt pod jakim porusza się obiekt
	String classPath = null;
    public Tank(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(1, -alfa);
        model.scale(0.2);
        this.camera = camera;
	classPath = ((MainRenderer)camera.renderer).getClass().getResource("").getPath();
	if (Math.abs(position.x-camera.position.x)<10 || Math.abs(position.z-camera.position.z)<10){
		model.remove(((MainRenderer)camera.renderer).triangles);
		entityHandler.entities.remove(this);
	}
    }

    Random random = new Random();
    double time = 0;
    double fireTime = 0;

    public void logic() {
        time++;
	// przemiesczanie
        position.add(velocity);
        model.move(velocity);
	// odległość do kamery
	Vector3 dst = new Vector3(camera.position);
	dst.subtract(position);
	dst.y = 0;
        if (dst.magnitude() < 15 && dst.magnitude() >= 7){ // zbliża się do gracza
		velocity.x = (dst.x/dst.magnitude())*speed;	
		velocity.z = (dst.z/dst.magnitude())*speed; // dzielimy przez wartość wektora aby otrzymać składową wektora jednostkowego skierowanego w stronę kamery a następnie mnożymy tę składową razy prędkość
		model.rotate(1, -beta); // obracamy obiekt tak, aby był obrócony domyślnie
		beta = -Math.atan2(dst.z, dst.x); // kąt pod jakim ma się poruszać czołg aby szedł do kamery
		model.rotate(1, beta); // obracamy obiekt o ten nowy kąt 
        }
	else if (dst.magnitude() < 7 && dst.magnitude() > 4) { // stoi w miejscu i celuje w gracza
		velocity.x = 0;
		velocity.z = 0;
		// obliczamy kąt i zwracamy czołg w stronę gracza
		model.rotate(1, -beta);
		beta = -Math.atan2(dst.z, dst.x);
		model.rotate(1, beta);
		fireTime++;
		if (fireTime >= 120) {
			fireTime = 0;
			Model modelP = LoadModel.loadModel(new File(classPath + "/pocisk.model"), Color.red, camera.renderer, camera);
			Bullet2 p = new Bullet2(modelP, new Vector3(position), entityHandler, new Vector3(Bullet2.speed*(dst.x/dst.magnitude()), 0, Bullet2.speed*(dst.z/dst.magnitude())), camera);
			entityHandler.entities.add(p);
			modelP.init(((MainRenderer)camera.renderer).triangles);
		}
	}
	else if (dst.magnitude() <= 4) { // oddala się od gracza
		velocity.x = -(dst.x/dst.magnitude())*speed;	
		velocity.z = -(dst.z/dst.magnitude())*speed; // dzielimy przez wartość wektora aby otrzymać składową wektora jednostkowego skierowanego w stronę kamery a następnie mnożymy tę składową razy prędkość
		model.rotate(1, -beta); // obracamy obiekt tak, aby był obrócony domyślnie
		beta = -Math.atan2(dst.z, dst.x); // kąt pod jakim ma się poruszać czołg aby szedł do kamery
		model.rotate(1, beta); // obracamy obiekt o ten nowy kąt 

	}
        else {
            if(time / 60 >= 5) {
		alfa = beta; // rejestrujemy poprzednią wartość kąta 
                time = 0; // resetujemy czas
		beta += Math.PI/4-random.nextDouble(Math.PI/2); // dodajemy do bety losowy kąt między -pi/4 a pi/4
		model.rotate(1, beta-alfa); // obracamy czołg o zmianę kąta
            }
	    velocity.x = Math.cos(-beta) * speed;
	    velocity.z = Math.sin(-beta) * speed;
	    // ustalamy prędkośc obiektów na podstawie kątów
        }
	for (int i = 0; i < entityHandler.entities.size(); i++) {
         if (entityHandler.entities.get(i) != this) {
           if (collision(entityHandler.entities.get(i).hitbox)) {
            if(entityHandler.entities.get(i).getClass()==Bullet1.class) {
               util.Console.log("Kolizja z pociskiem");
	       entityHandler.entities.get(i).model.remove(((MainRenderer)camera.renderer).triangles);
	       entityHandler.entities.remove(entityHandler.entities.get(i));
		   for (int a = 5; a>0;a--) {
			   Model modelP = LoadModel.loadModel(new File(classPath + "/piece.model"), Color.green, camera.renderer, camera);
			   Piece p = new Piece(modelP, new Vector3(position), entityHandler, camera);
			   entityHandler.entities.add(p);
			   modelP.init(((MainRenderer) camera.renderer).triangles);
		   }
		   model.remove(((MainRenderer)camera.renderer).triangles);
		   entityHandler.entities.remove(this);
		   MainLogic.spawnedTanks--;
            }
	   }
         }
       }

    }
}
