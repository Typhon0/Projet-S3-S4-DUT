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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;

/**
 * Created by typhon0 on 28/02/17.
 */

public class MainMenu implements Screen {

    Skin skin;
    Stage stage;
    Catan game;


    // constructor to keep a reference to the main Game class
    public MainMenu(Catan pgame) {
        this.game = pgame;

        stage = new Stage(new ExtendViewport(800, 510));

        Gdx.input.setInputProcessor(stage); // Detection des inputs

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));

        Table table = new Table();
        table.setSize(800, 510);

        //Background
        Texture t = new Texture("background_menu.jpg");
        Drawable d = new TextureRegionDrawable(new TextureRegion(t));
        table.setBackground(d);
        table.setFillParent(true);

        final TextButton startGame = new TextButton("Nouvelle partie", skin);
        table.add(startGame).size(450, 100);
        table.row();
        final TextButton loadgame = new TextButton("Charger partie", skin);
        table.add(loadgame).size(450, 100).padTop(10);
        table.row();

        final TextButton options = new TextButton("Options", skin);
        table.add(options).size(300, 100).padTop(10).padBottom(3);
        table.row();


        TextButton quit = new TextButton("Quitter", skin);
        table.add(quit).size(300, 100).padTop(10);
        table.row();
        table.pack();

        stage.addActor(table);

        //Listener Bouton option
        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                options.addAction(Actions.fadeOut(0.7f));
                game.setScreen(new OptionScreen(game));

            }
        });
        //Listener Bouton Nouvelle partie
        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame.addAction(Actions.fadeOut(0.7f));

                game.setScreen(new GameScreen(game));

            }
        });

        //Listener Bouton Charcher une partie

        loadgame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadgame.addAction(Actions.fadeOut(0.7f));
                game.setScreen(new LoadGameScreen(game));

            }
        });

        //Listener Bouton quitter
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();

            }
        });


    }

    @Override
    public void render(float delta) {

        // clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // let the stage act and draw
        stage.act(delta);
        stage.draw();

        //Quitte l'application si back is pressed
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            System.out.println("Back pressed");
            Gdx.app.exit();
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

