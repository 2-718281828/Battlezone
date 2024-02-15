package battlezone.main;

import engine.Logic;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainLogic implements Logic {

    public Camera camera;
    public EntityHandler entityHandler;
    String classPath=getClass().getResource("").getPath();
    public MainLogic(Camera camera){
        this.camera = camera;
        entityHandler = new EntityHandler();
        entityHandler.entities.add(new Torus(LoadModel.loadModel(new File(classPath + "/torus.mode"), Color.green, camera.renderer, camera), new Vector3(0,0,0), entityHandler));
    }
    public void update() {
        camera.update();
    }
}
