package com.chifoumi;
import javax.swing.*  ;

import java.awt.*     ;

import java.awt.image.BufferedImage;

class Visuel extends JPanel
{
	private Jeu jeu;

	public Visuel(Jeu jeu)
	{
		this.jeu = jeu;
	}

	
	/** 
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		BufferedImage image;
		int[] position;

		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 7-1, 7-1);

		g.setColor(Color.RED);
		g.drawRect(0, 0, 7, 7);
		
		for(int i=0; i<this.jeu.getNbImage(); i++)
		{
			position = this.jeu.getPosition(i);
			image = this.jeu.getImage(i);
			g.drawImage(image,position[0],position[1],null);
		}
	}
}