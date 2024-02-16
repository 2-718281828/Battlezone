package battlezone.main;

import battlezone.entity.Enemy1;
import battlezone.entity.Enemy2;
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
        entityHandler.entities.add(new Enemy1(LoadModel.loadModel(
                new File(classPath + "/torus.model"), new Color(128,0,128), camera.renderer, camera),
                new Vector3(2,0,0), entityHandler));//model, położenie, entityHandler
        entityHandler.entities.add(new Enemy2(LoadModel.loadModel(
                new File(classPath + "/torus.model"), new Color(128,0,128), camera.renderer, camera),
                new Vector3(-2,0,0), entityHandler));
        for (int i = 0; i < entityHandler.entities.size(); i++) {
            entityHandler.entities.get(i).model.init(((MainRenderer)camera.renderer).triangles);
        }
    }
    public void update() {
        camera.update();
        entityHandler.logic();
    }
}
