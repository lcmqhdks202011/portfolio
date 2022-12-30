/* -----------------------------------------------------------------
 * Auteur     : Changmin Lee

 * Date        : 11 mars 2020
 *
 * Description : Ce programme recoit une largeur, une longueur et
 *               un pas, et crée un labyrinthe.
 *
 *------------------------------------------------------------------
 */




var iota = function (n) {

    /* Fonction iota prend un entier n positif et crée un tableau
     * de nombre contenant les nombres entiers en ordre de 0 a n-1.*/

    var table = [];

    for (var i = 0; i < n; i++) {

        table.push(i);
    }

    return table;
};

// Tests unitaires:

var testIota = function () {

    assert(iota(0) == "");
    assert(iota(2) == "0,1");
    assert(iota(6) == "0,1,2,3,4,5");

};


var contient = function (tab, n) {

    /* Fonction contient() prend un tableau de nombre et un nombre entier n 
     * et retourne un boolèen indiquant si ce dernier est contenu dans 
     * le tableau. Si oui, elle retourne true, sinon, false.*/

    for (var i = 0; i < tab.length; i++) {
        if (tab[i] == n) {
            return true;
        }
    }
    return false;
};

// Tests unitaires

var testContient = function () {

    assert(contient([], 3) == false);
    assert(contient([0, 1], 4) == false);
    assert(contient([0, 1, 2, 3, 4, 5], 2) == true);

};



var ajouter = function (tab, n) {

    /* Fonction ajouter() prend un tableau de nombre et un nombre entier n 
     * pour retourner le meme tableau a part si n n'y est pas contenu,
     * il sera alors ajouter a la fin. */

    if (!contient(tab, n)) {      // nombre redondant?(booleen)
        tab.push(n);
        return tab;
    }

    return tab;
};

// Tests Unitaires

var testAjouter = function () {

    assert(ajouter([], 2) == "2");
    assert(ajouter([0, 1, 2, 3], 3) == "0,1,2,3");
    assert(ajouter([0, 1, 2, 3, 4, 5], 8) == "0,1,2,3,4,5,8");

};


var ajouterTab = function (tab, tab2, tab3) {

    /* La  fonction  ajouterTab()  obtient 3  tableaux(tab,tab2,tab3).
     * la fonction verifie s'il y a les memes elements de <<tab2>> dans
     * <<tab>> et <<tab3>> et  elle  met  les  elements qui  n'y sont
     * pas dans <<tab>> et retourne <<tab>>. */

    for (var i = 0; i < tab2.length; i++) {

        if (!contient(tab, tab2[i]) && !contient(tab3, tab2[i])) {
            tab.push(tab2[i]);
        }
    }

    return tab;
};

// Tests Unitaires

var testAjouterTab = function () {

    assert(ajouterTab([], [2], [2]) == "");
    assert(ajouterTab([0, 1, 2, 3], [1, 3, 5, 7], [7]) == "0,1,2,3,5");
    assert(ajouterTab([0, 1, 2, 3, 4, 5], [6, 7, 8], [9, 10]) == "0,1,2,3,4,5,6,7,8");

};



var retirer = function (tab, n) {

    /* La fonction retirer() prend un tableau de nombre et un nombre 
     * entier n, et verifie si ce nombre est contenu dans le tableau.
     * Si oui, la fonction retirera ce dernier du tableau.*/

    var temp = [];
    for (var i = tab.length - 1; i >= 0; i--) {

        if (tab[i] != n) {
            temp.push(tab[i]);
            tab.pop();
        }
        else {
            tab.pop();
            break;
        }
    }

    for (var j = temp.length - 1; j >= 0; j--) {

        tab.push(temp[j]);

    }
    return tab;
};

// Tests Unitaires

var testRetirer = function () {

    assert(retirer([], 1) == "");
    assert(retirer([0, 1, 2, 3, 4], 2) == "0,1,3,4");
    assert(retirer([0, 1, 2, 3], 4) == "0,1,2,3");

};




var voisins = function (x, y, NX, NY) {

    /* la fonction voisins() prend des coordonnees (x,y)
     * et la taille d'une grille NX(largeur) et NY(hauteur).
     * Ensuite elle calcule le numero de cellule et cree un
     * tableau(tab). Apres, elle enregistre les numeros des cell-
     * ule dans la table, en ordre de N-E-S-O.
     * Finalement la fonction retourne le tableau.*/

    var tab = [];

    if (y - 1 >= 0) {
        tab.push(x + (y - 1) * NX);
    }

    if (x + 1 < NX) {
        tab.push((x + 1) + y * NX);
    }

    if (y + 1 < NY) {
        tab.push(x + (y + 1) * NX);
    }

    if (x - 1 >= 0) {
        tab.push((x - 1) + y * NX);
    }

    return tab;
};

// Tests Unitaires

var testVoisins = function () {

    assert(voisins(0, 0, 8, 4) == "1,8");
    assert(voisins(5, 2, 10, 9) == "15,26,35,24");
    assert(voisins(0, 0, 1, 1) == "");

};


var position = function (x, y, pas, nx, ny) {

    /* Cette procedure obtient les entiers
     * x(position horizontale), y(position verticale)
     * pas(longueur du mur), nx, ny (longueur et largeur
     * de la grille.). Ensuite, elle deplacera la tortue
     * pour dessiner un mur. */

    pu();
    mv((x - nx / 2) * pas, (-y + ny / 2) * pas);
    pd();
};

var dessinH = function (pas) {

    /* Cette procedure obtient un entier pas et
     * tourne la tortue deplacee par la procedure 
     * position(), et place une ligne horizontale
     * dont sa longueur et tant long que pas.*/

    rt(90);
    fd(pas);
    lt(90);
};

var dessinV = function (pas) {

    /* Cette procedure obtient un entier pas et
     * tourne la tortue deplacee par la procedure
     * position(), et place une ligne verticale
     * dont sa longueur et tant long que pas.*/

    rt(180);
    fd(pas);
    lt(180);
};

var dessinmursH = function (tab, pas, nx, ny) {

    /* Cette procedure obtient une table(tab) et
     * des entiers (pas, nx, ny) et pour chaque entier
     * dans le tableau, la procedure calcule la position
     * et dessine des lignes horizontaux   */

    var x, y;
    var coord = [];

    for (var i = 0; i < tab.length; i++) {
        coord = coordH(nx, tab[i]);

        x = coord[0];
        y = coord[1];

        position(x, y, pas, nx, ny);
        dessinH(pas);
    }
};

var dessinmursV = function (tab, pas, nx, ny) {

    /* Cette procedure obtient une table(tab) et
     * des entiers (pas, nx, ny) et pour chaque entier
     * dans le tableau, la procedure calcule la position
     * et dessine des lignes verticaux */

    var x, y;
    var coord = [];

    for (var i = 0; i < tab.length; i++) {

        coord = coordV(nx, tab[i]);

        x = coord[0];
        y = coord[1];

        position(x, y, pas, nx, ny);
        dessinV(pas);

    }
};

var tirage = function (tab) {

    /* fonction tirage() obtient un tableau et retourne
     * aleatoirement un indice de son element */

    return Math.floor(tab.length * Math.random());
};

// Tests Unitaires

var testTirage = function () {

    assert(tirage([0, 1, 2]) < 3 && tirage([0, 1, 2]) >= 0);
    assert(tirage([]) == 0);
    assert(tirage([99]) == 0);

};

var coordH = function (nx, nmr) {

    /* Cette  fonction  prend une  largeur(nx) de la grille
     * et le numero  d'un mur  horizontal (nmr) et calcule
     * ses coordonnees(x, y). Ensuite, la fonction retourne un 
     * tableau (tab = [x,y]) */

    var x, y;                   // Coordonees a calculer
    var tab = [];               // Tableau a retourner

    x = nmr % nx;
    y = Math.floor(nmr / nx);

    tab.push(x);
    tab.push(y);

    return tab;
};

// Tests Unitaires

var testCoordH = function () {

    assert(coordH(8, 19) == "3,2");
    assert(coordH(1, 0) == "0,0");
    assert(coordH(10, 89) == "9,8");

};

var coordV = function (nx, nmr) {

    /* Cette  fonction  prend une  largeur(nx) de la grille
     * et le  numero d'un mur  vertical(nmr) et calcule
     * ses coordonnes(x, y). Ensuite, la fonction retourne un 
     * tableau (tab = [x,y]) */

    var x, y;                   // Coordonees a calculer
    var tab = [];               // Tableau a retourner

    x = nmr % (nx + 1);
    y = Math.floor(nmr / (nx + 1));

    tab.push(x);
    tab.push(y);

    return tab;
};

// Tests Unitaires

var testCoordV = function () {

    assert(coordV(8, 34) == "7,3");
    assert(coordV(1, 0) == "0,0");
    assert(coordV(10, 97) == "9,8");

};



var dir = function (x, y, tx, ty) {
    /* La fonction dir() prends les coordonees d'une cellule(x,y)
       et les coordonnees d'autre cellule(tx,ty) qui est adjacent a
       la premiere. Ensuite elle determine la direction de (tx,ty)
       a (x,y) et retourne une lettre qui represente la direction.
       Si, la cellule de (tx,ty) n'est pas adjacent, elle retourne
       <<indefini>>, une chaine de caracteres */

    if (x == tx && y + 1 == ty) {
        return "N";                      //vers nord
    }
    else if (x + 1 == tx && y == ty)     //vers ouest
    {
        return "O";
    }
    else if (x == tx && y == ty + 1) {
        return "S";                      //vers sud
    }
    else if (x == tx + 1 && y == ty) {
        return "E";                      //vers est
    }
    else {
        return "indefini";               //direction inconnu
    }
};

// Tests Unitaires

var testDir = function () {

    assert(dir(0, 2, 0, 3) == "N");
    assert(dir(1, 0, 0, 0) == "E");
    assert(dir(0, 0, 1, 0) == "O");
    assert(dir(0, 5, 0, 4) == "S");
    assert(dir(0, 0, 1, 1) == "indefini");
    assert(dir(0, 0, 0, 0) == "indefini");
    assert(dir(0, 2, 0, 6) == "indefini");
    assert(dir(5, 0, 9, 0) == "indefini");

};


// 미로 생성하기 (가로크기, 세로크기, 간격)
var laby = function (nx, ny, pas) {

    /* procedure laby() obtient une longueur(nx) et une hauteur(ny) et un pas
     * (pas) pour creer un labyrinthe aleatoire dont son entree se trouve en
     * haut a gauche et sa sortie est en bas a droite.  */



    //---------------1.Variables---------------------------------------------

    var mursH = [];     // represente murs horizontaux
    var mursV = [];     // represente murs verticaux
    var cell;           // represente le numero d'une cellule

    var cave = [];      // Partie de la labyrinthe
    var front = [];     // Partie a cote de la cavite

    var x, y;           // position de la cellule courant.

    var tx, ty;         /* Position de la cellule
                           de cavite precedente */

    var adj = [];       /* tableau gardant le resultat
                           de voisins(), adjacent */

    var N, E, S, O;     /* representent les numeros
                           des murs verticaux ou 
                           horizontaux */


    var direction;      // determine la direction a faire la cavite                                          

    var indefiniCave = [];  /* Si la  direction n'est pas determinee
                           On ajoute ici tous les  cellules qui  sont
                           deja dans la cavite et autour de la cellule
                           courante, pour determiner une direction.*/

    var t;              // garde le resultat de tirage()


    //---------------- 2. Initialisation-----------------------------------

    cs();
    mursH = iota(nx * (ny + 1));        // Commence par une grille en plein
    mursV = iota((nx + 1) * ny);

    cell = Math.floor(Math.random() * nx * ny);         /* Determine le debut de
                                                       la cavite */

    x = coordH(nx, cell)[0];        // Calcule la coordonee x de la cavite
    y = coordH(nx, cell)[1];        // Calcule la coordonee y de la cavite

    front = voisins(x, y, nx, ny);      /* Trouve l'ensemble des cellules 
                                           voisinees */

    ajouter(cave, cell);                /* ajoute la cellule courante 
                                           dans la cavite */

    retirer(mursH, 0);                  // enleve le mur de l'entree
    retirer(mursH, nx * (ny + 1) - 1);  // et de la sortie d'abord.



    //---------------- 3. Creation d'une labyrinthe---------------------------

    for (var i = 0; i < nx * ny && front.length != 0; i++) {

        t = tirage(front);              //Choix d'une cellule dans <<front>>
        ajouter(cave, front[t]);        //Ajouter la cellule dans <<cave>>
        retirer(front, front[t]);       //Retirer la cellule de <<front>>
        cell = cave[cave.length - 1];   /*Passer a la derniere cellule 
                                            dans <<cave>>*/

        x = coordH(nx, cell)[0];        //Recalcule les coordonees x et y
        y = coordH(nx, cell)[1];

        N = x + y * nx;                 //Recalcule les numeros de murs
        E = 1 + x + y * (nx + 1);       //autour de la cellule
        S = x + (y + 1) * nx;
        O = x + y * (nx + 1);

        adj = voisins(x, y, nx, ny);

        ajouterTab(front, adj, cave);   /*fusionner front et adj sauf les
                                          element qui ne sont pas deja dans
                                          <<cave>>*/

        tx = coordH(nx, cave[cave.length - 2])[0]; // Calcule la position
        ty = coordH(nx, cave[cave.length - 2])[1]; // de la cellule prece-
        // dent

        direction = dir(x, y, tx, ty);  // Determine la direction 

        if (direction == "N") {         // Selon la direction, retire le mur
            retirer(mursH, S);          // qui est traverse
        }
        else if (direction == "E") {
            retirer(mursV, O);
        }
        if (direction == "S") {
            retirer(mursH, N);
        }
        if (direction == "O") {
            retirer(mursV, E);
        }
        if (direction == "indefini")    // Si la direction est indeterminee
        {                               // on cherche des cellules qui sont
            // deja dans la cavite

            for (var j = 0; j < adj.length; j++) {
                if (contient(cave, adj[j]) == true) {
                    indefiniCave = [];
                    ajouter(indefiniCave, adj[j]);

                }
            }

            var s = tirage(indefiniCave);    /* Choisir une parmi les cellules
                                            et les considerer comme la cellu-
                                            le precedente */

            tx = coordH(nx, indefiniCave[s])[0];
            ty = coordH(nx, indefiniCave[s])[1];
            direction = dir(x, y, tx, ty);

            if (direction == "N")        // retirer comme cas dans lequel                                            
            {                            // la direction est determinee 
                retirer(mursH, S);
            }
            else if (direction == "E") {
                retirer(mursV, O);
            }
            else if (direction == "S") {
                retirer(mursH, N);
            }
            else if (direction == "O") {
                retirer(mursV, E);
            }
            else if (direction == "indefini") {
                direction = 0;
            }

        };

    };

    dessinmursH(mursH, pas, nx, ny);     /* Affiche les murs selon mursH et
                                            mursV */
    dessinmursV(mursV, pas, nx, ny);

    ht();

};


//#####################Les fonctions pour labySol()##########################

var laby2 = function (nx, ny, pas) {

    /* La fonction laby2() est une fonction qui dessine un labyrinthe comme
     * la procedure laby() mais, elle met mursH et mursV(les tableaux qui con-
     * tiennent les numeros de murs) dans un tableau pour la fonction 
     * labySol()*/

    //---------------1.Variables---------------------------------------------

    var mursH = [];     // represente murs horizontaux
    var mursV = [];     // represente murs verticaux
    var cell;           // represente le numero d'une cellule

    var cave = [];      // Partie de la labyrinthe
    var front = [];     // Partie a cote de la cavite

    var x, y;           // position de la cellule courant.

    var tx, ty;         /* Position de la cellule
                           de cavite precedente */

    var adj = [];       /* tableau gardant le resultat
                           de voisins(), adjacent */

    var N, E, S, O;     /* representent les numeros
                           des murs verticaux ou 
                           horizontaux */


    var direction;      // determine la direction a faire la cavite                                          

    var indefiniCave = [];  /* Si la  direction n'est pas determinee
                           On ajoute ici tous les  cellules qui  sont
                           deja dans la cavite et autour de la cellule
                           courante, pour determiner une direction.*/

    var t;              // garde le resultat de tirage()


    //---------------- 2. Initialisation-----------------------------------

    cs();
    mursH = iota(nx * (ny + 1));        // Commence par une grille en plein
    mursV = iota((nx + 1) * ny);

    cell = Math.floor(Math.random() * nx * ny);         /* Determine le debut de
                                                       la cavite */

    x = coordH(nx, cell)[0];        // Calcule la coordonee x de la cavite
    y = coordH(nx, cell)[1];        // Calcule la coordonee y de la cavite

    front = voisins(x, y, nx, ny);      /* Trouve l'ensemble des cellules 
                                           voisinees */

    ajouter(cave, cell);                /* ajoute la cellule courante 
                                           dans la cavite */

    retirer(mursH, 0);                  // enleve le mur de l'entree
    retirer(mursH, nx * (ny + 1) - 1);  // et de la sortie d'abord.



    //---------------- 3. Creation d'une labyrinthe---------------------------


    for (var i = 0; i < nx * ny && front.length != 0; i++) {

        t = tirage(front);              //Choix d'une cellule dans <<front>>
        ajouter(cave, front[t]);        //Ajouter la cellule dans <<cave>>
        retirer(front, front[t]);       //Retirer la cellule de <<front>>
        cell = cave[cave.length - 1];   /*Passer a la derniere cellule 
                                            dans <<cave>>*/

        x = coordH(nx, cell)[0];        //Recalcule les coordonees x et y
        y = coordH(nx, cell)[1];

        N = x + y * nx;                 //Recalcule les numeros de murs
        E = 1 + x + y * (nx + 1);       //autour de la cellule
        S = x + (y + 1) * nx;
        O = x + y * (nx + 1);

        adj = voisins(x, y, nx, ny);

        ajouterTab(front, adj, cave);   /*fusionner front et adj sauf les
                                          element qui ne sont pas deja dans
                                          <<cave>>*/

        tx = coordH(nx, cave[cave.length - 2])[0]; // Calcule la position
        ty = coordH(nx, cave[cave.length - 2])[1]; // de la cellule prece-
        // dent

        direction = dir(x, y, tx, ty);  // Determine la direction 

        if (direction == "N") {         // Selon la direction, retire le mur
            retirer(mursH, S);          // qui est traverse
        }
        else if (direction == "E") {
            retirer(mursV, O);
        }
        if (direction == "S") {
            retirer(mursH, N);
        }
        if (direction == "O") {
            retirer(mursV, E);
        }
        if (direction == "indefini")    // Si la direction est indeterminee
        {                               // on cherche des cellules qui sont
            // deja dans la cavite

            for (var j = 0; j < adj.length; j++) {
                if (contient(cave, adj[j]) == true) {
                    indefiniCave = [];
                    ajouter(indefiniCave, adj[j]);

                }
            }

            var s = tirage(indefiniCave);    /* Choisir une parmi les cellules
                                            et les considerer comme la cellu-
                                            le precedente */

            tx = coordH(nx, indefiniCave[s])[0];
            ty = coordH(nx, indefiniCave[s])[1];
            direction = dir(x, y, tx, ty);

            if (direction == "N")        // retirer comme cas dans lequel                                            
            {                            // la direction est determinee 
                retirer(mursH, S);
            }
            else if (direction == "E") {
                retirer(mursV, O);
            }
            else if (direction == "S") {
                retirer(mursH, N);
            }
            else if (direction == "O") {
                retirer(mursV, E);
            }
            else if (direction == "indefini") {
                direction = 0;
            }

        };

    };

    dessinmursH(mursH, pas, nx, ny);     /* Affiche les murs selon mursH et
                                            mursV */
    dessinmursV(mursV, pas, nx, ny);

    position(0.5, 0, pas, nx, ny);
    rt(180);

    return ([mursH, mursV]);

};

// Tests unitaires

var testLaby2 = function () {

    assert(laby2(1, 1, 20)[0] == "");
    assert(laby2(1, 1, 20)[1] == "0,1");
    assert(laby2(1, 1, 0)[1] == "0,1");

};



var deplacer = function (x, y, pas, nx, ny, drtn) {

    /* Cette fonction prends cinq entiers, x,y(les coordonnes) et
       nx, ny(largeur et hauteur de la grille), et une caractere
       drtn(direction). ensuite la fonction change la position
       de la tortue d'apres la direction. la fonction retourne
       l'une des coordonnees x ou y selon la direction */

    switch (drtn) {

        case "N":

            y--;
            mv((x - nx / 2) * pas, (-y + ny / 2) * pas);
            return y;

        case "E":
            x++;

            mv((x - nx / 2) * pas, (-y + ny / 2) * pas);
            return x;

        case "S":

            y++;
            mv((x - nx / 2) * pas, (-y + ny / 2) * pas);
            return y;

        case "O":
            x--;
            mv((x - nx / 2) * pas, (-y + ny / 2) * pas);
            return x;


    }


};

//tests unitares

var testDeplacer = function () {

    assert(deplacer(0, 0, 20, 8, 4, "E") == 1);
    assert(deplacer(0, 0, 20, 8, 4, "O") == -1);
    assert(deplacer(0, 0, 20, 8, 4, "N") == -1);

};



var detecterMur = function (x, y, nx, ny, tab1, tab2) {

    /* Cette fonction prend position courante(x,y), et
     * longueur et hauteur(nx et ny) ainsi que les deux
     * tableaux qui enregistrent les numero de murs.
     * Ensuite, la fonction calcule les numeros des murs
     * qui sont adjacents et verifie s'ils sont dans
     * les tableaux. elle est utilisee pour detecter des
     * murs proches
     */

    x = Math.floor(x);
    y = Math.floor(y);

    var N = x + y * nx;
    var E = 1 + x + y * (nx + 1);
    var S = x + (y + 1) * nx;
    var O = x + y * (nx + 1);

    var resultat = [];

    if (contient(tab1, N)) { // Verifier s'il y a un mur
        resultat.push(1);    // vers le nord
    } else {
        resultat.push(0);
    }

    if (contient(tab2, E)) { // Verifier s'il y a un mur
        resultat.push(1);    // vers l'est
    } else {
        resultat.push(0);
    }

    if (contient(tab1, S)) { // Verifier s'il y a un mur
        resultat.push(1);    // vers le sud
    } else {
        resultat.push(0);
    }

    if (contient(tab2, O)) { // Verifier s'il y a un mur
        resultat.push(1);    // vers l'ouest
    } else {
        resultat.push(0);
    }

    return resultat;
};

//tests unitares

var testDetecterMur = function () {

    assert(detecterMur(0, 0, 8, 4, [0, 8], [0, 1]) == "1,1,1,1");
    assert(detecterMur(7, 3, 8, 4, [31, 39], [34, 35]) == "1,1,1,1");
    assert(detecterMur(7, 3, 8, 4, [0, 39], [34, 1]) == "0,0,1,1");

};


var fin = function (x, y, nx, ny) {

    // Cette fonction verifie si la position est egale a le point de fin
    // et retourne true si vrai.

    x = Math.floor(x);
    y = Math.floor(y);

    var N = x + y * nx;                     //Numero de la cellule
    return N == nx * (ny + 1) - 1;
};

var testFin = function () {

    assert(fin(0, 0, 8, 4) == false);
    assert(fin(7, 4, 8, 4) == true);
    assert(fin(8, 3, 8, 4) == false);

};


// 미로 생성 및 왼손 알고리즘으로 미로 풀기
// 파라미터: 가로크기, 세로크기, 간격
var labySol = function (nx, ny, pas) {

    /* Cette procedure dessine un labyrinthe aleatoire,
     * et trouve une chemin vers la sortie. */

    var mur = laby2(nx, ny, pas);           //recevoir une listes des murs
    var mursH = mur[0];                     //murs horizontaux
    var mursV = mur[1];                     //murs verticaux

    ajouter(mursH, 0);                      //permet a la tortue de ne pas
    //sortir

    setpc(1, 0, 0);


    var drtn = "S";                         //la direction (au debut)

    var x = 0.5;                            //les coordonees(au debut)
    var y = -0.5;

    y = deplacer(x, y, pas, nx, ny, drtn);  //bouger la tortue une fois
    //selon sa direction

    var count = 0;                          //Si 0, avancer tout droit
    //si non, tortue s'attache au mur

    var obstaclePosition;                   //l'information des murs 
    //autour de la tortue

    var sortie = 0;                         //Si true, la procedure se termine

    for (var i = 0; !sortie; i++) {

        obstaclePosition = detecterMur(x, y, nx, ny, mursH, mursV);

        if (drtn == "S" && count == 0) {

            switch (obstaclePosition[2]) {   //verifie s'il y a un mur proche
                // dans le sud

                case 0:
                    y = deplacer(x, y, pas, nx, ny, drtn);
                    break;

                case 1:                      //Si bloquee, tourner a gauche
                    lt(90);
                    drtn = "E";
                    count++;
                    break;

            }
        } else if (drtn == "E" && count == 0) {

            switch (obstaclePosition[1]) {   // verifie s'il y a un mur proche
                // dans l'Est
                case 0:
                    x = deplacer(x, y, pas, nx, ny, drtn);
                    break;

                case 1:                      //Si bloquee, tourner a gauche
                    lt(90);
                    drtn = "N";
                    count++;
                    break;
            }
        } else if (drtn == "N" && count == 0) {

            switch (obstaclePosition[0]) {   // verifie s'il y a un mur proche
                // dans le nord
                case 0:
                    y = deplacer(x, y, pas, nx, ny, drtn);
                    break;

                case 1:                      //Si bloquee, tourner a gauche
                    lt(90);
                    drtn = "O";
                    count++;
                    break;
            }
        } else if (drtn == "O" && count == 0) {

            switch (obstaclePosition[3]) {    // verifie s'il y a un mur proche
                // dans l'ouest
                case 0:
                    x = deplacer(x, y, pas, nx, ny, drtn);
                    break;

                case 1:                       //Si bloquee, tourner a gauche
                    lt(90);
                    drtn = "S";
                    count++;
                    break;
            }
        } else if (drtn == "E" && count != 0) { // vers l'Est, 
            // en touchant le mur

            switch (obstaclePosition[2]) {      // S'il n'y a pas de
                // mur dans le sud

                case 0:                         // tourner a droite
                    rt(90);                     // et avancer vers le sud
                    drtn = "S";
                    y = deplacer(x, y, pas, nx, ny, drtn);
                    count--;
                    break;

                case 1:                         //S'il y a un mur dans
                    //le sud

                    switch (obstaclePosition[1]) {   //le mur dans l'est

                        case 0:                      //avancer tout droit

                            x = deplacer(x, y, pas, nx, ny, drtn);
                            break;

                        case 1:                      //Si oui, tourne a gauche
                            lt(90);
                            drtn = "N";
                            count++;
                            break;
                    }

            }
        } else if (drtn == "S" && count != 0) { // vers le sud en touchant 
            // le mur

            switch (obstaclePosition[3]) {      // verifie l'ouest
                case 0:                         // tourne a droit
                    rt(90);
                    drtn = "O";
                    x = deplacer(x, y, pas, nx, ny, drtn);
                    count--;
                    break;

                case 1:                         // il y a un mur dans l'ouest

                    switch (obstaclePosition[2]) {     // verifie le sud

                        case 0:                        // avancer vers le sud

                            y = deplacer(x, y, pas, nx, ny, drtn);
                            break;

                        case 1:                        // tourner a gauche
                            lt(90);
                            drtn = "E";
                            count++;
                            break;
                    }
            }

        } else if (drtn == "O" && count != 0) {      // vers l'ouest, attachee

            switch (obstaclePosition[0]) {           // verifie le nord            
                case 0:
                    rt(90);
                    drtn = "N";
                    y = deplacer(x, y, pas, nx, ny, drtn);
                    count--;
                    break;

                case 1:                                // le nord est bloque

                    switch (obstaclePosition[3]) {     // verifie l'ouest

                        case 0:                        // avancer ver l'ouest

                            x = deplacer(x, y, pas, nx, ny, drtn);
                            break;

                        case 1:                        // si bloque, tourner
                            lt(90);                    // a gauche
                            drtn = "S";
                            count++;
                            break;
                    }
            }

        } else if (drtn == "N" && count != 0) {        // le nord, attache

            switch (obstaclePosition[1]) {             // verifie l'Est
                case 0:

                    rt(90);
                    drtn = "E";
                    x = deplacer(x, y, pas, nx, ny, drtn);
                    count--;
                    break;

                case 1:
                    switch (obstaclePosition[0]) {     // verifie le nord
                        case 0:
                            y = deplacer(x, y, pas, nx, ny, drtn);
                            break;

                        case 1:
                            lt(90);
                            drtn = "O";
                            count++;
                            break;

                    }

            }


        }

        sortie = fin(x, y, nx, ny);          // signifie si la tortue
    }                                        // est arrivee au point final

};


//##############Execution des tests unitaires#############################

testIota();
testContient();
testAjouter();
testRetirer();
testAjouterTab();
testVoisins();
testTirage();
testCoordH();
testCoordV();
testDir();
testVoisins();
testLaby2();
testDeplacer();
testDetecterMur();
testFin();
cs();