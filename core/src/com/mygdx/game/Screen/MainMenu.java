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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;
import com.mygdx.game.autre.Musique;
import com.mygdx.game.model.Constantes;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/**
 * Created by typhon0 on 28/02/17.
 */

public class MainMenu implements Screen {

    Skin skin;
    Stage stage;
    Catan game;
    Musique musique;
    public GDXDialogs dialogs;


    // constructor to keep a reference to the main Game class
    public MainMenu(Catan pgame, Musique mus) {

        dialogs = GDXDialogsSystem.install();
        musique = mus;
        //lancement de la musique
        musique.lancerMusique();

        this.game = pgame;

        stage = new Stage(new ExtendViewport(800, 510));

        Gdx.input.setInputProcessor(stage); // Detection des inputs

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));

        Table table = new Table();
        table.setSize(800, 510);

        //Background
        Texture t = new Texture(Constantes.CHEMIN_ACCES_UI + "background_menu.jpg");
        Drawable d = new TextureRegionDrawable(new TextureRegion(t));
        table.setBackground(d);
        table.setFillParent(true);

        final TextButton loadgame = new TextButton("Reprendre partie", skin);
        table.add(loadgame).size(300, 110).padRight(-200);
        table.row();

        //Text Button
        final TextButton startGame = new TextButton("Nouvelle partie", skin);
        table.add(startGame).size(300, 110).padTop(10).padRight(-200);
        table.row();


        //Button
        final Button quit = new Button(skin, "exit");
        table.add(quit).size(100, 100).padTop(40).padRight(10);

        final Button options = new Button(skin, "settings");
        table.add(options).size(100, 100).padTop(40).padRight(10);

        final Button help = new Button(skin, "help");
        table.add(help).size(100, 100).padTop(40).padRight(10);

        table.row();


        //  table.pack();

        stage.addActor(table);

        //Listener Bouton option
        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                options.addAction(Actions.fadeOut(0.7f));
                game.setScreen(new OptionScreen(game, false, musique));
                musique.lancerSon();

            }
        });
        //Listener Bouton Nouvelle partie
        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame.addAction(Actions.fadeOut(0.7f));
                game.NewPartie();
                game.setScreen(new GameScreen(game, musique));
                musique.lancerSon();
            }
        });

        //Listener Bouton Charcher une partie

        loadgame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.getPartie() != null) {
                    startGame.addAction(Actions.fadeOut(0.7f));
                    game.setScreen(new GameScreen(game, musique));
                    musique.lancerSon();
                } else {
                    GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
                    bDialog.setTitle("Information");
                    bDialog.setMessage("Vous devez d'abord créer une nouvelle partie");


                    bDialog.setClickListener(new ButtonClickListener() {

                        @Override
                        public void click(int button) {
                        }
                    });
                    bDialog.addButton(" Ok ");

                    bDialog.build().show();
                }
            }
        });

        //Listener Bouton help
        help.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                help.addAction(Actions.fadeOut(0.7f));
                game.setScreen(new HelpScreen(game, musique));
                musique.lancerSon();
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

