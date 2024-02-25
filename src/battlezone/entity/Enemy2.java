package battlezone.entity;

import battlezone.main.Main;
import battlezone.main.MainLogic;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.Model;
import renderer.Renderer;
import util.Console;

import java.util.Random;

public class Enemy2 extends Entity {
    public ID id = ID.Enemy2;
    double t = 0;
    Camera camera;
    double speed = 0.03; //ustalamy domyślną prędkość obiektu
    double alfa = 0; // kąt pod jakim poruszał się obiekt 
    double beta = alfa; //kąt pod jakim porusza się obiekt

    public Enemy2(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(1, -alfa);
        model.scale(0.2);
        this.camera = camera;

    }

    Random random = new Random();
    long time = 0;
    public static boolean fire=false;          //polece jeśli true to strzelaj
    long fireTime = 0;                            //przeładowanie
    public void logic() {
        time++;
        if (fireTime>0){
            fireTime--;
        }
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
	else if (dst.magnitude() < 7) { // stoi w miejscu i celuje w gracza
		velocity.x = 0;
		velocity.z = 0;
		// obliczamy kąt i zwracamy czołg w stronę gracza
		model.rotate(1, -beta);
		beta = -Math.atan2(dst.z, dst.x);
		model.rotate(1, beta);
        if (fireTime==0){
            fire = true;
            fireTime = 300;    //czas przeładowania w sekundach razy 60
        }
        else{
            fire = false;
        }
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
    }
}
