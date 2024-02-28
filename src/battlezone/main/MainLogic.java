package battlezone.main;
import battlezone.entity.*;
import battlezone.entity.Point;
import engine.Logic;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Camera;
import renderer.LoadModel;
import util.Console;

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
    public static int spawnedTanks = 0;         //działające czołgi
    public static int spawnedSTanks = 0;
    int maxspawnedTanks = 1;          //maksymalna ilość działających czołgów
    int maxspawnedSTanks = 0;        //liczba ujemna sprawi że super czołgi będą sie spawnić potem
    long tankTime = 600;                   //losowy czas spawnu
    long sTankTime = 1200 + random.nextLong(600);
    int gameType = 0;



    public MainLogic(Camera camera){
        this.camera = camera;
        entityHandler = new EntityHandler();
        space = new KeyHandler();
        camera.renderer.addKeyListener(space);
        for (int i = 0; i < entityHandler.entities.size(); i++) {
            entityHandler.entities.get(i).model.init(((MainRenderer)camera.renderer).triangles);
        }// inicjalizacja wszystkich modelow
        UFO ufo = new UFO(LoadModel.loadModel(new File(classPath + "/ufo.model"), Color.white, camera.renderer, camera),new Vector3(camera.position.x, camera.position.y-0.5, camera.position.z), entityHandler, camera);
        entityHandler.entities.add(ufo);
        ufo.model.init(((MainRenderer)camera.renderer).triangles);
        for (int x = 15; x>0; x--){
            Tree tree = new Tree(LoadModel.loadModel(new File(classPath + "/tree.model"), Color.green, camera.renderer, camera),new Vector3(-50+ random.nextInt(100), 0, -50+ random.nextInt(100)), entityHandler);
            entityHandler.entities.add(tree);
            tree.model.init(((MainRenderer)camera.renderer).triangles);
        }
        for (int x = 15; x>0; x--){
            Rock rock = new Rock(LoadModel.loadModel(new File(classPath + "/rock.model"), Color.green, camera.renderer, camera),new Vector3(-50+ random.nextInt(100), -1.5, -50+ random.nextInt(100)), entityHandler);
            entityHandler.entities.add(rock);
            rock.model.init(((MainRenderer)camera.renderer).triangles);
        }
    }
    boolean reload = false;
    public void update() {
        if (maxspawnedTanks > spawnedTanks) {
            tankTime--;
        }
        if (maxspawnedSTanks> spawnedSTanks) {
            sTankTime--;
        }
        gameTimer--;
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
            Console.log("Spawn tank");
        }
        if (maxspawnedSTanks>spawnedSTanks&&sTankTime<=0){
            SuperTank superTank = new SuperTank(LoadModel.loadModel(new File(classPath + "/SuperTank.model"), Color.orange, camera.renderer, camera), new Vector3(random.nextInt(60)-30,0, random.nextInt(60)-30), entityHandler, camera);//model, położenie, entityHandler
            entityHandler.entities.add(superTank);
            superTank.model.init(((MainRenderer)camera.renderer).triangles);
            sTankTime = sTankWait + random.nextLong(600);
            spawnedSTanks++;
            Console.log("Spawn super tank");
        }


        //instrukcje warunkujące przebieg gry
        if (gameTimer<=0&&gameType<9){
            gameType++;
            gameTimer = 60*30+60*random.nextInt(30);
        }
        else if (gameType>=9&&gameTimer<=0){
            maxspawnedTanks++;
            if (sTankWait>0){
                sTankWait-=100;
            }
            if (tankWait>0){
                tankWait-=100;
            }
            maxspawnedSTanks+=2;
            gameTimer = 60*30+60*random.nextInt(30);
        }
        switch (gameType){
            case 1:
                maxspawnedTanks = 3;
                gameType++;
                break;
            case 3:
                maxspawnedTanks=5;
                tankWait=400;
                break;
            case 5:
                maxspawnedTanks=4;
                maxspawnedSTanks=1;
                break;
            case 7:
                maxspawnedTanks=2;
                maxspawnedSTanks = 3;
                sTankWait=600;
                tankWait=200;
                break;
            default:

        }
    }
}
