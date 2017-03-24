package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;
import com.mygdx.game.autre.Musique;
import com.mygdx.game.model.Constantes;


/**
 * Created by typhon0 on 28/02/17.
 */

public class HelpScreen implements Screen {

    private Skin skin;
    private Stage stage;
    private Catan game;
    private Musique musique;



    // constructor to keep a reference to the main Game class
    public HelpScreen(Catan pgame, Musique mus) {
        musique = mus;

        this.game = pgame;

        stage = new Stage(new ExtendViewport(800, 500));

        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));
        Label regle = new Label(null, skin);
        regle.setColor(Color.BLACK);
        regle.setText("Cette regle contient toutes les informations importantes dont vous avez besoin pour jouer !\n\n" +
                "Materiel de jeu : \n\n" +
                "•\t37 hexagones representant des terrains. \n" +
                "•\t95 cartes Matieres premieres (bois, laine, ble, argile, minerai). \n" +
                "•\t25 cartes Developpement. \n" +
                "•\t4 fiches \"Coûts de construction\". \n" +
                "•\t2 cartes speciales : \"Route la plus longue\" et \"Chevalier le plus puissant\". \n" +
                "•\t16 villes (eglises). \n" +
                "•\t 20 colonies (maisons). \n" +
                "•\t 60 routes (batonnets). \n" +
                "•\t18 jetons numerotes. \n" +
                "•\t1 pion Brigand. \n" +
                "•\t2 des.\n\n" +
                "Mise en place de l’ile : \n\n" +
                "Le plateau est genere aleatoirement ! L’ile de Catane va donc changer d’une partie a l’autre.\n" +
                "Preparation : \n" +
                "•\tChaque joueur choisit une couleur et recoit 5 colonies, 4 villes et 15 routes. Il pose sur l'ile 2 routes et 2 colonies comme indique a la page precedente, et garde le reste devant lui.\n" +
                "•\t S'il n'y a que 3 joueurs, les pions rouges sont ecartes.\n" +
                "•\t Chaque joueur prend une fiche \"Coûts de construction\". \n" +
                "•\tLes cartes speciales \"Route la plus longue\" et \"Chevalier le plus puissant\" ainsi que les des sont a la disposition des joueurs et sont places a cote du plateau. \n" +
                "•\tLes cartes Matieres premieres sont triees par type et posees faces visibles dans les boitiers en plastique. 0n les garde aussi a portee de main !\n" +
                "•\t Les cartes Developpements sont melangees et rangees faces cachees dans la derniere case de rangement du boitier. \n" +
                "•\tEnfin, chaque joueur recoit pour sa colonie marquee d'une etoile, des matieres premieres. Pour chaque terrain touchant sa colonie, il recupere la carte matiere premiere correspondante dans le talon. Exemple : Le joueur bleu recoit pour sa colonie 2 cartes Bois et 1 carte Ble. \n" +
                "•\tChaque joueur garde en main ses cartes Matieres premieres face cachee.\n\n" +
                "Deroulement generale de la partie :\n\n" +
                "Le joueur le plus age commence. Celui dont c'est le tour peut effectuer les actions suivantes dans l'ordre decrit ci-dessous : \n" +
                "1. Il lance les des pour determiner la Recolte de matieres premieres. Le resultat est applique a tous les joueurs. \n" +
                "2. Il peut faire du commerce, c'est-a-dire echanger des matieres premieres - seul ou avec les autres joueurs. \n" +
                "3. Il peut construire des routes, des colonies ou des villes eV ou acheter des cartes Developpement. En plus, il peut jouer une de ses cartes Developpement quand bon lui semble. Puis le tour passe a son voisin de gauche qui commence a jouer en executant I’action 1.\n\n" +
                "Deroulement detaille de la partie :\n\n" +
                "1.\tRecolte de Matieres premieres. \n" +
                "•\tLe joueur commence son tour en lancant les deux des. Leur somme indique les terrains qui rapportent des matieres premieres. \n" +
                "•\tChaque joueur possedant une colonie sur un croisement qui touche un terrain producteur prend la carte matiere premiere qui correspond. Pour les exemples, voir Recolte de matieres premieres. Si le joueur possede 2 ou 3 colonies touchant le terrain producteur, il recoit 1 carte matiere premiere pour chaque colonie. \n\n" +
                "2.\tCommerce \n" +
                "Ensuite le joueur dont c'est le tour peut faire du commerce afin de se procurer les cartes matieres premieres qui lui manquent. \n" +
                "•\tCommerce interieur Le joueur dont c'est le tour peut echanger des cartes matieres premieres avec tous les joueurs. Il peut annoncer les matieres premieres qu'il recherche et ce qu'il est prêt a donner en echange. Il peut aussi ecouter les propositions des autres joueurs et reagir a ces offres. Important : On ne peut commercer qu'avec le joueur dont c'est le tour. Les autres joueurs ne peuvent pas commercer entre eux.\n" +
                "\n" +
                "•\tCommerce maritime Le joueur dont c'est le tour peut aussi faire du commerce sans les autres joueurs ! Il peut echanger a un taux de 4:1 en remettant sur le talon 4 cartes matieres premieres identiques (4 Bois par exemple) en echange d'une carte matiere premiere de son choix. Si le joueur a construit une colonie pres d'un port, il peut echanger a un taux plus avantageux : soit a un taux de 3:1 ou même a un taux de 2:1 dans un port specialise. Par exemple, dans le cas d'un echange dans un port specialise en minerai, le joueur devra echanger 2 de ses minerais contre une matiere premiere de son choix tiree du talon.                                                                                                                                    Important : le taux 4:1 est toujours possible. Il n'est pas necessaire d'avoir une colonie pres d'un port.\n" +
                "\n\n" +
                "3.\tConstruire \n" +
                "A la fin de son tour, le joueur peut construire afin de gagner des points de victoire.\n" +
                "•\tPour construire, le joueur doit se defausser d'une combinaison de cartes matieres premieres (Voir fiche Coûts de construction). En echange, il prend le nombre de routes, de colonies ou de villes correspondant et les place sur le plateau de jeu. \n" +
                "•\tAucun joueur ne peut construire plus de routes, colonies ou villes qu'il n'en possede en reserve ! \n\n" +
                "a)\tRoute : Bois + Argile. \n" +
                "\n" +
                "•\tUne nouvelle route doit toujours être en contact avec une autre construction de la même couleur. \n" +
                "•\tOn ne peut construire qu'une seule route par chemin.\n" +
                "•\tDes qu'un joueur possede une route continue d'au moins 5 troncons, il s'empare de la carte speciale \"Route la plus longue\" qui vaut 2 points de victoire. Si un autre joueur reussit a construire une route plus longue, il recupere la carte \" Route la plus longue\" ainsi que les 2 points de victoire.\n\n" +
                "b)\tColonie : Bois + Argile + Ble + Laine. \n" +
                "•\tImportant : lors de la construction, il faut respecter la regle de distance. On ne peut construire une colonie sur un croisement que si les 3 croisements qui I'entourent sont vides de colonies ou villes. \n" +
                "•\tLa colonie doit être rattachee a au moins une route de la même couleur. \n" +
                "•\tChaque colonie rapporte a son proprietaire 1 carte matiere premiere lorsque le tirage des des indique un terrain qu'elle touche. \n" +
                "•\tChaque Colonie vaut 1 point de victoire. \n" +
                "\n" +
                "c)\tVille : 2X Ble + 3X Minerai. \n" +
                "•\tOn ne peut construire une ville qu'en transformant une colonie deja presente sur le plateau de jeu. \n" +
                "•\tSi un joueur transforme une colonie en ville, il remet la colonie (maison) dans Ia reserve et la remplace par une ville (eglise).\n" +
                "•\tLe proprietaire d'une ville recoit 2 cartes matieres premieres au lieu d'une si un terrain adjacent est tire aux des. \n" +
                "•\tChaque ville vaut 2 points de victoire. \n\n" +
                "d)\tCarte Developpement : Ble + Laine + Minerai \n" +
                "Celui qui achete une carte Developpement pioche la premiere carte du talon.\n" +
                " Il y a 3 types de cartes Developpement ayant des effets differents : Chevalier, Progres et Point de victoire. Les cartes Developpement achetees sont gardees secretes jusqu'a ce que le joueur les utilise.\n" +
                "\n\n" +
                "4. Cas particuliers \n\n" +
                "A. Sept aux des ! Le Brigand entre en action. \n" +
                "•\tSi le joueur dont c'est le tour obtient un \"7\" aux des, aucun des joueurs ne recoit de matieres premieres. \n" +
                "•\tTous les joueurs possedant plus de 7 cartes Ressource en main doivent se debarrasser de la moitie de leurs cartes (on arrondit au nombre inferieur) en s'en defaussant sur les talons correspondants.\n" +
                "•\tEnsuite le joueur doit deplacer le Brigand de la maniere decrite ci-dessous : \n" +
                "1. Le joueur doit deplacer immediatement le Brigand sur un jeton numerote du terrain de son choix.\n" +
                "2. Puis il vole I carte matiere premiere a I'un des joueurs qui possede une colonie ou une ville au bord de ce terrain. Le joueur qui est attaque propose ses cartes matieres premieres face cachee et le voleur en tire une au hasard. Si plusieurs joueurs sont concernes, le voleur choisit sa victime.\n" +
                "Important : Tant que le Brigand se trouve sur un terrain, ce dernier ne produit plus de matiere premiere. Le Brigand reste sur ce Terrain jusqu'a ce qu'on tire a nouveau un \"7\" aux des. \n\n" +
                "B. Jouer une carte Developpement Le joueur dont c'est le tour peut a tout moment jouer une carte Developpement. Celle-ci est alors retournee face visible sur la table. 0n ne peut PAS jouer une carte qu'on vient d'acheter dans ce tour. De même, on ne peut jouer qu'une seule carte Developpement par tour.  \n" +
                "Fin de la partie La partie se termine des qu'un joueur atteint durant son tour 10 points de victoire ou plus.\n");
        regle.setWrap(true);
        ScrollPane sp = new ScrollPane(regle);

        Table table = new Table();

        table.setSize(800, 500);

        //Background
        Texture t = new Texture(Constantes.CHEMIN_ACCES_UI + "background_help.png");
        Drawable d = new TextureRegionDrawable(new TextureRegion(t));
        table.setBackground(d);
        table.setFillParent(true);
/*
        CheckBox easyCB = new CheckBox("  Facile", skin);
        table.add(easyCB);

        */
        table.add(sp).width(700);
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
            game.setScreen(new MainMenu(game, musique));
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

