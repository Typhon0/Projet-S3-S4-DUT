package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Catan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = Gdx.app.getGraphics().getWidth();//1920;//2;//Gdx.app.getGraphics().getWidth(); //1920/2;
//		config.height = Gdx.app.getGraphics().getHeight();//1080;//2;//Gdx.app.getGraphics().getHeight(); //1080/2;
		config.width = 1920;//1920;//2;//Gdx.app.getGraphics().getWidth(); //1920/2;
		config.height = 1080;//1080;//2;//Gdx.app.getGraphics().getHeight(); //1080/2;
		new LwjglApplication(new Catan(), config);
		System.out.println("test");

//		System.out.println((Math.sqrt(3)*108)/2);
	}
}
