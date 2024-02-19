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
                new File(classPath + "/tank.model"), new Color(0,255,0), camera.renderer, camera),new Vector3(2,0,0), entityHandler));//model, położenie, entityHandler
        for (int i = 0; i < entityHandler.entities.size(); i++) {
            entityHandler.entities.get(i).model.init(((MainRenderer)camera.renderer).triangles);
        } // inicjalizacja wszystkich modelow
    }
    public void update() {
        camera.update(); // aktualizacja kamery
        entityHandler.logic(); // logika wszystkich obiektow
    }
}
