package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Screen.MainMenu;
import com.mygdx.game.model.Partie;
import com.mygdx.game.model.Plateau;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;

public class Catan extends Game {

    public SpriteBatch batch, batch2,batchHUD;

    public ShapeRenderer sr;

    public PolygonSpriteBatch polyBatch;

    //public Plateau plateau;

    private Partie partie;


    //private SpriteBatch batch;
    //
    @Override
    public void create() {
        sr = new ShapeRenderer();
        polyBatch = new PolygonSpriteBatch();

        partie = new Partie();

        batch = new SpriteBatch();
        batch2 = new SpriteBatch();

        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
		//batch.dispose();
       // batch2.dispose();
//		img.dispose();
    }

    public Partie getPartie() {
        return partie;
    }
}