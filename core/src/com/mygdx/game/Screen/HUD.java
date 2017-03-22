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
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Catan;
import com.mygdx.game.model.Constantes;
import com.mygdx.game.model.State;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/**
 * Created by typhon0 on 15/03/17.
 */


public class HUD {
    private static Catan game;
    public Stage stage;
    private Skin skin;
    private GDXDialogs dialogs;

    public static Catan getGame() {
        return game;
    }

    public HUD(SpriteBatch sb, Catan game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));
        try {
            dialogs = GDXDialogsSystem.install();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        stage = new Stage(new ExtendViewport(1920, 1080));

        Table table = new Table();
        table.setSize(1920, 1080);
        //Text Button
        final Button piocher = new Button(skin, "round");//TODO Changer image button
        table.add(piocher).size(170, 170).padRight(1500);

        final Button stat = new Button(skin, "round");//TODO Changer image button
        table.add(stat).size(170, 170);
        table.row();

        //Text Button
        final Button echange = new Button(skin, "round");//TODO Changer image button
        table.add(echange).size(170, 170).padRight(1500);

        final Button regle = new Button(skin, "help");
        table.add(regle).size(170, 170);
        table.row();

        final Button lancedes = new Button(skin, "round");//TODO Changer image button
        table.add(lancedes).size(170, 170).padRight(1500);

        final Button settings = new Button(skin, "settings");
        table.add(settings).size(170, 170);

        table.row();

        Stack stackPion = new Stack();

        final Button pions = new Button(skin, "round"); //TODO Changer image button
        final Button pionsCancel = new Button(skin, "deny");
        pionsCancel.setVisible(false);
        stackPion.add(pions);
        stackPion.add(pionsCancel);

        table.add(stackPion).size(170, 170).padRight(1500);


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
        Texture texture_img_wood = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "bois.png"));
        Image image_wood = new Image(texture_img_wood);

        //image foin
        Texture texture_img_hay = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "argile.png"));
        Image image_hay = new Image(texture_img_hay);

        //image pierre
        Texture texture_img_rock = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "minerai.png"));
        Image image_rock = new Image(texture_img_rock);

        //image mouton
        Texture texture_img_sheep = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "laine.png"));
        Image image_sheep = new Image(texture_img_sheep);

        //image brique
        Texture texture_img_brick = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "argile.png"));
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
                afficherMessage("test", "test");
            }
        });

        //Listener Bouton echange
        echange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowsEchange();

            }
        });

        //Listener Bouton regle
        regle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                getGame().setScreen(new HelpScreen(getGame()));
            }
        });

        //Listener Bouton settings
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new OptionScreen(getGame(), true));
            }
        });

        //Listener Bouton pions
        pions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowsPion(pions, pionsCancel);
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

        //Listener Bouton cancel placement des pions
        pionsCancel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                pionsCancel.setVisible(false);
                pions.setVisible(true);
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

    /**
     * Permet d'afficher une boite de dialogue style android
     * avec un titre et un message personnalisé
     *
     * @param title
     * @param message
     */
    public void afficherMessage(String title, String message) {

        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        bDialog.setTitle(title);
        bDialog.setMessage(message);


        bDialog.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
            }
        });
        bDialog.addButton(" Ok ");

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
                    System.out.println("passe tour");
                    game.getPartie().finDeTour();

                }
            }
        });
        bDialog.addButton(" Oui ");
        bDialog.addButton(" Annuler ");

        bDialog.build().show();

    }

    public void windowsEchange() {
        Button deny = new Button(skin, "deny");
        TextButton Jbleu_button = new TextButton("Bleu", skin);
        TextButton Jrouge_button = new TextButton("Rouge", skin);
        TextButton Jvert_button = new TextButton("Vert", skin);
        TextButton Jjaune_button = new TextButton("Jaune", skin);


        final Window echangeWindows = new Window("Choisir joueur avec qui echanger", skin);


        echangeWindows.add(Jbleu_button).padRight(-100);
        echangeWindows.add(Jrouge_button);
        echangeWindows.add(Jvert_button);
        echangeWindows.add(Jjaune_button);


        echangeWindows.row();
        //button
        echangeWindows.add(deny).padTop(50).padRight(200);
        echangeWindows.setSize(1000, 500);
        echangeWindows.setPosition(450, stage.getHeight() / 2 - echangeWindows.getHeight() / 2);

        stage.addActor(echangeWindows);

        //Touch listener bouton bleu
        Jbleu_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                echangeWindows.setVisible(false);
                windowEchange2();
            }
        });

        //Touch listener bouton rouge
        Jrouge_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        //Touch listener bouton vert
        Jvert_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        //Touch listener bouton jaune
        Jjaune_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        //Touch listener bouton cancel
        deny.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                echangeWindows.setVisible(false);

            }
        });
    }

    public void windowEchange2() {
        Button deny = new Button(skin, "deny");
        Label bois_label = new Label("Bois", skin);
        Label argile_label = new Label("Argile", skin);
        Label laine_label = new Label("Laine", skin);
        Label pierre_label = new Label("Pierre", skin);
        Label ble_label = new Label("Blé", skin);

        Label label_boisJ1 = new Label("0",skin,"font-big");

        Button plus = new Button(skin,"plus");
        Button minus = new Button(skin,"minus");


        final Window echange2Windows = new Window("Echange", skin);


        echange2Windows.add(bois_label);
        echange2Windows.add(plus);
        echange2Windows.add(label_boisJ1);
        echange2Windows.add(minus);
        echange2Windows.row();
        echange2Windows.add(argile_label);
        echange2Windows.row();
        echange2Windows.add(laine_label);
        echange2Windows.row();
        echange2Windows.add(pierre_label);
        echange2Windows.row();
        echange2Windows.add(ble_label);


        echange2Windows.row();
        //button
        echange2Windows.add(deny).padTop(50).padRight(200);
        echange2Windows.setSize(1500, 1000);
        echange2Windows.setPosition(450, stage.getHeight() / 2 - echange2Windows.getHeight() / 2);

        stage.addActor(echange2Windows);


        //Touch listener bouton cancel
        deny.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                echange2Windows.setVisible(false);

            }
        });
    }


    /**
     * Affiche la fenêtre pour poser un pion
     *
     * @param pions
     * @param pionsCancel
     */
    public void windowsPion(final Button pions, final Button pionsCancel) {
        Button deny = new Button(skin, "deny");
        TextButton route_button = new TextButton("Route", skin);
        TextButton colonie_button = new TextButton("Colonie", skin);
        TextButton ville_button = new TextButton("Ville", skin);

        final Window pionWindows = new Window("Piocher", skin);


        pionWindows.add(route_button);
        pionWindows.add(colonie_button).padRight(50);
        pionWindows.add(ville_button);


        pionWindows.row();
        //button
        pionWindows.add(deny).padTop(50).padRight(250);
        pionWindows.setSize(1000, 500);
        pionWindows.setPosition(450, stage.getHeight() / 2 - pionWindows.getHeight() / 2);

        stage.addActor(pionWindows);

        //Touch listener route
        route_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pionWindows.setVisible(false);
                pions.setVisible(false);
                pionsCancel.setVisible(true);
                //TODO Afficher emplacement disponible et placer pion
                game.getPartie().setTypeStructure(Constantes.ROUTE);

            }
        });

        //Touch listener colonie
        colonie_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pionWindows.setVisible(false);
                pions.setVisible(false);
                pionsCancel.setVisible(true);
                //TODO Afficher emplacement disponible et placer pion
                game.getPartie().setTypeStructure(Constantes.COLONIE);
            }
        });

        //Touch listener bouton ville
        ville_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pionWindows.setVisible(false);
                pions.setVisible(false);
                pionsCancel.setVisible(true);
                //TODO Afficher emplacement disponible et placer pion
                game.getPartie().setTypeStructure(Constantes.VILLE);

            }
        });

        //Touch listener bouton cancel
        deny.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pionWindows.setVisible(false);

            }
        });
    }


}
