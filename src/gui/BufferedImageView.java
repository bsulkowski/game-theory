package gametheory.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 *	@author Bartosz Sułkowski
 */
abstract public class BufferedImageView extends JLabel implements Icon {
    public BufferedImage image;
    public int width, height;
    
    protected BufferedImageView(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        this.width = width;
        this.height = height;
        setIcon(this);
    }
    
    public void update() {
        updateImage();
        repaint();
    }
    
    abstract public void updateImage();
    
    public int getIconWidth() {
        return width;
    }
    
    public int getIconHeight() {
        return height;
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawImage(image, x, y, width, height, this);
    }
}
