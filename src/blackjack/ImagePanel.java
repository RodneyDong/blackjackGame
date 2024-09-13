package blackjack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;
/**
 * Starting Image panel with the title and animation
 * ICS4U
 * @author Rodney Dong
 * Student Number:350706008
 * @date January 20 2022
 */
public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Image frontImg; // animation image on configuration panel
	
	/**
	 * Creates assign animation to the frontImg variable
	 */
	public ImagePanel() {
		URL url = getClass().getResource("/resources/animation.gif");
		frontImg = Toolkit.getDefaultToolkit().getImage(url);
	}
	
	/**
	 * Paint and set the image panel
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(107,155,17)); // green background color
		g.fillRect(0, 0, 1024, 768); // fill background
		g.setColor(new Color(110, 15, 135)); // purple String color
		g.setFont(new Font(Font.SERIF, Font.BOLD, 48));
		g.drawString("Welcome to Our Blackjack Game!", 160, 70);
		g.drawImage(frontImg, 180, 110, this);
	}
	
}
