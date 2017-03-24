package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;
import com.mygdx.game.autre.Musique;
import com.mygdx.game.model.Constantes;
import com.mygdx.game.model.De;
import com.mygdx.game.model.Joueur;
import com.mygdx.game.model.Partie;
import com.mygdx.game.model.Plateau;
import com.mygdx.game.model.SiteConstruction;
import com.mygdx.game.model.State;
import com.mygdx.game.model.Tuile;
import com.mygdx.game.model.Voleur;

import java.util.ArrayList;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

import static com.mygdx.game.model.Plateau.*;

/**
 * Created by typhon0 on 01/03/17.
 */

public class GameScreen implements Screen, InputProcessor {
    private Catan game;
    public Sprite sprite;
    public FloatArray vertices, v2, v3;
    public Vector2 center;
    public Texture texture, texture2, textureBrick;
    public TextureRegion texture3, texreg;
    public PolygonSprite polygonSprite, polygonSprite2;
    public ArrayList<PolygonRegion> listePolygonRegion;
    public Skin skin;
    public GDXDialogs dialogs;
    public Stage stage;
    private HUD hud;

    private Musique musique;


    private State state = State.RUN; // status du jeu


    Label point_vic_J1;
    Label point_vic_J2;
    Label point_vic_J3;
    Label point_vic_J4;

    public GameScreen(Catan g, Musique mus) {
        musique = mus;

        this.game = g;
        try {
            dialogs = GDXDialogsSystem.install();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        state = State.RUN;
        hud = new HUD(game.batch, g, musique);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud.stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));

        point_vic_J1 = new Label("0", skin, "big");
        point_vic_J2 = new Label("0", skin, "big");
        point_vic_J3 = new Label("0", skin, "big");
        point_vic_J4 = new Label("0", skin, "big");


    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {

        switch (state) {
            case RUN:
                Gdx.gl.glClearColor(0, 130, 175, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                //Plateau
                drawBoard();
                //HUD
                //game.batch2.setProjectionMatrix(hud.stage.getCamera().combined);

                Table table2 = new Table();
                table2.setSize(1920, 1080);
                table2.setPosition(700,-470);

                point_vic_J1.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_BLEU].getPoints()));
                point_vic_J1.setColor(Color.BLUE);
                point_vic_J2.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_JAUNE].getPoints()));
                point_vic_J2.setColor(Color.YELLOW);
                point_vic_J3.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_ROUGE].getPoints()));
                point_vic_J3.setColor(Color.RED);
                point_vic_J4.setText(String.valueOf(game.getPartie().getJoueurs()[Constantes.COULEUR_VERT].getPoints()));
                point_vic_J4.setColor(Color.GREEN);

                table2.add(point_vic_J1).padRight(50);
                table2.add(point_vic_J2).padRight(50);
                table2.add(point_vic_J3).padRight(50);
                table2.add(point_vic_J4);

                hud.stage.addActor(table2);

                hud.stage.act();
                hud.stage.draw();

/*
                stage.act(delta);

                stage.draw();
*/

                break;
            case PAUSE:


                break;
            case RESUME:

                break;

            default:
                break;
        }


    }

    public void drawBoard() {

        game.batch.begin();
        // Affichage de la mer
        game.batch.draw(game.getPartie().getPlateau().getTextureMer(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Affichage de chaque Tuile/Terrain
        for (int i = 0; i < game.getPartie().getPlateau().getListeTuiles().size(); i++) {
            game.batch.draw(game.getPartie().getPlateau().getListeTuiles().get(i).getTextureTuile(),
                    game.getPartie().getPlateau().getListeTuiles().get(i).getCoinInferieurGaucheTuile().x,
                    game.getPartie().getPlateau().getListeTuiles().get(i).getCoinInferieurGaucheTuile().y,
                    (float) Math.sqrt(3) * game.getPartie().getPlateau().TAILLE_TUILE,
                    (float) (game.getPartie().getPlateau().TAILLE_TUILE * 2));
        }

        // Affichage de chaque Jeton
        for (int i = 0; i < game.getPartie().getPlateau().getListeTuiles().size(); i++) {
            game.batch.draw(game.getPartie().getPlateau().getListeTuiles().get(i).getJeton().getTextureJeton(),
                    game.getPartie().getPlateau().getListeTuiles().get(i).getCoinInferieurGaucheJeton().x,
                    game.getPartie().getPlateau().getListeTuiles().get(i).getCoinInferieurGaucheJeton().y);
        }

        // Affichage de chaque Port
        for (int i = 0; i < game.getPartie().getPlateau().getListePorts().size(); i++) {
            game.batch.draw(game.getPartie().getPlateau().getListePorts().get(i).getTexturePort(),
                    game.getPartie().getPlateau().getListePorts().get(i).getCoinInferieurGauchePort().x,
                    game.getPartie().getPlateau().getListePorts().get(i).getCoinInferieurGauchePort().y,
                    64,
                    64);
        }

        game.batch.end();

        // Affichage du squelette en surcouche
        for (int i = 0; i < game.getPartie().getPlateau().getListeTuiles().size(); i++) {
            game.sr.begin(ShapeRenderer.ShapeType.Line);
            game.sr.polygon(game.getPartie().getPlateau().getListeTuiles().get(i).getVertices());
            game.sr.setColor(0, 0, 0, 1);
            Gdx.gl.glLineWidth(5);
            game.sr.end();


        }


        // Affichage de chaque Site de construction et de route du plateau et non des joueurs
        /*
        for (int i = 0; i < game.getPartie().getPlateau().getListeTuiles().size(); i++) {
            for (int j = 0; j < game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstruction().size(); j++) {
                game.batch.draw( Plateau.getVilleRouge(),
                        game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstruction().get(j).getCoinInferieurGaucheSiteConstruction().x,
                        game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstruction().get(j).getCoinInferieurGaucheSiteConstruction().y,
                        Constantes.DISTANCE_SITE_CONSTRUCTION_X*2,
                        Constantes.DISTANCE_SITE_CONSTRUCTION_X*2);
            }
            for (int j = 0; j < game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstructionRoute().size(); j++) {
                game.batch.draw( Plateau.getRouteRouge(),
                        game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstructionRoute().get(j).getCoinInferieurGaucheSiteConstruction().x,
                        game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstructionRoute().get(j).getCoinInferieurGaucheSiteConstruction().y,
                        Constantes.DISTANCE_SITE_CONSTRUCTION_X*2,
                        Constantes.DISTANCE_SITE_CONSTRUCTION_X*2);
            }
        }
        */

        // Afficher le voleur
        Voleur v = game.getPartie().getPlateau().getVoleur();
        Tuile t = v.getTuile();

        game.batch.begin();

        game.batch.draw(v.getTexture(), t.getCoinInferieurGaucheJeton().x, t.getCoinInferieurGaucheJeton().y, DELTA_X, DELTA_X);

        // Affichage des structures de chaque joueur
        for (int i = 0; i < game.getPartie().getJoueurs().length; i++) {
            for (int j = 0; j < game.getPartie().getJoueurs()[i].getListeStructures().size(); j++) {
                game.batch.draw(game.getPartie().getJoueurs()[i].getListeStructures().get(j).getTexture(),
                        game.getPartie().getJoueurs()[i].getListeStructures().get(j).getSc().getCoinInferieurGaucheSiteConstruction().x,
                        game.getPartie().getJoueurs()[i].getListeStructures().get(j).getSc().getCoinInferieurGaucheSiteConstruction().y,
                        Constantes.DISTANCE_SITE_CONSTRUCTION_X * 2,
                        Constantes.DISTANCE_SITE_CONSTRUCTION_X * 2);
            }
        }

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            this.state = State.PAUSE;
            quitGameConfirm();

        }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Le pixel (touchDown) " + screenX + " " + screenY + " a été touché");
        screenY = Gdx.graphics.getHeight() - 1 - screenY;
        System.out.println("Le pixel (Corrigé) " + screenX + " " + screenY + " a été touché");

        Joueur joueur = game.getPartie().getJoueurActif();
        // Si une construction a été sélectionnée
        if (game.getPartie().getPlateau().getVoleur().isActif()) {
            Vector2 pixel = new Vector2(screenX, screenY);
            Voleur v = game.getPartie().getPlateau().getVoleur();
            for (int i = 0; i < game.getPartie().getPlateau().getListeTuiles().size(); i++) {
                Tuile t = game.getPartie().getPlateau().getListeTuiles().get(i);
                if (t.equals(pixel)) {
                    Partie.getHud().afficherMessage("Voleur déplacé", "Le voleur a été affecté à une tuile");
                    game.getPartie().actionVoleur(t);
                }
            }
        } else {
            if (game.getPartie().getTypeStructure() >= Constantes.NUMERO_STRUCTURE_MIN && game.getPartie().getTypeStructure() <= Constantes.NUMERO_STRUCTURE_MAX) {
                for (int i = 0; i < game.getPartie().getPlateau().getListeTuiles().size(); i++) {
                    for (int j = 0; j < game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstruction().size(); j++) {
                        if (game.getPartie().getTypeStructure() == Constantes.ROUTE) {
                            if (game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstructionRoute().get(j).estToucheInt(screenX, screenY)) {
                                SiteConstruction sc = game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstructionRoute().get(j);
                                joueur.construireRoute(sc);
                                return false;
                            }
                        } else {
                            if (game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstruction().get(j).estToucheInt(screenX, screenY)) {
                                SiteConstruction sc = game.getPartie().getPlateau().getListeTuiles().get(i).getListeSitesConstruction().get(j);
                                // Colonie
                                if (game.getPartie().getTypeStructure() == Constantes.COLONIE) {
                                    joueur.construireColonie(sc);
                                }
                                // Ville
                                else {
                                    joueur.construireVille(sc);
                                }
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
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
                    setState(State.RUN);
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



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void setState(State t) {
        this.state = t;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
