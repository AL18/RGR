package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img = null;
	
	public ImagePanel(String fileName) {
		setBackground(new Color(252,252,252));
		try {
			URL url = getClass().getResource(fileName);
			img = ImageIO.read(url);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		double kImg = (double) img.getHeight() / img.getWidth(),
				kPanel = (double) getHeight() / getWidth(),
				hScale = getHeight(), wScale = getWidth();
		int hTop = 0;
		if (kPanel > kImg) {
			hScale = wScale * kImg;
			hTop = getHeight() - (int) hScale;
		} else
			wScale = hScale / kImg;
		
		Image scaleImg = img.getScaledInstance((int) wScale, 
				(int) hScale, Image.SCALE_SMOOTH);
		g2d.drawImage(scaleImg, 0, hTop, this);
	}
	
	
}
