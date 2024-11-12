package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    int code;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        code= e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        code =-1;
    }
}
