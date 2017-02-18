package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controler.Draw;
import com.mygdx.game.model.HexTile;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = Gdx.app.getGraphics().getWidth();//1920;//2;//Gdx.app.getGraphics().getWidth(); //1920/2;
//		config.height = Gdx.app.getGraphics().getHeight();//1080;//2;//Gdx.app.getGraphics().getHeight(); //1080/2;
		config.width = 1920;//1920;//2;//Gdx.app.getGraphics().getWidth(); //1920/2;
		config.height = 1080;//1080;//2;//Gdx.app.getGraphics().getHeight(); //1080/2;
		new LwjglApplication(new Draw(), config);
		System.out.println("test");

//		System.out.println((Math.sqrt(3)*108)/2);
	}
}
