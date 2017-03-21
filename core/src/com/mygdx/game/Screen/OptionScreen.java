package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    public boolean fromGame;


    // constructor to keep a reference to the main Game class
    public OptionScreen(Catan pgame, final boolean fromGame) {
        this.game = pgame;
        this.fromGame = fromGame;
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

        CheckBox easyCB = new CheckBox("  Facile", skin);
        table.add(easyCB).padTop(-100);

        CheckBox mediumCB = new CheckBox("  Moyen", skin);
        table.add(mediumCB).padLeft(2).padTop(-100);

        CheckBox hardCB = new CheckBox("  Difficile", skin);
        table.add(hardCB).padLeft(10).padTop(-100);
        table.row();

        CheckBox musicCB = new CheckBox("    Musique", skin, "music");
        table.add(musicCB).padTop(20).padLeft(40);
        table.row();


        CheckBox soundCB = new CheckBox("    Son", skin, "sound");
        table.add(soundCB).padTop(20);
        table.row();


        ButtonGroup<CheckBox> bgTB = new ButtonGroup<CheckBox>();
        bgTB.add(easyCB);
        bgTB.add(mediumCB);
        bgTB.add(hardCB);
        bgTB.setMaxCheckCount(1);


        stage.addActor(table);

        Table table2 = new Table();
        table2.setSize(800, 500);
        table2.setPosition(0, -180);

        final Button save = new Button(skin, "confirm");
        final Button cancel = new Button(skin, "deny");

        table2.add(cancel).padRight(500);
        table2.add(save).padLeft(50);
        stage.addActor(table2);


        //Listener Bouton Save
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.addAction(Actions.fadeOut(0.7f));
                //TODO save options

                if (fromGame == true) {

                    game.setScreen(new GameScreen(game));
                }else{
                    game.setScreen(new MainMenu(game));

                }
            }
        });

        //Listener Bouton Annuler
        cancel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cancel.addAction(Actions.fadeOut(0.7f));

                if (fromGame == true) {

                    game.setScreen(new GameScreen(game));
                }else{
                    game.setScreen(new MainMenu(game));

                }

            }
        });


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

/*
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (fromGame == true) {
                game.setScreen(new GameScreen(game));

            } else {
                game.setScreen(new MainMenu(game));

            }
        }*/
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

