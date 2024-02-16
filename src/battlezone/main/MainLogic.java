package battlezone.main;

import battlezone.entity.Enemy1;
import engine.Logic;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;
import java.awt.*;
import java.io.File;

public class MainLogic implements Logic {

    public Camera camera;
    public EntityHandler entityHandler;
    String classPath=getClass().getResource("").getPath();
    public MainLogic(Camera camera){
        this.camera = camera;
        entityHandler = new EntityHandler();
        entityHandler.entities.add(new Enemy1(LoadModel.loadModel(new File(classPath + "/torus.model"), Color.green, camera.renderer, camera), new Vector3(0,0,0), entityHandler));
        //model, położenie, entityHandler
    }
    public void update() {
        camera.update();
    }
}
