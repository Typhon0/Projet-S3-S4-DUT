package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Catan;
import com.mygdx.game.model.State;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/**
 * Created by typhon0 on 15/03/17.
 */


public class HUD {
    public Catan game;
    public Stage stage;
    private Skin skin;
    private GDXDialogs dialogs;


    public HUD(SpriteBatch sb, final Catan game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));
        dialogs = GDXDialogsSystem.install();


        stage = new Stage(new ExtendViewport(1920, 1080));

        Table table = new Table();
        table.setSize(1920, 1080);
        //Text Button
        final Button piocher = new Button(skin, "round");
        table.add(piocher).size(170, 170).padRight(1500);

        final Button stat = new Button(skin, "round");
        table.add(stat).size(170, 170);
        table.row();

        //Text Button
        final Button echange = new Button(skin, "round");
        table.add(echange).size(170, 170).padRight(1500);

        final Button regle = new Button(skin, "help");
        table.add(regle).size(170, 170);
        table.row();

        final Button lancedes = new Button(skin, "round");
        table.add(lancedes).size(170, 170).padRight(1500);

        final Button settings = new Button(skin, "settings");
        table.add(settings).size(170, 170);

        table.row();


        final Button pions = new Button(skin, "round");
        table.add(pions).size(170, 170).padRight(1500);

        final Button quit = new Button(skin, "exit");
        table.add(quit).size(170, 170);
        table.row();

        final Button passertour = new Button(skin, "confirm");
        table.add(passertour).size(170, 170).padRight(1500);
        table.row();

        stage.addActor(table);

        Table table2 = new Table();
        table2.setSize(1920, 1080);
        table2.setPosition(650, -450);
        final Label wood_count = new Label("0", skin, "big");
        final Label foin_count = new Label("0", skin, "big");
        final Label stone_count = new Label("0", skin, "big");
        final Label mouton_count = new Label("0", skin, "big");
        final Label brick_count = new Label("0", skin, "big");


        table2.add(wood_count);
        table2.add(foin_count);
        table2.add(stone_count);
        table2.add(mouton_count);
        table2.add(brick_count);
        table2.row();

        //image bois
        Texture texture_img_wood = new Texture(Gdx.files.internal("textures/wood.png"));
        Image image_wood = new Image(texture_img_wood);


        //image foin
        Texture texture_img_hay = new Texture(Gdx.files.internal("textures/hay.png"));
        Image image_hay = new Image(texture_img_hay);

        //image pierre
        Texture texture_img_rock = new Texture(Gdx.files.internal("textures/rock.png"));
        Image image_rock = new Image(texture_img_rock);

        //image mouton
        Texture texture_img_sheep = new Texture(Gdx.files.internal("textures/sheep.png"));
        Image image_sheep = new Image(texture_img_sheep);

        //image brique
        Texture texture_img_brick = new Texture(Gdx.files.internal("textures/brick.png"));
        Image image_brick = new Image(texture_img_brick);

        table2.add(image_wood);
        table2.add(image_hay);
        table2.add(image_rock);
        table2.add(image_sheep);
        table2.add(image_brick);
        stage.addActor(table2);


        //Listener Bouton piocher
        piocher.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        //Listener Bouton statistique
        stat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

//TODO
            }
        });

        //Listener Bouton echange
        echange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//TODO

            }
        });

        //Listener Bouton regle
        regle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new HelpScreen(game));
            }
        });

        //Listener Bouton settings
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionScreen(game, true));
            }
        });

        //Listener Bouton pions
        pions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowsPion();
            }
        });

        //Listener Bouton quit
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                quitGameConfirm(); // Affiche le dialogue pour quitter le tour


            }
        });

        //Listener Bouton passertour
        passertour.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                passerTourDialog(); // Affiche le dialogue pour passer le tour
            }
        });

    }

    public void quitGameConfirm() {
        System.out.println("quit");
        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        bDialog.setTitle("Quitter ?");
        bDialog.setMessage("Voulez-vous vraiment quitter ?");


        bDialog.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
                System.out.println(button);

                if (button == 1) { //Annuler

                } else if (button == 0) { //Quitter et sauvegarder
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new MainMenu(game));
                        }
                    });

                }
            }
        });
        bDialog.addButton(" Sauvegarder et quitter ");
        bDialog.addButton(" Annuler ");

        bDialog.build().show();

    }

    public void passerTourDialog() {

        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        bDialog.setTitle("Passer son tour ?");
        bDialog.setMessage("Voulez-vous vraiment passer votre tour ?");


        bDialog.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
                System.out.println(button);

                if (button == 1) { //Annuler

                } else if (button == 0) { //Oui
                    //TODO passer le tour
                }
            }
        });
        bDialog.addButton(" Oui ");
        bDialog.addButton(" Annuler ");

        bDialog.build().show();

    }

    public void windowsPion() {
        Button ok = new Button(skin, "confirm");
        Button deny = new Button(skin, "deny");

        final Window pionWindows = new Window("Piocher", skin);


        //Pion

        Texture texture_img_route = new Texture(Gdx.files.internal("textures/pions/colonie/Colonie_rouge.png"));
        Image image_route = new Image(texture_img_route);

        Texture texture_img_colonie = new Texture(Gdx.files.internal("textures/pions/colonie/Colonie_bleue.png"));
        Image image_colonie = new Image(texture_img_colonie);

        Texture texture_img_ville = new Texture(Gdx.files.internal("textures/pions/ville/Ville_bleue.png"));
        Image image_ville = new Image(texture_img_ville);
        pionWindows.add(image_colonie);
        pionWindows.add(image_route);
        pionWindows.add(image_ville);


        pionWindows.row();
        //button
        pionWindows.add(deny);
        pionWindows.add(ok);
        pionWindows.setSize(1000, 500);
        pionWindows.setPosition(450, stage.getHeight() / 2 - pionWindows.getHeight() / 2);

        stage.addActor(pionWindows);

        //Touch listener route
        image_route.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Touch listener colonie
        image_colonie.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        //Touch listener ville
        image_ville.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
    }


}
