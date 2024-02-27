package battlezone.entity;

import battlezone.main.MainRenderer;
import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;
import renderer.Model;

import java.awt.*;
import java.io.File;
import java.util.Random;

public class UFO extends Entity {
    public ID id = ID.UFO;
    Camera camera;
    double speed = 0.03; //ustalamy domyślną prędkość obiektu
    double alfa = 0; // kąt pod jakim poruszał się obiekt
	String classPath = null;
    public UFO(Model model, Vector3 position, EntityHandler entityHandler, Camera camera) {
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(0, -0.5*Math.PI);
        model.scale(0.5);
        this.camera = camera;
	classPath = ((MainRenderer)camera.renderer).getClass().getResource("").getPath();
    model.move(new Vector3(position.x, 0.5, position.z));
    }

    Random random = new Random();
    long time = 0;

    public void logic() {
        time++;
	// przemiesczanie
        position.add(velocity);
        model.move(velocity);
        if(time / 60 >= 5) {
		        alfa = alfa+ random.nextDouble(Math.PI);
                time = 0;
            }
	    velocity.x = Math.cos(alfa) * speed;
	    velocity.z = Math.sin(alfa) * speed;
	    // ustalamy prędkośc obiektów na podstawie kątów
        }
    }
