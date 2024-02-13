package battlezone.main;

import maths.Vector2;
import renderer.Camera;
import renderer.Window;
import util.Console;
import renderer.Renderer;
import engine.Engine;

public class Main {
  public static final int WIDTH=1920, HEIGHT=1080;
  public static final String TITLE = "Battlezone";
  public static void main(String[] args) {
    Camera camera = new Camera();
    Renderer renderer  = new MainRenderer(new Vector2(WIDTH, HEIGHT), camera);
    Window window = new Window(new Vector2(WIDTH, HEIGHT), TITLE, renderer);
    renderer.addKeyListener(camera);
    renderer.addMouseMotionListener(camera);
    renderer.requestFocus();
    camera.enableRotationPitch = false;
    MainLogic mainLogic = new MainLogic();
    Engine engine = new Engine(renderer, mainLogic);
    engine.start();
  }
}
