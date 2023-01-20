package com.chifoumi;


import javax.swing.*;

import java.awt.*;

class Fenetre extends JFrame
{
	private Visuel visuel;

	public Fenetre(Jeu jeu)
	{
		this.visuel = new Visuel(jeu);

		this.setTitle("Chifoumi!");
		this.setSize((7+1)*64,(7+1)*64);
		this.setLocationRelativeTo(null);
		this.visuel.setBackground(Color.WHITE);
		this.setContentPane(this.visuel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.majIHM();
	}

	public void majIHM()
	{
		this.visuel.repaint();
	}
}