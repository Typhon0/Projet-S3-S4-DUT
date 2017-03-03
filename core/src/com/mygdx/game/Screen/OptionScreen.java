package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;


/**
 * Created by typhon0 on 28/02/17.
 */

public class OptionScreen implements Screen {

    private Skin skin;
    private Stage stage;
    private Catan game;


    // constructor to keep a reference to the main Game class
    public OptionScreen(Catan pgame) {
        this.game = pgame;

        stage = new Stage(new ExtendViewport(800, 500));

        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));

        Table table = new Table();
        table.setSize(800, 500);

        //Background
        Texture t = new Texture("background_menu.jpg");
        Drawable d = new TextureRegionDrawable(new TextureRegion(t));
        table.setBackground(d);
        table.setFillParent(true);

        CheckBox musicCB = new CheckBox("Musique",skin);
        table.add(musicCB);


        CheckBox soundCB = new CheckBox("Son",skin);
        table.add(soundCB);
        table.row();

        SelectBox<String> sb = new SelectBox<String>(skin);
        Array<String> difficulte = new Array<String>();
        difficulte.add("Facile");
        difficulte.add("Moyen");
        difficulte.add("Difficile");
        sb.setItems(difficulte);

        table.add(sb).size(200, 30).padTop(20);
        table.row();

        stage.addActor(table);


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


    }

    @Override
    public void render(float delta) {

        // clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // let the stage act and draw
        stage.act(delta);
        stage.draw();


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            System.out.println("Back pressed");
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        // never called automatically
        stage.dispose();
    }
}

