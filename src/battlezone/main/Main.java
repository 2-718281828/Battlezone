package battlezone.main;

import maths.Vector2;
import renderer.Camera;
import renderer.Window;
import renderer.Renderer;
import engine.Engine;
import java.util.Locale;

public class Main {
  public static final int WIDTH=1280, HEIGHT=720;                                     //deklaruje rozmiary okna w pikselach
  public static final String TITLE = "Battlezone";                                     //deklaruje nazwe okna
  public static void main(String[] args) {
    Locale.setDefault(new Locale("en", "US"));
    Camera camera = new Camera();                                                     //tworzy kamere bul bul bul
    Renderer renderer  = new MainRenderer(new Vector2(WIDTH, HEIGHT), camera);
    Window window = new Window(new Vector2(WIDTH, HEIGHT), TITLE, renderer);
    renderer.addKeyListener(camera);                                                   //uruchamia słuchacze
    renderer.addMouseMotionListener(camera);
    renderer.requestFocus();
    camera.enableRotationYaw = true;  //wyłącza obrótw kamery w pionie
    camera.enableRotationPitch = true;
    MainLogic mainLogic = new MainLogic(camera);                                       //wspomaga kamere
    Engine engine = new Engine(renderer, mainLogic);                                   //obsługuje silnik
    engine.start();
  }
}
