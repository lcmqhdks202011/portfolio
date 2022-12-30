//Auteurs : Thi Hang Vo, Christian Lungescu, Changmin Lee


var requete;


var init = function () {
  var annee = $("#annee").val();
  poste(requeteJouer(), "table1", "Joueurs(" + annee + ")");
  poste(requeteLance(), "table2", "Lanceurs(" + annee + ")");
  poste(requeteAutre(), "table3", "Autres Informations(" + annee +
    ")");
}

// 투수 외 선수의 통계를 나타내는 SQL 문 생성
var requeteJouer = function () {
  var annee = $("#annee").val();
  var ba, sl, ob, so, sbp, ab, h, b2, b3, hr, bb, r, sb, cs;
  var fp, pos, a, e, sal;

  var ordre = ($("#desc1").prop("checked")) ? " DESC" : " ASC";


  var tri = ($("#tri_BA").prop("checked")) ? " BA" :
    ($("#tri_SL").prop("checked")) ? " SL" :
      ($("#tri_OB").prop("checked")) ? " OB" :
        ($("#tri_FP").prop("checked")) ? " FP" :
          ($("#tri_HR").prop("checked")) ? " HR" :
            ($("#tri_salaire").prop("checked")) ? " Salaire" : " Nom";

  if (($("#tri_BA").prop("checked"))) {
    $("#BA").prop("checked", true);
  }

  if (($("#tri_SL").prop("checked"))) {
    $("#sL").prop("checked", true);
  }

  if (($("#tri_OB").prop("checked"))) {
    $("#OB").prop("checked", true);
  }

  if (($("#tri_FP").prop("checked"))) {
    $("#FP").prop("checked", true);
  }

  if (($("#tri_HR").prop("checked"))) {
    $("#HR").prop("checked", true);
  }

  if (($("#tri_salaire").prop("checked"))) {
    $("#salaire").prop("checked", true);
  }


  // 체크된 항목에 따라 SQL 문 변경
  ba = $("#BA").prop("checked") ? ", H/AB AS 'BA'" : "";
  sl = $("#sL").prop("checked") ? ", (2*2B + 3*3B + 4*HR)/AB AS SL" : "";
  ob = $("#OB").prop("checked") ? ", (H+BB+HBP)/(AB+BB+HBP+SF) AS 'OB'" : "";
  so = $("#sO").prop("checked") ? ", SO/AB AS 'SO'" : "";
  sbp = $("#sBp").prop("checked") ? ", B0.SB/(B0.CS+B0.SB) AS 'SB'" : "";
  ab = $("#AB").prop("checked") ? ", AB" : "";
  h = $("#H").prop("checked") ? ", H" : "";
  b2 = $("#2B").prop("checked") ? ", 2B" : "";
  b3 = $("#3B").prop("checked") ? ", 3B" : "";
  hr = $("#HR").prop("checked") ? ", HR" : "";
  bb = $("#BB").prop("checked") ? ", BB" : "";
  r = $("#R").prop("checked") ? ", R" : "";
  sb = $("#sB").prop("checked") ? ", SB" : "";
  cs = $("#CS").prop("checked") ? ", CS" : "";
  fp = $("#FP").prop("checked") ? ", F4.Eff as 'FP'" : "";
  pos = $("#POS").prop("checked") ? ", F4.POSX AS POS" : "";
  a = $("#A").prop("checked") ? ", F4.A" : "";
  e = $("#E").prop("checked") ? ", F4.E" : "";
  sal = $("#salaire").prop("checked") ? ", salary AS Salaire" : "";
  sal = (annee >= 1969 && annee < 1985) ? "" : sal
  var tableSal = (sal != "") ? "Salaries," : "";
  var resultat = "CONCAT(nameFirst,\" \",nameLast) AS Nom" +
    ba + sl + ob + so + sbp + ab + h + b2 + b3 + hr + bb + r + sb + cs +
    fp + pos + a + e + sal +
    "\nFROM Master AS M0," + tableSal + " Batting AS B0, " +
    " (SELECT F3.playerID, SUM(A)/(SUM(A)+SUM(E)) AS Eff," +
    "  SUM(A) AS A, SUM(E) AS E, POSX " +
    "    FROM Fielding AS F3, " +
    "        ((SELECT playerID AS PID, MAX(GS) AS MaxGS" +
    "        FROM Fielding as F1" +
    "        WHERE (teamID=\"MON\") AND (yearID=" + annee + ")" +
    "              AND (POS <> \"P\") AND (POS <> \"OF\")" +
    "        GROUP BY playerID) AS T1 INNER JOIN" +
    "          (SELECT playerID, POS AS POSX, GS " +
    "           FROM Fielding AS F2" +
    "           WHERE (F2.teamID=\"MON\") AND" +
    "                 (F2.yearID=" + annee + ") AND" +
    "                 (F2.POS <> \"P\")" +
    "                 AND (F2.POS <> \"OF\")) AS T2" +
    "                 ON (T1.PID=T2.playerID)" +
    "                     AND (T1.MaxGS = GS))" +
    "   WHERE (T1.PID=F3.playerID) AND (F3.yearID=" + annee + ")" +
    "   GROUP BY F3.playerID) as F4 " +
    "WHERE (AB>10) AND (M0.playerID=B0.playerID) AND " +
    "   (B0.yearID=" + annee + ") AND (B0.teamID='MON') AND" +
    "   (M0.playerID=F4.playerID)";

  if (sal != "") {
    resultat += "  AND (M0.playerID=Salaries.playerID)" +
      "  AND (Salaries.yearID=" + annee + ")";
  }

  resultat += " ORDER BY " + tri + " " + ordre;
  resultat += ";";
  return resultat;
};

// 투수 통계를 조회하는 SQL 문 생성
var requeteLance = function () {
  var annee = $("#annee").val();
  var era, bao, par, g, gs, cg, w, l, sv, ipo, so, h, bb2, sa;

  era = $("#ERA").prop("checked") ? ", ERA" : "";
  bao = $("#BAO").prop("checked") ? ", BAOpp" : "";
  par = $("#Par").prop("checked") ? ", (GS>0) AS Par," +
    "(G>0 AND GS=0) AS RE" : "";
  g = $("#G").prop("checked") ? ", G" : "";
  gs = $("#GS").prop("checked") ? ", GS" : "";
  cg = $("#CG").prop("checked") ? ", CG" : "";
  w = $("#W").prop("checked") ? ", W" : "";
  l = $("#L").prop("checked") ? ", L" : "";
  sv = $("#SV").prop("checked") ? ", SV" : "";
  ipo = $("#IPo").prop("checked") ? ", IPouts" : "";
  so = $("#SO").prop("checked") ? ", SO" : "";
  h = $("#H2").prop("checked") ? ", H" : "";
  bb2 = $("#BB2").prop("checked") ? ", BB" : "";
  sa = $("#Sa").prop("checked") ? ", salary AS Salaire" : "";



  var tri = ($("#tri_ERA").prop("checked")) ? " ERA" :
    ($("#tri_BAOpp").prop("checked")) ? " BAOpp" :
      ($("#tri_G").prop("checked")) ? " G" :
        ($("#tri_GS").prop("checked")) ? " GS" :
          ($("#tri_CG").prop("checked")) ? " CG" :
            ($("#tri_W").prop("checked")) ? " W" :
              ($("#tri_SO").prop("checked")) ? " SO" :
                ($("#tri_H").prop("checked")) ? " H" :
                  ($("#tri_BB").prop("checked")) ? " BB" :
                    ($("#tri_SA").prop("checked")) ? " Salaire" : " Nom";




  if (($("#tri_ERA").prop("checked"))) {
    $("#ERA").prop("checked", true);
  }
  if (($("#tri_BAOpp").prop("checked"))) {
    $("#BAO").prop("checked", true);
  }
  if (($("#tri_G").prop("checked"))) {
    $("#G").prop("checked", true);
  }
  if (($("#tri_GS").prop("checked"))) {
    $("#GS").prop("checked", true);
  }
  if (($("#tri_CG").prop("checked"))) {
    $("#CG").prop("checked", true);
  }
  if (($("#tri_W").prop("checked"))) {
    $("#W").prop("checked", true);
  }
  if (($("#tri_SO").prop("checked"))) {
    $("#SO").prop("checked", true);
  }
  if (($("#tri_H").prop("checked"))) {
    $("#H2").prop("checked", true);
  }
  if (($("#tri_BB").prop("checked"))) {
    $("#BB2").prop("checked", true);
  }
  if (($("#tri_SA").prop("checked"))) {
    $("#Sa").prop("checked", true);
  }

  if (annee >= 1969 && annee < 1985) {
    sa = "";
    tri = ($("#tri_SA").prop("checked")) ? " Nom" : " Nom";
  }

  var tableSal = (sa != "") ? ", Salaries AS S0" : "";

  var ordre = ($("#desc1").prop("checked")) ? " DESC" : " ASC";


  var resultat = "CONCAT(nameFirst,\"  \",nameLast) AS Nom" +
    era + bao + g + gs + cg + w + l + sv + ipo + so + h + bb2 + sa + par +
    " FROM Master AS M0, Pitching AS P0" + tableSal +
    " WHERE M0.playerID=P0.playerID AND P0.teamID='MON'" +
    " AND P0.yearID=" + annee;

  if (sa != "") {
    resultat += " AND P0.playerID=S0.playerID " +
      "AND P0.yearID=S0.yearID AND P0.teamID=S0.teamID";
  }
  resultat += " ORDER BY " + tri + " " + ordre;

  resultat += ";";


  return resultat;
};

// 그외 인물들에 대한 통계 작성
var requeteAutre = function () {
  var annee = $("#annee").val();
  var ms = "";
  var sousReqSal = "";
  if (!(annee >= 1969 && annee < 1985)) {
    ms = ", MS AS 'Masse Salariale'";
    sousReqSal = ", (SELECT SUM(salary) AS MS FROM Salaries AS S1 " +
      "WHERE S1.yearID=" + annee + " AND S1.teamID='MON') AS MA "
  }
  var resultat = "CONCAT(nameFirst,\" \",nameLast) AS Gerant" +
    ", attendance AS 'Assistance à domicile'" + ms +
    " FROM Master AS M0, Managers AS MN0, Teams AS T0" +
    sousReqSal +
    " WHERE MN0.yearID=" + annee + " AND MN0.teamID='MON' AND " +
    "M0.playerID=MN0.playerID AND T0.yearID=" + annee +
    " AND T0.teamID='MON'";
  return resultat;
};

// 표 만들기
function genereTableau(donnees, id, title) {
  var test2 = [];
  var deleteColumn;
  var defensive = 0;
  var total = 0;
  var i = 0;

  var nb = donnees.length;
  if (nb > 0) {
    var htmltable = caption(title);
    var nbattributs = donnees[0].length;
    htmltable += "<tr>";
    for (var attr in donnees[0]) {
      i++;
      total++;

      if (id == "table1") {
        switch (attr) {
          case 'FP':
          case 'POS':
          case 'A':
          case 'E':
          case 'Salaire':
            defensive++;
        }
        switch (attr) {
          case 'BA':
          case 'SL':
          case 'OB':
          case 'FP':
          case 'HR':
            attr += "%";
        }
      }


      htmltable = htmltable + "<th id=\"" + id + "-" + i + "\">" + attr +
        "</th>";
    };
    htmltable = htmltable + "</tr>";
    for (var x = 0; x < nb; x++) {
      htmltable = htmltable + "<tr id=\"" + id + "_" + x + "\">";
      for (var a in donnees[x]) {

        if (donnees[x][a] == null) {

          donnees[x][a] = "S/O";
        }

        htmltable = htmltable + "<td id=\"" + id + "_" + x + "_" + a + "\">" +
          donnees[x][a] + "</td>";

        if (a == "Par" && donnees[x][a] == 1) {
          deleteColumn = 2;
          test2.push("#" + id + "_" + x + "_" + "Nom");
          partant();
        }
      }
      htmltable = htmltable + "</tr>";
    }

    $("#" + id).html(htmltable);


    for (var i = 0; i < test2.length; i++) {
      var test = $(test2[i]).text();
      $(test2[i]).html(test + "*");
    }


    for (var i = 0; i < deleteColumn; i++) {
      $("#table2  th:last-child," + " #table2 td:last-child").remove();
    }


    for (var i = total; i > total - defensive && id == 'table1'; i--) {
      // console.log(i);
      $("#table1  th:nth-child(" + i + ")")
        .css("background", "darkgreen");
      $("#table1  td:nth-child(" + i + ")")
        .css("background", "palegreen");
    }




  } else {
    alert("Le résultat retourné est vide.");
    $("#" + id).html("");
  }
};

// SQL 문 전송 및 응답 수신 후 표 생성
function poste(requete, table, title) {
  var postData = {};
  postData["db"] = "dift6800_baseball";
  postData["query"] = requete;

  $.post(
    "http://www-ens.iro.umontreal.ca/~dift6800/baseball/db.php",
    postData,
    function (reponse, status) {


      var obj = JSON.parse(reponse);
      //console.log(obj);
      //console.log(obj.data);
      if (obj.error == "") {
        genereTableau(obj.data, table, title);
      } else {
        alert("Erreur:" + obj.error);
      }
    }
  );
};


// 버튼 클릭시 표 생성 및 정보 출력
$(document).ready(function () {
  $("#bouton").click(function (event) {
    var annee = $("#annee").val();
    poste(requeteJouer(), "table1", "Joueurs(" + annee + ")");
    poste(requeteLance(), "table2", "Lanceurs(" + annee + ")");
    poste(requeteAutre(), "table3", "Autres Informations(" + annee +
      ")");
    //console.log("textarea:"+$("#req").val());

  });
});

var caption = function (title) {
  return "<caption style=\"font-size: xx-large;\">" + title + "</caption>"
};

var partant = function () {
  $("#legPartant").html("( Remarque *: Lanceur Partant)")
};

// 1969-1985 사이에 제공되지 않는 정보를 비활성화
var disableSalaire = function () {
  var annee = $("#annee").val();
  if (annee >= 1969 && annee < 1985) {

    $("#salaire").prop("checked", false);
    $("#tri_salaire").prop("checked", false);
    $("#Sa").prop("checked", false);
    $("#tri_SA").prop("checked", false);
    $("#salaire").prop("disabled", true);
    $("#tri_salaire").prop("disabled", true);
    $("#Sa").prop("disabled", true);
    $("#tri_SA").prop("disabled", true);
    $("#tri_BA").prop("checked", true);
    $("#tri_ERA").prop("checked", true);

  } else {

    $("#salaire").prop("disabled", false);
    $("#tri_salaire").prop("disabled", false);
    $("#Sa").prop("disabled", false);
    $("#tri_SA").prop("disabled", false);
    $("#salaire").prop("checked", true);
    $("#Sa").prop("checked", true);

  }

};
