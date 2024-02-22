package battlezone.main;

import battlezone.entity.Pocisk;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
    boolean spacePressed=false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            spacePressed = false;
        }
    }
}
