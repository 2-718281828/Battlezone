package battlezone.main;

import battlezone.entity.Enemy1;
import battlezone.entity.Enemy2;
import battlezone.entity.Point;
import engine.Logic;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;
import java.awt.*;
import java.io.File;
import battlezone.entity.ID;

public class MainLogic implements Logic {

    public Camera camera;
    double timer=0;
    public EntityHandler entityHandler;
    String classPath=getClass().getResource("").getPath();
    KeyHandler space;
    public MainLogic(Camera camera){
        this.camera = camera;
        entityHandler = new EntityHandler();
        entityHandler.entities.add(new Enemy1(LoadModel.loadModel(new File(classPath + "/pocisk.model"), Color.black, camera.renderer, camera),new Vector3(0,-0.5,0), entityHandler, camera));//model, położenie, entityHandler
        entityHandler.entities.add(new Enemy2(LoadModel.loadModel(new File(classPath + "/tank.model"), Color.green, camera.renderer, camera),new Vector3(0,-1,0), entityHandler));//model, położenie, entityHandler
        entityHandler.entities.add(new Point(LoadModel.loadModel(new File(classPath + "/cube.model"), Color.BLUE , camera.renderer, camera),new Vector3(1,0,0), entityHandler));//model, położenie, entityHandler
        space = new KeyHandler();
        camera.renderer.addKeyListener(space);
        for (int i = 0; i < entityHandler.entities.size(); i++) {
            entityHandler.entities.get(i).model.init(((MainRenderer)camera.renderer).triangles);
        } // inicjalizacja wszystkich modelow
    }
    boolean reload = false;
    public void update() {
        camera.update(); // aktualizacja kamery
        entityHandler.logic(); // logika wszystkich obiektow
        if (reload)
            timer++;
        if (timer>=120){
            timer=0;
            reload = false;
        }
        if (space.spacePressed && !reload){
            reload = true;
            Enemy1 pocisk = new Enemy1(LoadModel.loadModel(new File(classPath + "/pocisk.model"), Color.red, camera.renderer, camera),new Vector3(camera.position.x, camera.position.y-0.5, camera.position.z), entityHandler, camera);//model, położenie, entityHandler
            entityHandler.entities.add(pocisk);
            pocisk.model.init(((MainRenderer)camera.renderer).triangles);
        }
    }
}
