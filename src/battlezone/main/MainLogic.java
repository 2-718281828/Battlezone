package battlezone.main;
import battlezone.entity.*;
import battlezone.entity.Point;
import engine.Logic;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class MainLogic implements Logic {

    public Camera camera;
    double timer=0;
    public EntityHandler entityHandler;
    String classPath=getClass().getResource("").getPath();
    KeyHandler space;
    Random random = new Random();
    long gameTimer = 0;
    int tankWait = 600;                       //minimalny czas od ostatniego spawnu
    int sTankWait = 1800;
    int spawnedTanks = 0;         //działające czołgi
    int spawnedSTanks = 0;
    int maxspawnedTanks = 3;          //maksymalna ilość działających czołgów
    int maxspawnedSTanks = 2;
    long tankTime = 600 + random.nextLong(600);                   //czas od ostatniego spawnu
    long sTankTime = 1200 + random.nextLong(600);



    public MainLogic(Camera camera){
        this.camera = camera;
        entityHandler = new EntityHandler();
        entityHandler.entities.add(new SuperTank(LoadModel.loadModel(new File(classPath + "/tank.model"), Color.red, camera.renderer, camera),new Vector3(35,0,35), entityHandler, camera));
        space = new KeyHandler();
        camera.renderer.addKeyListener(space);
        for (int i = 0; i < entityHandler.entities.size(); i++) {
            entityHandler.entities.get(i).model.init(((MainRenderer)camera.renderer).triangles);
        } // inicjalizacja wszystkich modelow
    }
    boolean reload = false;
    public void update() {
        tankTime--;
        sTankTime--;
        gameTimer++;
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
            Bullet1 bullet1 = new Bullet1(LoadModel.loadModel(new File(classPath + "/pocisk.model"), Color.red, camera.renderer, camera),new Vector3(camera.position.x, camera.position.y-0.5, camera.position.z), entityHandler, camera);//model, położenie, entityHandler
            entityHandler.entities.add(bullet1);
            bullet1.model.init(((MainRenderer)camera.renderer).triangles);
        }
        if (maxspawnedTanks>spawnedTanks&&tankTime<=0){
            Tank tank = new Tank(LoadModel.loadModel(new File(classPath + "/tank.model"), Color.green, camera.renderer, camera), new Vector3(random.nextInt(60)-30,0, random.nextInt(60)-30), entityHandler, camera);//model, położenie, entityHandler
            entityHandler.entities.add(tank);
            tank.model.init(((MainRenderer)camera.renderer).triangles);
            tankTime = tankWait + random.nextLong(600);
            spawnedTanks++;
        }
        if (maxspawnedSTanks>spawnedSTanks&&sTankTime<=0){
            SuperTank superTank = new SuperTank(LoadModel.loadModel(new File(classPath + "/tank.model"), Color.green, camera.renderer, camera), new Vector3(random.nextInt(60)-30,0, random.nextInt(60)-30), entityHandler, camera);//model, położenie, entityHandler
            entityHandler.entities.add(superTank);
            superTank.model.init(((MainRenderer)camera.renderer).triangles);
            tankTime = sTankWait + random.nextLong(600);
            spawnedSTanks++;
        }

    }
}
