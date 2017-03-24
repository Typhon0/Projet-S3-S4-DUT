package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import com.mygdx.game.Catan;
import com.mygdx.game.autre.Musique;
import com.mygdx.game.model.Constantes;
import com.mygdx.game.model.PaquetRessources;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class HUD {
    private static Catan game;
    public Stage stage;
    private Skin skin;
    private GDXDialogs dialogs;
    private Musique musique;

    public static Catan getGame() {
        return game;
    }

    public HUD(final SpriteBatch sb, final Catan game, Musique mus) {
        musique = mus;

        this.game = game;
        this.game.getPartie().setHud(this);
        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));
        dialogs = GDXDialogsSystem.install();

        stage = new Stage(new ExtendViewport(1920, 1080));

        Table table = new Table();
        table.setSize(1920, 1080);
        //Text Button
        final Button piocher = new Button(skin, "cartedevellopement");
        table.add(piocher).size(170, 170).padRight(1500);

        final Button stat = new Button(skin, "statistiques");
        table.add(stat).size(170, 170);
        table.row();

        //Text Button
        final Button echange = new Button(skin, "echange");//TODO Changer image button
        table.add(echange).size(170, 170).padRight(1500);

        final Button regle = new Button(skin, "help");
        table.add(regle).size(170, 170);
        table.row();

        final Button echangePort = new Button(skin, "echange-port");//TODO Changer image button
        table.add(echangePort).size(170, 170).padRight(1500);

        final Button settings = new Button(skin, "settings");
        table.add(settings).size(170, 170);

        table.row();

        Stack stackPion = new Stack();

        final Button pions = new Button(skin, "pion");
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


        //Listener Bouton piocher carte dévellopement
        piocher.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                acheterCarteDevellopementConfirm();
            }
        });


        //Listener Bouton statistique
        stat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                final Window windows_stat = new Window("Statistique", skin);
                windows_stat.setSize(1500, 1000);
                windows_stat.setPosition(200, 50);
                Button deny = new Button(skin, "deny");
                Label space = new Label(" ", skin);


                final Label j1_label = new Label("Joueur rouge", skin);
                final Label j2_label = new Label("Joueur bleu", skin);
                final Label j3_label = new Label("Joueur vert", skin);
                final Label j4_label = new Label("Joueur jaune", skin);
                j1_label.setColor(Color.RED);
                j2_label.setColor(Color.BLUE);
                j3_label.setColor(Color.GREEN);
                j4_label.setColor(Color.YELLOW);


                final Label wood_count = new Label("0", skin, "big");
                final Label foin_count = new Label("0", skin, "big");
                final Label stone_count = new Label("0", skin, "big");
                final Label mouton_count = new Label("0", skin, "big");
                final Label brick_count = new Label("0", skin, "big");
                final Label route_count = new Label("0", skin, "big");
                final Label colonie_count = new Label("0", skin, "big");
                final Label ville_count = new Label("0", skin, "big");


                final Label wood_count_j2 = new Label("0", skin, "big");
                final Label foin_count_j2 = new Label("0", skin, "big");
                final Label stone_count_j2 = new Label("0", skin, "big");
                final Label mouton_count_j2 = new Label("0", skin, "big");
                final Label brick_count_j2 = new Label("0", skin, "big");
                final Label route_count_j2 = new Label("0", skin, "big");
                final Label colonie_count_j2 = new Label("0", skin, "big");
                final Label ville_count_j2 = new Label("0", skin, "big");


                final Label wood_count_j3 = new Label("0", skin, "big");
                final Label foin_count_j3 = new Label("0", skin, "big");
                final Label stone_count_j3 = new Label("0", skin, "big");
                final Label mouton_count_j3 = new Label("0", skin, "big");
                final Label brick_count_j3 = new Label("0", skin, "big");
                final Label route_count_j3 = new Label("0", skin, "big");
                final Label colonie_count_j3 = new Label("0", skin, "big");
                final Label ville_count_j3 = new Label("0", skin, "big");


                final Label wood_count_j4 = new Label("0", skin, "big");
                final Label foin_count_j4 = new Label("0", skin, "big");
                final Label stone_count_j4 = new Label("0", skin, "big");
                final Label mouton_count_j4 = new Label("0", skin, "big");
                final Label brick_count_j4 = new Label("0", skin, "big");
                final Label route_count_j4 = new Label("0", skin, "big");
                final Label colonie_count_j4 = new Label("0", skin, "big");
                final Label ville_count_j4 = new Label("0", skin, "big");


                //image bois
                Texture texture_img_wood = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "bois.png"));
                Image image_wood = new Image(texture_img_wood);
                windows_stat.add(image_wood);
                //image foin
                Texture texture_img_hay = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "ble.png"));
                Image image_hay = new Image(texture_img_hay);
                windows_stat.add(image_hay);

                //image pierre
                Texture texture_img_rock = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "minerai.png"));
                Image image_rock = new Image(texture_img_rock);
                windows_stat.add(image_rock);

                //image mouton
                Texture texture_img_sheep = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "laine.png"));
                Image image_sheep = new Image(texture_img_sheep);
                windows_stat.add(image_sheep);

                //image brique
                Texture texture_img_brick = new Texture(Gdx.files.internal(Constantes.CHEMIN_ACCES_RESSOURCES + "argile.png"));
                Image image_brick = new Image(texture_img_brick);

                Label route_label = new Label("Route", skin);
                Label colonie_label = new Label("Colonie", skin);
                Label ville_label = new Label("Ville", skin);


                //J1
                wood_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetRessources().getRessources()[1]));
                foin_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetRessources().getRessources()[2]));
                stone_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetRessources().getRessources()[3]));
                mouton_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetRessources().getRessources()[4]));
                brick_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetRessources().getRessources()[5]));
                route_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN]));
                colonie_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 1]));
                ville_count.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 2]));


                wood_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetRessources().getRessources()[1]));
                foin_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetRessources().getRessources()[2]));
                stone_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetRessources().getRessources()[3]));
                mouton_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetRessources().getRessources()[4]));
                brick_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetRessources().getRessources()[5]));
                route_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN]));
                colonie_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 1]));
                ville_count_j2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 2]));

                wood_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetRessources().getRessources()[1]));
                foin_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetRessources().getRessources()[2]));
                stone_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetRessources().getRessources()[3]));
                mouton_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetRessources().getRessources()[4]));
                brick_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetRessources().getRessources()[5]));
                route_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN]));
                colonie_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 1]));
                ville_count_j3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 2]));

                wood_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetRessources().getRessources()[1]));
                foin_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetRessources().getRessources()[2]));
                stone_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetRessources().getRessources()[3]));
                mouton_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetRessources().getRessources()[4]));
                brick_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetRessources().getRessources()[5]));
                route_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN]));
                colonie_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 1]));
                ville_count_j4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPaquetStructures()[Constantes.NUMERO_STRUCTURE_MIN + 2]));

                windows_stat.row();
                windows_stat.add(space);
                windows_stat.add(image_wood);
                windows_stat.add(image_sheep);
                windows_stat.add(image_hay);
                windows_stat.add(image_brick);
                windows_stat.add(image_rock);
                windows_stat.add(route_label).padLeft(100);
                windows_stat.add(colonie_label).padLeft(50);
                windows_stat.add(ville_label).padLeft(50);


                windows_stat.row();

                windows_stat.add(j1_label);
                windows_stat.add(wood_count);
                windows_stat.add(foin_count);
                windows_stat.add(stone_count);
                windows_stat.add(mouton_count);
                windows_stat.add(brick_count);

                windows_stat.add(ville_count);
                windows_stat.add(colonie_count);
                windows_stat.add(route_count);


                windows_stat.row();

                windows_stat.add(j2_label);
                windows_stat.add(wood_count_j2);
                windows_stat.add(foin_count_j2);
                windows_stat.add(stone_count_j2);
                windows_stat.add(mouton_count_j2);
                windows_stat.add(brick_count_j2);
                windows_stat.add(ville_count_j2);
                windows_stat.add(colonie_count_j2);
                windows_stat.add(route_count_j2);



                windows_stat.row();

                windows_stat.add(j3_label);
                windows_stat.add(wood_count_j3);
                windows_stat.add(foin_count_j3);
                windows_stat.add(stone_count_j3);
                windows_stat.add(mouton_count_j3);
                windows_stat.add(brick_count_j3);
                windows_stat.add(ville_count_j3);
                windows_stat.add(colonie_count_j3);
                windows_stat.add(route_count_j3);



                windows_stat.row();

                windows_stat.add(j4_label);
                windows_stat.add(wood_count_j4);
                windows_stat.add(foin_count_j4);
                windows_stat.add(stone_count_j4);
                windows_stat.add(mouton_count_j4);
                windows_stat.add(brick_count_j4);
                windows_stat.add(ville_count_j4);
                windows_stat.add(colonie_count_j4);
                windows_stat.add(route_count_j4);



                windows_stat.row();
                windows_stat.add(deny);

                deny.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        windows_stat.setVisible(false);
                    }
                });

                stage.addActor(windows_stat);


            }
        });

        //Listener Bouton echange
        echange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowsEchange();

            }
        });

        //Listener Bouton echangePort
        echangePort.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (game.getPartie().getJoueurActif().possedePort() == true) {
                    windowEchangePort();
                } else {
                    afficherMessage("Information", "Vous ne possedez pas de port");
                }


            }
        });


        //Listener Bouton regle
        regle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                getGame().setScreen(new HelpScreen(getGame(), musique));
            }
        });

        //Listener Bouton settings
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new OptionScreen(getGame(), true, musique));
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

    /**
     * Affiche la fenêtre de confirmation pour acheter une carte de dévellopement
     */
    public void acheterCarteDevellopementConfirm() {
        ;
        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        bDialog.setTitle("Achat de carte de développement");
        bDialog.setMessage("Confirmer l'achat d'une carte de développement?");
        bDialog.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
                System.out.println(button);

                if (button == 1) { //Annuler

                } else if (button == 0) { //Oui
                    game.getPartie().getJoueurActif().acheterCarteDeveloppement();

                }
            }
        });
        bDialog.addButton(" Acheter ");
        bDialog.addButton(" Annuler ");
        bDialog.build().show();
    }


    /**
     * Affiche la fenêtre de confirmation pour quitter la partie
     */
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
                            game.setScreen(new MainMenu(game, musique));
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
     * Affiche la fenêtre de confirmation pour afficher un tour
     */
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
                    game.getPartie().finDeTour();

                }
            }
        });
        bDialog.addButton(" Oui ");
        bDialog.addButton(" Annuler ");

        bDialog.build().show();

    }

    /**
     * Affiche la fênetre d'échange ou est selectionner
     * le joueur avec qui l'on veut échanger.
     */
    public void windowsEchange() {

        TextButton Jbleu_button = new TextButton("Bleu", skin);
        TextButton Jrouge_button = new TextButton("Rouge", skin);
        TextButton Jvert_button = new TextButton("Vert", skin);
        TextButton Jjaune_button = new TextButton("Jaune", skin);

        switch (game.getPartie().getJoueurActif().getCouleur()) {
            case Constantes.COULEUR_BLEU:
                Jbleu_button.setVisible(false);
                break;
            case Constantes.COULEUR_JAUNE:
                Jjaune_button.setVisible(false);

                break;
            case Constantes.COULEUR_ROUGE:
                Jrouge_button.setVisible(false);

                break;
            case Constantes.COULEUR_VERT:
                Jvert_button.setVisible(false);

                break;
            default:
                break;

        }

        Button deny = new Button(skin, "deny");


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
                game.getPartie().setJoueurAQuiOnVeutEchanger(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU]);
                windowEchange2();
            }
        });

        //Touch listener bouton rouge
        Jrouge_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getPartie().setJoueurAQuiOnVeutEchanger(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE]);
                windowEchange2();
            }
        });

        //Touch listener bouton vert
        Jvert_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getPartie().setJoueurAQuiOnVeutEchanger(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT]);
                windowEchange2();
            }
        });

        //Touch listener bouton jaune
        Jjaune_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getPartie().setJoueurAQuiOnVeutEchanger(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE]);
                windowEchange2();
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

    /**
     * Affiche la fênetre d'échange ou
     * est selectionner le nombre de ressource échanger.
     */
    public void windowEchange2() {

        final int[] ressources_J1 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];
        int[] ressources_J2 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];


        Button deny = new Button(skin, "deny");
        Button confirm = new Button(skin, "confirm");
        Label J1_label = new Label("Joueur 1", skin);
        Label J2_label = new Label("Joueur 2", skin);
        Label space = new Label(" ", skin);


        //J1 button
        final Label bois_label = new Label("Bois", skin);
        final Label count_bois_J1 = new Label("0", skin, "big");
        Button plus_bois_J1 = new Button(skin, "plus");
        Button minus_bois_J1 = new Button(skin, "minus");

        Label argile_label = new Label("Argile", skin);
        final Label count_argile_J1 = new Label("0", skin, "big");
        Button plus_argile_J1 = new Button(skin, "plus");
        Button minus_argile_J1 = new Button(skin, "minus");

        Label laine_label = new Label("Laine", skin);
        final Label count_laine_J1 = new Label("0", skin, "big");
        Button plus_laine_J1 = new Button(skin, "plus");
        Button minus_laine_J1 = new Button(skin, "minus");

        Label pierre_label = new Label("Pierre", skin);
        final Label count_pierre_J1 = new Label("0", skin, "big");
        Button plus_pierre_J1 = new Button(skin, "plus");
        Button minus_pierre_J1 = new Button(skin, "minus");

        Label ble_label = new Label("Blé", skin);
        final Label count_ble_J1 = new Label("0", skin, "big");
        Button plus_ble_J1 = new Button(skin, "plus");
        Button minus_ble_J1 = new Button(skin, "minus");

        //J2 Button

        Label bois_label_J2 = new Label("Bois", skin);
        final Label count_bois_J2 = new Label("0", skin, "big");
        Button plus_bois_J2 = new Button(skin, "plus");
        Button minus_bois_J2 = new Button(skin, "minus");

        Label argile_label_J2 = new Label("Argile", skin);
        final Label count_argile_J2 = new Label("0", skin, "big");
        Button plus_argile_J2 = new Button(skin, "plus");
        Button minus_argile_J2 = new Button(skin, "minus");

        Label laine_label_J2 = new Label("Laine", skin);
        final Label count_laine_J2 = new Label("0", skin, "big");
        Button plus_laine_J2 = new Button(skin, "plus");
        Button minus_laine_J2 = new Button(skin, "minus");

        Label pierre_label_J2 = new Label("Pierre", skin);
        final Label count_pierre_J2 = new Label("0", skin, "big");
        Button plus_pierre_J2 = new Button(skin, "plus");
        Button minus_pierre_J2 = new Button(skin, "minus");

        Label ble_label_J2 = new Label("Ble", skin);
        final Label count_ble_J2 = new Label("0", skin, "big");
        Button plus_ble_J2 = new Button(skin, "plus");
        Button minus_ble_J2 = new Button(skin, "minus");


        final Window echange2Windows = new Window("Echange", skin);

        echange2Windows.add(J1_label);
        echange2Windows.add(space);
        echange2Windows.add(space);
        echange2Windows.add(space);
        echange2Windows.add(space);
        echange2Windows.add(J2_label);

        echange2Windows.row();

        echange2Windows.add(bois_label);
        echange2Windows.add(plus_bois_J1);
        echange2Windows.add(count_bois_J1);
        echange2Windows.add(minus_bois_J1);

        echange2Windows.add(bois_label_J2).padLeft(100).padRight(80);
        echange2Windows.add(plus_bois_J2);
        echange2Windows.add(count_bois_J2);
        echange2Windows.add(minus_bois_J2);

        echange2Windows.row();

        echange2Windows.add(laine_label);
        echange2Windows.add(plus_laine_J1);
        echange2Windows.add(count_laine_J1);
        echange2Windows.add(minus_laine_J1);

        echange2Windows.add(laine_label_J2);
        echange2Windows.add(plus_laine_J2);
        echange2Windows.add(count_laine_J2);
        echange2Windows.add(minus_laine_J2);

        echange2Windows.row();

        echange2Windows.add(argile_label);
        echange2Windows.add(plus_argile_J1);
        echange2Windows.add(count_argile_J1);
        echange2Windows.add(minus_argile_J1);

        echange2Windows.add(argile_label_J2);
        echange2Windows.add(plus_argile_J2);
        echange2Windows.add(count_argile_J2);
        echange2Windows.add(minus_argile_J2);

        echange2Windows.row();

        echange2Windows.add(pierre_label);
        echange2Windows.add(plus_pierre_J1);
        echange2Windows.add(count_pierre_J1);
        echange2Windows.add(minus_pierre_J1);

        echange2Windows.add(pierre_label_J2);
        echange2Windows.add(plus_pierre_J2);
        echange2Windows.add(count_pierre_J2);
        echange2Windows.add(minus_pierre_J2);

        echange2Windows.row();

        echange2Windows.add(ble_label);
        echange2Windows.add(plus_ble_J1);
        echange2Windows.add(count_ble_J1);
        echange2Windows.add(minus_ble_J1);

        echange2Windows.add(ble_label_J2);
        echange2Windows.add(plus_ble_J2);
        echange2Windows.add(count_ble_J2);
        echange2Windows.add(minus_ble_J2);

        echange2Windows.row();
        //button
        echange2Windows.add(deny);
        echange2Windows.add(confirm);

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

        //Touch listener bouton valider
        confirm.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int[] ressource_j1 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];
                ressource_j1[Constantes.ARGILE] = Integer.valueOf(count_argile_J1.getText().toString());
                ressource_j1[Constantes.BLE] = Integer.valueOf(count_ble_J1.getText().toString());
                ressource_j1[Constantes.BOIS] = Integer.valueOf(count_bois_J1.getText().toString());
                ressource_j1[Constantes.LAINE] = Integer.valueOf(count_laine_J1.getText().toString());
                ressource_j1[Constantes.MINERAI] = Integer.valueOf(count_pierre_J1.getText().toString());


                int[] ressource_j2 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];

                ressource_j2[Constantes.ARGILE] = Integer.valueOf(count_argile_J2.getText().toString());
                ressource_j2[Constantes.BLE] = Integer.valueOf(count_ble_J2.getText().toString());
                ressource_j2[Constantes.BOIS] = Integer.valueOf(count_bois_J2.getText().toString());
                ressource_j2[Constantes.LAINE] = Integer.valueOf(count_laine_J2.getText().toString());
                ressource_j2[Constantes.MINERAI] = Integer.valueOf(count_pierre_J2.getText().toString());

                System.out.println("Ressource J1");
                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    System.out.println(ressource_j1[i]);
                }

                System.out.println("Ressource J2");
                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    System.out.println(ressource_j2[i]);
                }


                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    PaquetRessources.recevoirRessource(game.getPartie().getJoueurActif().getPaquetRessources(),
                            game.getPartie().getJoueurAQuiOnVeutEchanger().getPaquetRessources(),
                            i, ressource_j2[i]);
                }

                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    PaquetRessources.recevoirRessource(game.getPartie().getJoueurAQuiOnVeutEchanger().getPaquetRessources(),
                            game.getPartie().getJoueurActif().getPaquetRessources(),
                            i, ressource_j1[i]);
                }


                System.out.println("Ressource J1");
                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    System.out.println(game.getPartie().getJoueurActif().getPaquetRessources().getRessources()[i]);
                }

                System.out.println("Ressource J2");
                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    System.out.println(game.getPartie().getJoueurAQuiOnVeutEchanger().getPaquetRessources().getRessources()[i]);
                }
                echange2Windows.setVisible(false);


            }
        });

        plus_bois_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J1.getText().toString());
                tmp_count++;
                count_bois_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_bois_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J1.getText().toString());
                    if (tmp_count>0)
                tmp_count--;
                count_bois_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_bois_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J2.getText().toString());
                tmp_count++;
                count_bois_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_bois_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_bois_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_laine_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J1.getText().toString());
                tmp_count++;
                count_laine_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_laine_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_laine_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_laine_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J2.getText().toString());
                tmp_count++;
                count_laine_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_laine_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_laine_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_argile_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J1.getText().toString());
                tmp_count++;
                count_argile_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_argile_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_argile_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_argile_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J2.getText().toString());
                tmp_count++;
                count_argile_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_argile_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_argile_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_pierre_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J1.getText().toString());
                tmp_count++;
                count_pierre_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_pierre_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_pierre_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_pierre_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J2.getText().toString());
                tmp_count++;
                count_pierre_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_pierre_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_pierre_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_ble_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J1.getText().toString());
                tmp_count++;
                count_ble_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_ble_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_ble_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_ble_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J2.getText().toString());
                tmp_count++;
                count_ble_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_ble_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_ble_J2.setText(String.valueOf(tmp_count));
            }
        });


    }

    /**
     * Affiche la fênetre d'échange du port ou
     * est selectionner le nombre de ressource échanger.
     */
    public void windowEchangePort() {


        final int[] ressources_J1 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];
        int[] ressources_J2 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];


        Button deny = new Button(skin, "deny");
        Button confirm = new Button(skin, "confirm");
        Label J1_label = new Label("Joueur 1", skin);
        Label J2_label = new Label("Joueur 2", skin);
        Label space = new Label(" ", skin);


        //J1 button
        final Label bois_label = new Label("Bois", skin);
        final Label count_bois_J1 = new Label("0", skin, "big");
        Button plus_bois_J1 = new Button(skin, "plus");
        Button minus_bois_J1 = new Button(skin, "minus");

        Label argile_label = new Label("Argile", skin);
        final Label count_argile_J1 = new Label("0", skin, "big");
        Button plus_argile_J1 = new Button(skin, "plus");
        Button minus_argile_J1 = new Button(skin, "minus");

        Label laine_label = new Label("Laine", skin);
        final Label count_laine_J1 = new Label("0", skin, "big");
        Button plus_laine_J1 = new Button(skin, "plus");
        Button minus_laine_J1 = new Button(skin, "minus");

        Label pierre_label = new Label("Pierre", skin);
        final Label count_pierre_J1 = new Label("0", skin, "big");
        Button plus_pierre_J1 = new Button(skin, "plus");
        Button minus_pierre_J1 = new Button(skin, "minus");

        Label ble_label = new Label("Blé", skin);
        final Label count_ble_J1 = new Label("0", skin, "big");
        Button plus_ble_J1 = new Button(skin, "plus");
        Button minus_ble_J1 = new Button(skin, "minus");

        //J2 Button

        Label bois_label_J2 = new Label("Bois", skin);
        final Label count_bois_J2 = new Label("0", skin, "big");
        Button plus_bois_J2 = new Button(skin, "plus");
        Button minus_bois_J2 = new Button(skin, "minus");

        Label argile_label_J2 = new Label("Argile", skin);
        final Label count_argile_J2 = new Label("0", skin, "big");
        Button plus_argile_J2 = new Button(skin, "plus");
        Button minus_argile_J2 = new Button(skin, "minus");

        Label laine_label_J2 = new Label("Laine", skin);
        final Label count_laine_J2 = new Label("0", skin, "big");
        Button plus_laine_J2 = new Button(skin, "plus");
        Button minus_laine_J2 = new Button(skin, "minus");

        Label pierre_label_J2 = new Label("Pierre", skin);
        final Label count_pierre_J2 = new Label("0", skin, "big");
        Button plus_pierre_J2 = new Button(skin, "plus");
        Button minus_pierre_J2 = new Button(skin, "minus");

        Label ble_label_J2 = new Label("Ble", skin);
        final Label count_ble_J2 = new Label("0", skin, "big");
        Button plus_ble_J2 = new Button(skin, "plus");
        Button minus_ble_J2 = new Button(skin, "minus");


        final Window echange2WindowsPort = new Window("Echange", skin);

        echange2WindowsPort.add(J1_label);
        echange2WindowsPort.add(space);
        echange2WindowsPort.add(space);
        echange2WindowsPort.add(space);
        echange2WindowsPort.add(space);
        echange2WindowsPort.add(J2_label);

        echange2WindowsPort.row();

        echange2WindowsPort.add(bois_label);
        echange2WindowsPort.add(plus_bois_J1);
        echange2WindowsPort.add(count_bois_J1);
        echange2WindowsPort.add(minus_bois_J1);

        echange2WindowsPort.add(bois_label_J2).padLeft(100).padRight(80);
        echange2WindowsPort.add(plus_bois_J2);
        echange2WindowsPort.add(count_bois_J2);
        echange2WindowsPort.add(minus_bois_J2);

        echange2WindowsPort.row();

        echange2WindowsPort.add(laine_label);
        echange2WindowsPort.add(plus_laine_J1);
        echange2WindowsPort.add(count_laine_J1);
        echange2WindowsPort.add(minus_laine_J1);

        echange2WindowsPort.add(laine_label_J2);
        echange2WindowsPort.add(plus_laine_J2);
        echange2WindowsPort.add(count_laine_J2);
        echange2WindowsPort.add(minus_laine_J2);

        echange2WindowsPort.row();

        echange2WindowsPort.add(argile_label);
        echange2WindowsPort.add(plus_argile_J1);
        echange2WindowsPort.add(count_argile_J1);
        echange2WindowsPort.add(minus_argile_J1);

        echange2WindowsPort.add(argile_label_J2);
        echange2WindowsPort.add(plus_argile_J2);
        echange2WindowsPort.add(count_argile_J2);
        echange2WindowsPort.add(minus_argile_J2);

        echange2WindowsPort.row();

        echange2WindowsPort.add(pierre_label);
        echange2WindowsPort.add(plus_pierre_J1);
        echange2WindowsPort.add(count_pierre_J1);
        echange2WindowsPort.add(minus_pierre_J1);

        echange2WindowsPort.add(pierre_label_J2);
        echange2WindowsPort.add(plus_pierre_J2);
        echange2WindowsPort.add(count_pierre_J2);
        echange2WindowsPort.add(minus_pierre_J2);

        echange2WindowsPort.row();

        echange2WindowsPort.add(ble_label);
        echange2WindowsPort.add(plus_ble_J1);
        echange2WindowsPort.add(count_ble_J1);
        echange2WindowsPort.add(minus_ble_J1);

        echange2WindowsPort.add(ble_label_J2);
        echange2WindowsPort.add(plus_ble_J2);
        echange2WindowsPort.add(count_ble_J2);
        echange2WindowsPort.add(minus_ble_J2);

        echange2WindowsPort.row();
        //button
        echange2WindowsPort.add(deny);
        echange2WindowsPort.add(confirm);

        echange2WindowsPort.setSize(1500, 1000);
        echange2WindowsPort.setPosition(450, stage.getHeight() / 2 - echange2WindowsPort.getHeight() / 2);

        stage.addActor(echange2WindowsPort);


        //Touch listener bouton cancel
        deny.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                echange2WindowsPort.setVisible(false);

            }
        });

        //Touch listener bouton valider
        confirm.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                int[] ressource_j1 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];
                ressource_j1[Constantes.ARGILE] = Integer.valueOf(count_argile_J1.getText().toString());
                ressource_j1[Constantes.BLE] = Integer.valueOf(count_ble_J1.getText().toString());
                ressource_j1[Constantes.BOIS] = Integer.valueOf(count_bois_J1.getText().toString());
                ressource_j1[Constantes.LAINE] = Integer.valueOf(count_laine_J1.getText().toString());
                ressource_j1[Constantes.MINERAI] = Integer.valueOf(count_pierre_J1.getText().toString());


                int[] ressource_j2 = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];

                ressource_j2[Constantes.ARGILE] = Integer.valueOf(count_argile_J2.getText().toString());
                ressource_j2[Constantes.BLE] = Integer.valueOf(count_ble_J2.getText().toString());
                ressource_j2[Constantes.BOIS] = Integer.valueOf(count_bois_J2.getText().toString());
                ressource_j2[Constantes.LAINE] = Integer.valueOf(count_laine_J2.getText().toString());
                ressource_j2[Constantes.MINERAI] = Integer.valueOf(count_pierre_J2.getText().toString());

                int sumj1 = 0, sumj2 = 0;

                for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                    sumj1 = sumj1 + ressource_j1[i];
                    sumj2 = sumj2 +
                            ressource_j2[i];
                }
                System.out.println("Somme " + sumj1 + " " + sumj2);

                if (sumj1 == (sumj2 * game.getPartie().getJoueurActif().getTauxDeChangeMinimum())) { //Si la somme des valeur du j1 est égale à taux de change fois la somme des valeur j2

                    System.out.println("Ressource J1");
                    for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                        System.out.println(ressource_j1[i]);
                    }

                    System.out.println("Ressource J2");
                    for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                        System.out.println(ressource_j2[i]);
                    }


                    for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                        PaquetRessources.recevoirRessource(game.getPartie().getJoueurActif().getPaquetRessources(),
                                game.getPartie().getPlateau().getRessources(),
                                i, ressource_j2[i]);
                    }

                    game.getPartie().getPlateau().getRessources();

                    for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                        PaquetRessources.recevoirRessource(game.getPartie().getPlateau().getRessources(),
                                game.getPartie().getJoueurActif().getPaquetRessources(),
                                i, ressource_j1[i]);
                    }


                    System.out.println("Ressource J1");
                    for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                        System.out.println(game.getPartie().getJoueurActif().getPaquetRessources().getRessources()[i]);
                    }

                    System.out.println("Ressource J2");
                    for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < Constantes.TAILLE_TABLEAU_RESSOURCE; i++) {
                        System.out.println(game.getPartie().getPlateau().getRessources().getRessources()[i]);
                    }
                    echange2WindowsPort.setVisible(false);
                } else {
                    afficherMessage("Erreur echange", "Vous devez echanger avec le port 1 pour 3");
                }


            }
        });


        plus_bois_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J1.getText().toString());
                tmp_count++;
                count_bois_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_bois_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_bois_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_bois_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J2.getText().toString());
                tmp_count++;
                count_bois_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_bois_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_bois_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_bois_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_laine_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J1.getText().toString());
                tmp_count++;
                count_laine_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_laine_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_laine_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_laine_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J2.getText().toString());
                tmp_count++;
                count_laine_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_laine_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_laine_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_laine_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_argile_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J1.getText().toString());
                tmp_count++;
                count_argile_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_argile_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_argile_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_argile_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J2.getText().toString());
                tmp_count++;
                count_argile_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_argile_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_argile_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_argile_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_pierre_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J1.getText().toString());
                tmp_count++;
                count_pierre_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_pierre_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_pierre_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_pierre_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J2.getText().toString());
                tmp_count++;
                count_pierre_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_pierre_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_pierre_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_pierre_J2.setText(String.valueOf(tmp_count));
            }
        });


        plus_ble_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J1.getText().toString());
                tmp_count++;
                count_ble_J1.setText(String.valueOf(tmp_count));
            }
        });

        minus_ble_J1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J1.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_ble_J1.setText(String.valueOf(tmp_count));
            }
        });

        plus_ble_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J2.getText().toString());
                tmp_count++;
                count_ble_J2.setText(String.valueOf(tmp_count));
            }
        });

        minus_ble_J2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int tmp_count = Integer.valueOf(count_ble_J2.getText().toString());
                if (tmp_count>0)
                tmp_count--;
                count_ble_J2.setText(String.valueOf(tmp_count));
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

        //Touch listener bouton cancelpion
        pionsCancel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getPartie().setTypeStructure(0);
            }
        });
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

}
