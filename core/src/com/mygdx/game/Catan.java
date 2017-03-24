package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Screen.MainMenu;
import com.mygdx.game.autre.Musique;
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
        final Musique musique = new Musique();
        this.setScreen(new MainMenu(this, musique));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }

    public Partie getPartie() {
        return partie;
    }
}