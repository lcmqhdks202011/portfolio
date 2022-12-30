/* Nom du fichier : tp2.js

   Auteur : Changmin Lee

   Description :
    Ce programme realise un jeu de carte "Poker Shuffle" dans une page de HTML
    dans une naviguateur. Les joueurs doivent combiner les cartes dans les ra-
    ngées horizontales et verticales pour obtenir autant de points que 
    possible. Dès que vous placez la 25e carte, le jeu se termine et redémar-
    re.

    이 프로그램은 HTML 페이지에서 포커 셔플 카드 게임을 구현합니다. 플레이어는 가로
    세로로 카드값을 합쳐 최고 점수를 만들어야 합니다. 
    25번째 카드를 위치시키면 게임이 끝나고 재시작합니다.

*/





//------------------------0. Variables Globales-------------------------------

var dernierId; // Utilisee dans clic() pour echanger deux positions de cartes
var etat;      // Utilisee dans clic() pour determiner l'etape d'action.
var cartes;    // Utilisee dans init() pour enregistrer une pile de cartes
var position;  /* Utilisee dans sous-fonctions de clic() pour enregistrer les
                  positions de chaque carte placee 
                  0-51 : a partir de A a K, en ordre de S,H,D,C */

var restant;   // Pour compter de 25 jusqu'a 0, et terminer.




//------------------------1. Initialisation-----------------------------------


/* Procedure init() initialise le jeu, fait une pile de 52 cartes et
   realise une table de jeu avec jeuControle(); Bouton et une pile des cartes,
   et tableJeu(); Une table a placer des cartes et les scores.
   Au debut, les cartes sur la table sont vides. position[], positions de 
   cartes est remplies par -1. Ici, -1 est une place vide, -2 est le dos d'une
   carte. les positions 0 ~ 24 sont vide(dans la grille), et on ajoute -2 dans
   25. C'est une position ou le jeu de cartes se trouve. */

//초기화
var init = function () {
  etat = 0;
  cartes = pileAleatoire(52);
  restant = 25;

  document.getElementById("b").innerHTML = jeuControle() + tableJeu();

  position = Array(25).fill(-1);

  position.push(-2);

  document.getElementById("B5").innerHTML = 0;
};

/* tableJeu() est une fonction qui construit une table de jeu avec <table>
   de html. pour 5X5 grille, on met l'image "empty.svg" dans chaque cellule.
   a la droite et en bas, on met les tableaux des scores. Finalement, la
   fonction retourne un texte(code html) */

// 테이블 생성
var tableJeu = function () {
  var resultat = "<table><tr>";
  var i = 0;

  for (var k = 0; k < 5; k++) {

    for (var j = 0; j < 5; j++) {

      resultat += "<td id=\"" + i + "\" onclick=\"clic(" + i + ");\">"
        + carteId2Image(-1)
        + "</td>";

      i++;

    }

    resultat += "<td id=\"D" + k + "\"></td>"         // Tableau de bord à droite
      + "</tr>";

  }

  resultat += "<tr>";

  for (k = 0; k <= 5; k++) {                           // Tableau de bord en bas

    resultat += "<td id=\"B" + k + "\"></td>";

  }

  resultat += "</tr></table>";

  return resultat;
};


/* jeuControle() est une fonction qui construit une table qui se trouve a cote
   de la table de jeu. On met un bouton qui va reinitialiser le jeu et une
   image "back.svg" ou la pile des cartes se place. Finalement, la fonction 
   retourne un texte(code html). */
// 게임 테이블 생성 및 버튼 생성
var jeuControle = function () {
  return "\
  <table>\
    <tr>\
      <td><button onclick = \"init()\">Nouvelle Partie</button></td>\
      <td></td>\
      <td id=\"25\" onclick=\"clic(25)\">"+ carteId2Image(-2) + "</td>\
      <td></td>\
    </tr>\
  </table>";

};


/* Fonction iota prend un entier n positif et crée un tableau de nombre 
   contenant les nombres entiers en ordre de 0 a n-1.*/

var iota = function (n) {

  var table = [];

  for (var i = 0; i < n; i++) {
    table.push(i);
  }

  return table;
};


/* Fonction contient() prend un tableau de nombre et un nombre entier n 
 * et retourne un boolèen indiquant si ce dernier est contenu dans 
 * le tableau. Si oui, elle retourne true, sinon, false.*/

// contains 혹은 has 함수
var contient = function (tab, n) {

  for (var i = 0; i < tab.length; i++) {
    if (tab[i] == n) {
      return true;
    }
  }
  return false;
};


/* Fonction ajouter() prend un tableau de nombre et un nombre entier n
 * pour retourner le meme tableau a part si n n'y est pas contenu,
 * il sera alors ajouter a la fin. */

// 중복되지 않도록 배열에 값 추가
var ajouter = function (tab, n) {

  if (!contient(tab, n)) {      // nombre redondant?(booleen)
    tab.push(n);
    return tab;
  }

  return tab;
};


/* La fonction retirer() prend un tableau de nombre et un nombre
   * entier n, et verifie si ce nombre est contenu dans le tableau.
   * Si oui, la fonction retirera ce dernier du tableau.*/
// 어떤 값을 배열에서 제거
var retirer = function (tab, n) {


  var temp = [];
  for (var i = tab.length - 1; i >= 0; i--) {

    if (tab[i] != n) {
      temp.push(tab[i]);
      tab.pop();
    } else {
      tab.pop();
      break;
    }
  }

  for (var j = temp.length - 1; j >= 0; j--) {

    tab.push(temp[j]);

  }
  return tab;
};


/* Fonction pileAleatoire prend un entier. fait un tableau ou des nombres 
   se trouvent en ordre aleatoire. un tableau sera retourne */
// 카드섞기
var pileAleatoire = function (entier) {

  var tab1 = Array(entier).fill(0).map(function (x, i) {
    return i;
  });

  var tab2 = [];
  var temp;

  for (var i = 0; i < entier; i++) {

    temp = Math.floor(Math.random() * (tab1.length));

    ajouter(tab2, tab1[temp]);
    retirer(tab1, tab1[temp]);
  }
  return tab2;
};



//------------------------2. Deroulement------------------------------------


/* procedure clic() prend un id(position dans la grille) gere 
   les evenements qui se produisent lorsque vous cliquez chaque cellule.
   etat == 0 signifie que rien n'est selectione. S'il y a -2 ("back.svg")
   dans la position[25](la position de la pile de cartes), on appelle la fon-
   ction sortir(), qui va sortir la carte derniere. Si on avait deja sorti
   la carte, on change la couleur de cette carte sans sortir d'autre carte.
   et on change etat de 0 a 1. Si on clique une carte dans la table, s'il
   y avait rien selectionne(etat : 0), on selectionne cette carte et passe a
   un autre etat(2)
    */

// 모든 클릭에 대한 액션 설정
var clic = function (id) {
  if (id == 25 && (etat == 0)) {
    if (position[id] == -2) {

      sortir(cartes);

    } else { couleur(id, "lime"); }

    etat = 1;

  } else if (id != 25 && etat == 0 && position[id] != -1) {

    couleur(id, "lime");
    etat = 2;

  } else if (id != 25 && etat == 1) {      // etat == 1 signifie que la  
    // carte dans position 25 est
    // prete a se placer.

    if (position[id] == -1) {                // Si dans une cellule est -1,
      // (-1 : "empty")
      placer(id);                           // On apelle placer();
      etat = 0;

    } else {                                // Si la place n'est pas vide,
      // la carte ne sera pas placee et
      couleur(25, "transparent");            // on selectionne celle qui etait
      couleur(id, "lime");                  // sur la meme place.
      etat = 2;

    }
  } else if (id != 25 && etat == 2 && dernierId != 25) {

    // Si etat est 2, la carte sur la
    // table sera selectionnee.

    if (id != dernierId) {                   // Si on clique une autre (qui 
      // n'est pas dans position 25),
      echanger(dernierId, id);               // ses positions sont echangees.

    } else if (id == dernierId) {

      couleur(id, "transparent");           // Si on clique la meme, on la
    }                                       // deselectionne. 
    etat = 0;

  } else if (id == 25 && etat == 2) {
    // Dans le cas ou etat est 2, si
    couleur(dernierId, "transparent");       // on clique position 25 (Jeu de
    // cartes), on deselectionne
    // la carte derniere.

    if (position[id] == -2) {                // Si la carte suivante n'est pas
      // encore sortie, on appelle sort-
      sortir(cartes);                       // ir(). 

    } else { couleur(id, "lime"); }         // Sinon, on selectionne la carte
    // sans sortir une autre.
    etat = 1;

  } else if (id == 25 && etat == 1) {       // Lorsque la carte dans le jeu
    // de cartes est selectionnee,
    couleur(id, "transparent");              // une clique va la deselectionner
    etat = 0;
  }

  dernierId = id;                           // on enregistre la position cli-
  // quee pour etat 2.

};


/* couleur est une procedure qui change la couleur de fond
   d'un element de HTML. la procedure prend id d'une place(id)
    et un text(couleur) */
// 카드가 선택되었음을 나타내기 (초록색으로 표시)
var couleur = function (id, couleur) {
  document.getElementById(id).style.backgroundColor = couleur;
};


/* procedure sortir() lit le dernier element(nombre) dans un tableau(cartes),
   et va charger un fichier image selons le nombre dans une place(25).
   Apres, dans position[], qui a la position et le type de carte,
   on enregistre le numero de la carte sortie. */

// 셔플된 카드 덱에서 카드 뽑기
var sortir = function (cartes) {

  document.getElementById(25).innerHTML
    = carteId2Image(cartes[cartes.length - 1]);

  couleur(25, "lime");

  position[25] = cartes[cartes.length - 1];
  cartes.pop();

};


/* echanger() est une procedure pour que deux cartes soient echangees.
   D'abord, on prend deux IDs, une place qui vient d'etre cliquee, 
   et sa precedente. Apres, les contenus des deux elements seront echangees.
   et puis, les couleurs des 2 places seront transparentes. les positions
   seront echangees aussi dans le tableau position[]. affichageScore calculera
   le score pour les lignes et les colonnes, pour chaque position echangee.*/

// 카드의 위치를 서로 맞바꾸는 기능
var echanger = function (id1, id2) {
  var temp;

  temp = document.getElementById(id2).innerHTML;

  document.getElementById(id2).innerHTML
    = document.getElementById(id1).innerHTML;

  document.getElementById(id1).innerHTML = temp;

  couleur(id1, "transparent");
  couleur(id2, "transparent");

  temp = position[id2];
  position[id2] = position[id1];
  position[id1] = temp;

  affichageScore(position, id1);
  affichageScore(position, id2);


};

/* placer() prend une position(id) pour placer la carte qui est
  sur position 25 :Jeu de cartes. Si la position cliquee n'est
  pas vide, d'apres position[], rien n'arrivera. on transfere
  l'image a une place dans la table, et l'image du jeu de cartes
  devient "back.svg" par carte2Image. couleur sera appelee
  pour deselectionner position 25 visuellement. Dans le niveau de
  position[], le numero de la carte deplacee sera enregistre. Et
  pour la position 25, -2 qui represente "back.svg" sera place.
  Si restant est 0, on termine le jeu. */

// 카드를 테이블 위에 놓는 기능
var placer = function (id) {
  if (position[id] == -1) {

    document.getElementById(id).innerHTML
      = document.getElementById(25).innerHTML;

    document.getElementById(25).innerHTML = carteId2Image(-2);

    couleur(25, "transparent");

    position[id] = position[25];
    position[25] = -2;

    affichageScore(position, id);
    restant--;
    if (restant == 0) {
      terminer();
    }
  }
};


/* carteId2Type est une fonction qui prend carteId,
   dans position[]. on calcule modulo 4 pour determiner
   le type du cartes. (S: pique, H: coeur, D:carreau, C:trefle).
   et retourne le texte. */

// 카드 ID로부터 하트/스페이드/클로버/다이아몬드 알아내기
var carteId2Type = function (carteId) {
  switch (carteId % 4) {

    case 0: return "S";

    case 1: return "H";

    case 2: return "D";

    case 3: return "C";
  }
};


/* carteId2Valeur est une fonction qui prend carteId,
et on la divise par 4 pour determiner le numero de carte
(de AS a Roi) et on retourne la valeur de carte.*/

// 카드 ID를 카드의 문자와 대응시키기
var carteId2Valeur = function (carteId) {
  var numero = carteId >> 2;

  switch (numero) {

    case 0: return "A";

    case 10: return "J";

    case 11: return "Q";

    case 12: return "K";

    default: return ++numero + "";
  }
};


/* carteId2NomDeFichier est une fonction qui prend carteId
   et en convertit en nom de fichier (sans extension).
   pour "back" et "empty", certains traitements intermediaires seront omis. */

// 카드 ID로 부터 카드의 경로를 구하기
var carteId2NomDeFichier = function (carteId) {

  switch (carteId) {
    case -2: return "back";

    case -1: return "empty";

    default: return carteId2Valeur(carteId) + carteId2Type(carteId);
  }
};


/* carteId2Image prend un carteId pour construire un code de HTML <img> pour
   afficher une image de carte dans le naviguateur. on retourne un texte. */

// 카드 ID에 해당하는 경로에서 HTML img에 불러오기 
var carteId2Image = function (carteId) {

  return "<img src=\"cards/" + carteId2NomDeFichier(carteId) + ".svg\">";

};




//-------------------3. Calcul de points obtenus -----------------------------

/* matrice prend un tableau (position) pour faire 5X5 2D matrice.
   la position 25 (26e de tableau) sera exclue. on retourne cette
   matrice. */
// 2D 행렬 생성하기
var matrice = function (tab) {

  var j = 0;
  var resultat = [];
  var temp;

  while (j < tab.length - 1) {

    temp = [];

    for (var i = 0; i < 5; i++) {

      temp.push(tab[j++]);

    }

    resultat.push(temp);

  }
  return resultat;
};


/* La fonction colonne() prend une matrice 2D(mat) et un entier(colNum: Numero
 * de colonne) et Pour une colonne selectionnee par colNum, on ajoute chaque
 * donnee dans un tableau(resultat) a l'ordre de ligne de la matrice et retou-
 * rne le tableau(resultat).
 */

// 2D 행렬에서 열을 배열로 반환
var colonne = function (mat, colNum) {

  return Array(mat.length).fill(0).map(function (x, i) {

    return mat[i][colNum];

  });
};


/* La fonction transpose() prend une matrice 2D(matrice caree/rectangulaire)
* et appelle la fonction colonne() pour toutes ses colonnes et les ajoute
* dans un tableau() a l'ordre de colonnes. et puis, transpose() retourne sa
* matrice transposee.
*/

// 전치행렬 구하기
var transpose = function (matrice) {

  var resultat = [];

  matrice[0].forEach(function (x, i) { resultat.push(colonne(matrice, i)); });

  return resultat;
};


/* trierMemeNombre prend un tableau(un tableau de position en 5x5 matrice)
   et si un element n'est pas -1 ("empty"), la fonction convertit les nombres
   dans le tableau en valeur de carte.(AS a Roi). Grace a ajouter(), S'il y a
   le meme nombre dans resultat, on en ajoute pas a nouveau. un tableau
   resultat[] sera retoune */

var trierMemeNombre = function (tab) {
  var resultat = [];

  for (var i = 0; i < tab.length; i++) {

    if (tab[i] != -1) ajouter(resultat, tab[i] >> 2);

    if (tab[i] == -1) resultat.push(-1);
  }

  return resultat;
};


/* compterMemeNombre() prend un tableau(un tableau dans position en 5X5 
   matrice) on divise chaque element par 4 et enregistre dans une variable
   (valeurs) chaque valeur de carte. et on appelle trierMemeNombre()
   pour le meme tableau (tab) pour compter le nombre de chaque valeur. 
   le nombre de -1 sera pas considere comme la meme valeur(pour empecher
   pointage des places vides). on met chaque compteur dans le tableau res-
   ultat et on le retourne.  */

var compterMemeNombre = function (tab) {

  var resultat = [];

  var valeurs = tab.map(function (x) { return x >> 2; });
  var nombresTries = trierMemeNombre(tab);

  for (var i = 0; i < nombresTries.length; i++) {

    compteur = 0;

    for (var j = 0; j < valeurs.length; j++) {

      if (valeurs[j] == nombresTries[i] && valeurs[j] != -1) {

        compteur++;
      }

      if (valeurs[j] == nombresTries[i] && valeurs[j] == -1) {

        compteur++;

        break;
      }
    }
    resultat.push(compteur);
  }
  return resultat;
};


/* compterMemeType() est une fonction qui prend un tableau(un tableau 
   dans position en 5X5 matrice). on calcule chaque element avec & 3 et
   on compare le premier element avec les autres. on retourne true
   quand tous les elements sont egaux. sinon, false. */

//같은 타입인지 여부 구하기
var compterMemeType = function (tab) {

  for (var i = 1; i < tab.length; i++) {

    if (((tab[0] & 3) != (tab[i] & 3) || (tab[i] == -1))) {

      return false;

    }
  }
  return true;
};


/* tri() est une fonction qui prend un tableau et trie les elements
   par ordre croissant. */

// 정렬
var tri = function (tab) {

  var temp = 0;
  var i = 0;
  var j;

  for (; i < tab.length - 1; i++) {

    for (j = i + 1; j < tab.length; j++) {

      if (tab[i] > tab[j]) {

        temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;

      }
    }
  }
  return tab;

};


/* shift2() prend un tableau, divise chaque element par 4,
   et retourne un tableau ou les elements divises se trouvent. */

var shift2 = function (tab) {

  var resultat = tab.map(function (x) { return x >> 2; });

  return resultat;
};


/* pointage() est une fonction qui prend un tableau (un tableau dans
   position en 5X5 matrice). une variable valeurs est un tableau qui contient
   les valeurs des cartes d'une main. variable compteurs determine le nombre
   de carte pour chaque valeur. les variables: unePaire, doublePaire, brelan
   fullHouse, carre, sequenceSpeciale sont les tableaux a comparer avec une 
   main courante. une condition compterMemeType(tab) veut dire que les types 
   des 5 cartes sont egaux. sequence(valeurs) est satisfait lorsque les cartes
   se suivent, sauf le cas de 10,J,Q,K,AS. Si l'une des conditions est 
   remplie, le score correspondant lui est retourné. on retourne "" pour
   ne rien afficher au cas ou il n'y a pas de point. */

// 특수한 카드 조합에 대해서 점수 계산하기
var pointage = function (tab) {

  var valeurs = tri(shift2(tab));

  var compteurs = tri(compterMemeNombre(tab));

  var unePaire = [1, 1, 1, 2];       // 4 types, 2 cartes de meme valeur

  var doublePaire = [1, 2, 2];         /* 3 types, 2 cartes de meme valeur
                                            Autres 2 cartes de meme valeur */


  var brelan = [1, 1, 3];         // 3 types, 3 cartes de meme valeur

  var fullHouse = [2, 3];           // 2 types, 2 cartes et 3 cartes

  var carre = [1, 4];           // 2 types, 4 cartes de meme valeur

  var sequenceSpeciale = [0, 9, 10, 11, 12]; // 9-K Sequence avec AS et Roi 

  if (comparer2Tableaux(compteurs, unePaire)) return 2;

  if (comparer2Tableaux(compteurs, doublePaire)) return 5;

  if (comparer2Tableaux(compteurs, brelan)) return 10;

  if (comparer2Tableaux(compteurs, fullHouse)) return 25;

  if (comparer2Tableaux(compteurs, carre)) return 50;

  if (compterMemeType(tab) &&
    comparer2Tableaux(valeurs, sequenceSpeciale)) return 100;

  if (compterMemeType(tab) &&
    sequence(valeurs)) return 75;

  if (compterMemeType(tab)) return 20;

  if (sequence(valeurs) ||
    comparer2Tableaux(valeurs, sequenceSpeciale)) return 15;

  return "";
};


/* sequence() est une fonction qui prend un tableau deja trie
   verifie si les valeurs d'un tableau se suivent.
   Si chaque élément n'augmente pas de 1, false sera retourne. */
// 배열이 1씩 증가하는 수열을 나타내는지 확인
var sequence = function (tab) {

  for (var i = 1; i < tab.length; i++) {
    if (tab[0] + i != tab[i]) return false;
  }

  return true;
};


/* La fonction comparer2Tableaux() prend 2 tableaux(tab,tab2)
 * et verifie s'ils ont les memes elements en ordre. Si oui,
 * la fonction retourne true, sinon, false.
 */
// 배열 2개의 내용이 같은지 비교하기
var comparer2Tableaux = function (tab, tab2) {
  var resultat = true;
  if (tab.length != tab2.length) {
    return false;
  } else {
    tab.forEach(function (x, i) {
      if (tab[i] !== tab2[i]) {
        resultat = false;
      }
    });
  }
  return resultat;
};


/* scoreCalc() est une fonction qui prend un tableau (idealement position)
  pour faire sa version en matrice(temp) et en matrice transposee(temp2)
  Alors, on va obtenir des mains horizontalement et verticalement.
  Ensuite, on calcule les points pour chaque main qui constitue les 2 
  matrices. Apres les calculs, on obtient 2 tableaux, l'un des points cal-
  cules horizontalement et l'autre des ceux calcules verticalement.
  on met les 2 tableaux dans un tableau et on retourne. */
// 총점 계산하기
var scoreCalc = function (tab) {
  var temp = matrice(tab);
  var temp2 = transpose(temp);
  var resultat = [];

  resultat.push(Array(temp.length).fill(0)
    .map(function (x, i) { return pointage(temp[i]); }));

  resultat.push(Array(temp.length).fill(0)
    .map(function (x, i) { return pointage(temp2[i]); }));

  return resultat;
};


/* somme() est une fonction qui obtient une matrice (resultat de scoreCalc())
   calcule la somme des tous les points calcules pour chaque ligne et chaque 
   colonne, et retourne le score total ( entier) */

var somme = function (mat) {
  var resultat = 0;

  for (var i = 0; i < mat.length; i++) {

    for (var j = 0; j < mat[0].length; j++) {

      resultat += +mat[i][j];

    }
  }
  return resultat;
};

/* affichageScore() est une fonction qui prend un tableau (cardIds[])
   et id (id d'une place cliquee dans la grille en appelant placer()
   ou echanger() ), pour afficher les scores pour la ligne et la colonne 
   dans laquelle la place cliquee se trouve. le premier branche if est
   pour les lignes, le dernier est pour les colonnes. Enfin,
   on affiche le point total a "B5" (a droite, en bas) */

var affichageScore = function (tab, id) {
  var tab1 = scoreCalc(tab);
  if (0 <= id && id < 5) {

    document.getElementById("D0").innerHTML = tab1[0][0];

  } else if (5 <= id && id < 10) {

    document.getElementById("D1").innerHTML = tab1[0][1];

  } else if (10 <= id && id < 15) {

    document.getElementById("D2").innerHTML = tab1[0][2];

  } else if (15 <= id && id < 20) {

    document.getElementById("D3").innerHTML = tab1[0][3];

  } else if (20 <= id && id < 25) {

    document.getElementById("D4").innerHTML = tab1[0][4];
  }


  if (id % 5 == 0) {

    document.getElementById("B0").innerHTML = tab1[1][0];

  } else if (id % 5 == 1) {

    document.getElementById("B1").innerHTML = tab1[1][1];

  } else if (id % 5 == 2) {

    document.getElementById("B2").innerHTML = tab1[1][2];

  } else if (id % 5 == 3) {

    document.getElementById("B3").innerHTML = tab1[1][3];

  } else if (id % 5 == 4) {

    document.getElementById("B4").innerHTML = tab1[1][4];

  }

  document.getElementById("B5").innerHTML = somme(tab1);
};



// --------------------4. Termination du jeu-----------------------------

/* terminer() est une procedure qui est activee quand une variable
   globale "restant" atteint 0, lors d'utilisation de placer().
   pointFinal, le texte a "B5" sera affiche par alert() et le jeu
   sera automatiquement reinitialise. */

//게임 종료하기
var terminer = function () {
  var pointFinal = document.getElementById("B5").innerHTML;
  setTimeout(() => {
    alert("Votre pointage final est " + pointFinal);
    init();
  }, 200);
};




//--------------------5. Tests Unitaires---------------------------------

// testPointage est une fonction pour tester la fonction pointage()
// 각 함수마다 유닛 테스트 수행하기
var testPointage = function () {

  // Quint Flush Royale Test
  console.assert((pointage([0, 48, 44, 40, 36]) == 100), "QuiFluRoy1");
  console.assert((pointage([1, 49, 45, 41, 37]) == 100), "QuiFluRoy2");
  console.assert((pointage([2, 50, 46, 42, 38]) == 100), "QuiFluRoy3");
  console.assert((pointage([3, 51, 47, 43, 39]) == 100), "QuiFluRoy4");

  // Quinte Flush Test
  console.assert((pointage([32, 48, 44, 40, 36]) == 75), "QuinteFlush1");
  console.assert((pointage([33, 49, 45, 41, 37]) == 75), "QuinteFlush2");
  console.assert((pointage([34, 50, 46, 42, 38]) == 75), "QuinteFlush3");
  console.assert((pointage([35, 51, 47, 43, 39]) == 75), "QuinteFlush4");

  // Quinte Test
  console.assert((pointage([0, 49, 45, 42, 38]) == 15), "Quinte1");
  console.assert((pointage([0, 50, 46, 41, 37]) == 15), "Quinte2");
  console.assert((pointage([34, 31, 25, 20, 19]) == 15), "Quinte3");
  console.assert((pointage([14, 16, 22, 24, 29]) == 15), "Quinte4");


  // Flush Test
  console.assert((pointage([16, 24, 32, 40, 8]) == 20), "Flush1");
  console.assert((pointage([17, 25, 33, 41, 9]) == 20), "Flush2");
  console.assert((pointage([18, 26, 34, 42, 10]) == 20), "Flush3");
  console.assert((pointage([19, 27, 35, 43, 11]) == 20), "Flush4");

  // Carre Test
  console.assert((pointage([0, 1, 2, 3, 4]) == 50), "Carre1");
  console.assert((pointage([15, 16, 17, 18, 19]) == 50), "Carre2");
  console.assert((pointage([20, 21, 22, 23, 24]) == 50), "Carre3");
  console.assert((pointage([35, 36, 37, 38, 39]) == 50), "Carre4");

  // Full House Test
  console.assert((pointage([5, 6, 7, 8, 9]) == 25), "FullHouse1");
  console.assert((pointage([10, 11, 12, 13, 14]) == 25), "FullHouse2");
  console.assert((pointage([25, 26, 27, 28, 29]) == 25), "FullHouse3");
  console.assert((pointage([30, 31, 32, 33, 34]) == 25), "FullHouse4");
  console.assert((pointage([47, 46, 45, 2, 3]) == 25), "FullHouse5");

  // Brelan Test

  console.assert((pointage([47, 46, 45, 2, 9]) == 10), "Brelan1");
  console.assert((pointage([21, 43, 41, 42, 8]) == 10), "Brelan2");
  console.assert((pointage([13, 12, 15, 35, 50]) == 10), "Brelan3");
  console.assert((pointage([28, 30, 8, 44, 29]) == 10), "Brelan4");


  // Double Paire Test
  console.assert((pointage([49, 48, 47, 6, 5]) == 5), "Double Paire1");
  console.assert((pointage([47, 46, 25, 2, 3]) == 5), "Double Paire2");
  console.assert((pointage([28, 10, 8, 44, 29]) == 5), "Double Paire3");
  console.assert((pointage([28, -1, 3, 1, 29]) == 5), "Double Paire4");

  // Paire Test
  console.assert((pointage([48, 47, 46, 3, 4]) == 2), "Paire1");
  console.assert((pointage([25, 45, 32, 2, 47]) == 2), "Paire2");
  console.assert((pointage([30, 31, 39, 49, 13]) == 2), "Paire3");
  console.assert((pointage([28, 10, 24, 44, 29]) == 2), "Paire4");

  // "" Test
  console.assert((pointage([28, 25, 5, 37, 11]) == ""), "Zero1");
  console.assert((pointage([29, 23, 26, 14, 36]) == ""), "Zero2");
  console.assert((pointage([2, 23, 37, 17, 30]) == ""), "Zero3");
  console.assert((pointage([8, 43, 13, 35, 38]) == ""), "Zero4");



  // -1 : "empty", -2 : "back"
  console.assert((pointage([51, 50, -1, -1, -1]) == 2), "paire + 3 empty");
  console.assert((pointage([-1, -1, -1, -1, 4]) == ""), "!Carre");
  console.assert((pointage([-1, -1, -1, 3, 4]) == ""), "!Brelan");
  console.assert((pointage([-1, -1, 2, 9, 4]) == ""), "!Paire");
  console.assert((pointage([-1, -1, -1, -2, -2]) == ""), "!FH");
  console.assert((pointage([-1, -1, -1, -1, -1]) == ""), "!flush");

};

testPointage(); // Execution



// testCarteId2Valeur() est un test unitaire pour carteId2Valeur()

var testCarteId2Valeur = function () {

  console.assert((carteId2Valeur(51) == "K"), "Roi");
  console.assert((carteId2Valeur(47) == "Q"), "Dame");
  console.assert((carteId2Valeur(42) == "J"), "Valet");
  console.assert((carteId2Valeur(39) == 10), "10");
  console.assert((carteId2Valeur(3) == "A"), "AS");
  console.assert((carteId2Valeur(4) == 2), "2");
  console.assert((carteId2Valeur(9) == 3), "3");
  console.assert((carteId2Valeur(13) == 4), "4");
  console.assert((carteId2Valeur(19) == 5), "5");
  console.assert((carteId2Valeur(23) == 6), "6");
  console.assert((carteId2Valeur(25) == 7), "7");
  console.assert((carteId2Valeur(31) == 8), "8");
  console.assert((carteId2Valeur(35) == 9), "9");

};

testCarteId2Valeur();       // Execution



// testCarteId2Type() est un test unitaire pour carteId2Type()

var testCarteId2Type = function () {

  console.assert((carteId2Type(51) == "C"), "trefle");
  console.assert((carteId2Type(47) == "C"), "trefle2");
  console.assert((carteId2Type(42) == "D"), "Carreau1");
  console.assert((carteId2Type(39) == "C"), "trefle3");
  console.assert((carteId2Type(3) == "C"), "trefle4");
  console.assert((carteId2Type(4) == "S"), "pique");

};

testCarteId2Type();      // Execution

// testCarteId2NomDefichier() est un test unitaire pour carteId2NomDefichier()

testCarteId2NomDeFichier = function () {

  console.assert((carteId2NomDeFichier(51) == "KC"), "KC");
  console.assert((carteId2NomDeFichier(47) == "QC"), "QC");
  console.assert((carteId2NomDeFichier(42) == "JD"), "JD");
  console.assert((carteId2NomDeFichier(39) == "10C"), "10C");
  console.assert((carteId2NomDeFichier(3) == "AC"), "AC");
  console.assert((carteId2NomDeFichier(4) == "2S"), "2S");
  console.assert((carteId2NomDeFichier(-1) == "empty"), "vide");
  console.assert((carteId2NomDeFichier(-2) == "back"), "dos");

};

testCarteId2NomDeFichier();      // Execution

// testCarteId2Image() est un test unitaire pour carteId2Image

testCarteId2Image = function () {

  console.assert((carteId2Image(51) == "<img src=\"cards/KC.svg\">"), "51i");
  console.assert((carteId2Image(47) == "<img src=\"cards/QC.svg\">"), "47i");
  console.assert((carteId2Image(42) == "<img src=\"cards/JD.svg\">"), "42i");
  console.assert((carteId2Image(39) == "<img src=\"cards/10C.svg\">"), "39");
  console.assert((carteId2Image(3) == "<img src=\"cards/AC.svg\">"), "3i");
  console.assert((carteId2Image(4) == "<img src=\"cards/2S.svg\">"), "4i");

};

testCarteId2Image();      // Execution
