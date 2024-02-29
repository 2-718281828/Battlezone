package battlezone.main;
import java.awt.*;

public class HUD {

	Font font = new Font("Sans Serif", Font.PLAIN, 30);

	public void render(Graphics2D graphics2D) {
		graphics2D.setColor(Color.WHITE);
		graphics2D.fillRect(100, 100, 100, 100);
		graphics2D.setFont(font);
		graphics2D.drawString("abc", 300, 300);
	}

	public void update() {

	}
	
}
