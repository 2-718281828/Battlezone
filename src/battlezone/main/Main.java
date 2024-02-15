package battlezone.main;

import maths.Vector2;
import renderer.Camera;
import renderer.Window;
import util.Console;
import renderer.Renderer;
import engine.Engine;
import java.util.Locale;

public class Main {
  public static final int WIDTH=1920, HEIGHT=1080;                                     //deklaruje rozmiary okna w pikselach
  public static final String TITLE = "Battlezone";                                     //deklaruje nazwe okna
  public static void main(String[] args) {
    Locale.setDefault(new Locale("en", "US"));
    Camera camera = new Camera();                                                     //tworzy kamere
    Renderer renderer  = new MainRenderer(new Vector2(WIDTH, HEIGHT), camera);
    Window window = new Window(new Vector2(WIDTH, HEIGHT), TITLE, renderer);
    renderer.addKeyListener(camera);                                                   //uruchamia słuchacze
    renderer.addMouseMotionListener(camera);
    renderer.requestFocus();
    camera.enableRotationYaw = true;                                                  //wyłącza obrótw kamery w pionie
    MainLogic mainLogic = new MainLogic(camera);                                       //wspomaga kamere
    Engine engine = new Engine(renderer, mainLogic);                                   //obsługuje silnik
    engine.start();
  }
}
