package battlezone.main;
import java.awt.*;
import java.io.Console;
import java.util.ArrayList;

import battlezone.entity.SuperTank;
import battlezone.entity.Tank;
import entity.*;
import renderer.*;
import maths.*;

import static battlezone.main.MainLogic.*;

public class HUD {

	Font font = new Font("Sans Serif", Font.PLAIN, 30);

	MainRenderer renderer;
	Camera camera;
	ArrayList<Vector3> points = new ArrayList<>();
	public HUD(MainRenderer renderer, Camera camera) {
		this.renderer = renderer;
		this.camera = camera;
	}

	int radarTime = 0;
	public void render(Graphics2D graphics2D) {
		graphics2D.setColor(Color.green);
		graphics2D.drawOval(100, 100, 100, 100);
	
		for (Vector3 v : points) {
			graphics2D.drawOval((int) (150 + v.x), (int) (150 + v.y), 5, 5);
		}

		graphics2D.drawLine(150, 150, 150 + (int) (Math.cos(camera.rotation.x+Math.PI*3/4)*50), 150 + (int) (Math.sin(camera.rotation.x+Math.PI*3/4)*50));

		graphics2D.setFont(font);
		graphics2D.drawString("Score: "+score, 100, 400);
		graphics2D.drawString("Health: "+health, 100, 350);
		graphics2D.drawString("Ammunition: "+magazine, 100, 300);
	}

	public void update() {
		radarTime++;
		if (radarTime >= 20) {
			points = new ArrayList<>();

			radarTime = 0;
			for (int i = 0; i < renderer.entityHandler.entities.size(); i++) {
				if (renderer.entityHandler.entities.get(i).getClass() == Tank.class || renderer.entityHandler.entities.get(i).getClass() == SuperTank.class) {
					util.Console.log("abc");
					Vector3 dst = new Vector3(renderer.entityHandler.entities.get(i).position);
					dst.subtract(camera.position);
					if (dst.magnitude() <= 20) {
						dst.multiply(5);
						points.add(dst);
					}

				} 
			}
		}
	}
	
}
