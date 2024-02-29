package battlezone.main;
import java.awt.*;

import static battlezone.main.MainLogic.*;

public class HUD {

	Font font = new Font("Sans Serif", Font.PLAIN, 30);

	public void render(Graphics2D graphics2D) {
		graphics2D.setColor(Color.green);
		graphics2D.fillRect(100, 100, 100, 100);
		graphics2D.setFont(font);
		graphics2D.drawString("Score: "+score, 100, 400);
		graphics2D.drawString("Health: "+health, 100, 350);
		graphics2D.drawString("Ammunition: "+magazine, 100, 300);
	}

	public void update() {

	}
	
}
