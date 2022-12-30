//Auteurs : Thi Hang Vo, Christian Lungescu, Changmin Lee

var deleteColumn;

// 초기 테이블 생성
function init() {
    classSelectOption("anneeSel");
    var annee = $("#anneeSel").val();
    poste(requeteSQL("E"), "tableA");
    poste(requeteSQL("W"), "tableB");
    if (annee >= 1995) {
        poste(requeteSQL("C"), "tableC");
    } else {
        $("#tableC").html("");
        $("#table3Title").html("");
    }
};

// 특정 연도를 선택했을 시 체크 박스 변경
function classSelectOption(id) {
    var resultat = "";
    for (var y = 2004; y >= 1969; y--) {
        resultat += balise("option", "year_" + id, y);
    }
    document.getElementById(id).innerHTML = resultat;
};

// 태그 생성
function balise(tag, id, contenu) {
    var resultat = "<" + tag + " id=\"" + id + "\">"
        + contenu
        + "</" + tag + ">\n";
    return resultat;
};

// SQL request 전송 및 response 수신
function requeteSQL(div) {

    var annee = $("#anneeSel").val();
    var ligueSelected = $("#ligueSel option:selected").val();
    var ligue;

    if (annee < 1995 && div == "C") return 0;

    switch (ligueSelected) {
        case "NL": ligue = "(lgID=\'NL\')"; break;
        case "AL": ligue = "((lgID=\'NL\') OR (lgID=\'AL\'))";
    }

    var line1_attach = "";
    var table_C1 = "";
    var lineF_attach = "";


    if ($("#champs2ligue").prop("checked") && !$("#champSM").prop("checked")) {
        switch (ligueSelected) {
            case "NL": {
                line1_attach = ", \
                    (REPLACE(NLCS,1,\'C\'))\
                    NLCS";
                table_C1 = "\
                   ,(SELECT teamID, teamID=LC.teamIDWinner AS NLCS\
                    FROM Teams,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='NLCS') AS LC\
                    WHERE yearID="+ annee + "\
                          AND "+ ligue + " AND divID=\'" + div + "\') as C1";
                lineF_attach = " AND (C1.teamID=S1.teamID)";
                deleteColumn = 1;
                break;
            }
            case "AL": {
                line1_attach = ", (REPLACE(NLCS,1,\'C\')) NLCS,\
                                  (REPLACE(ALCS,1,\'AC\'))\ ALCS";
                table_C1 = "\
                    ,(SELECT teamID, teamID=ALC.teamIDWinner AS ALCS,\
                            teamID=LC.teamIDWinner AS NLCS\
                    FROM Teams,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='NLCS') AS LC,\
                        (SELECT teamIDWinner\
                            FROM SeriesPost\
                            WHERE yearID="+ annee + " AND round='ALCS') AS ALC\
                    WHERE yearID="+ annee + "\
                        AND "+ ligue + " AND divID=\'" + div + "\') as C1";
                lineF_attach = " AND (C1.teamID=S1.teamID)";
                deleteColumn = 2;
            }
        }
    }
    if ($("#champSM").prop("checked") && !$("#champs2ligue").prop("checked")) {
        switch (ligueSelected) {
            case "NL": {
                line1_attach = ",(REPLACE(WS,1,\'W\'))\ WS";
                table_C1 = "\
                    ,(SELECT teamID, teamID=WSC.teamIDWinner AS WS\
                    FROM Teams,\
                        (SELECT teamIDWinner\
                            FROM SeriesPost\
                            WHERE yearID="+ annee + " AND round='WS') AS WSC\
                    WHERE yearID="+ annee + "\
                        AND "+ ligue + " AND divID=\'" + div + "\') as C1";
                lineF_attach = " AND (C1.teamID=S1.teamID)";
                deleteColumn = 1;
                break;
            }
            case "AL": {
                line1_attach = ",(REPLACE(WS,1,\'W\'))\ WS";
                table_C1 = "\
                    ,(SELECT teamID, teamID=WSC.teamIDWinner AS WS\
                    FROM Teams,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='WS') AS WSC\
                    WHERE yearID="+ annee + "\
                        AND "+ ligue + " AND divID=\'" + div + "\') as C1";
                lineF_attach = " AND (C1.teamID=S1.teamID)";
                deleteColumn = 1;

            }
        }
    }

    if ($("#champSM").prop("checked") && $("#champs2ligue").prop("checked")) {
        switch (ligueSelected) {
            case "NL": {
                line1_attach = ",(REPLACE(NLCS,1,\'C\')) NLCS, \
                (REPLACE(WS,1,\'W\')) WS";
                table_C1 = "\
                    ,(SELECT teamID, teamID=WSC.teamIDWinner AS WS,\
                            teamID=LC.teamIDWinner AS NLCS\
                    FROM Teams,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='NLCS') AS LC,\
                        (SELECT teamIDWinner\
                            FROM SeriesPost\
                            WHERE yearID="+ annee + " AND round='WS') AS WSC\
                    WHERE yearID="+ annee + "\
                        AND "+ ligue + " AND divID=\'" + div + "\') as C1";
                lineF_attach = " AND (C1.teamID=S1.teamID)";
                deleteColumn = 2;
                break;
            }
            case "AL": {
                line1_attach = ",(REPLACE(NLCS,1,\'C\')) NLCS,\
                (REPLACE(ALCS,1,\'AC\')) ALCS,(REPLACE(WS,1,\'W\')) WS";
                table_C1 = "\
                    ,(SELECT teamID, teamID=ALC.teamIDWinner AS ALCS,\
                                teamID=WSC.teamIDWinner AS WS,\
                            teamID=LC.teamIDWinner AS NLCS\
                    FROM Teams,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='NLCS') AS LC,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='WS') AS WSC,\
                        (SELECT teamIDWinner\
                        FROM SeriesPost\
                        WHERE yearID="+ annee + " AND round='ALCS') AS ALC\
                    WHERE yearID="+ annee + "\
                        AND "+ ligue + " AND divID=\'" + div + "\') as C1";
                lineF_attach = " AND (C1.teamID=S1.teamID)";
                deleteColumn = 3;

            }
        }
    }

    if (!$("#champs2ligue").prop("checked") && !$("#champSM").prop("checked")) {
        deleteColumn = 0;
    }

    var request = "name AS Equipe, S1.W/S1.G AS Moyenne,\
                   S1.W,S1.L, tb.maxW-S1.W AS \"Diff.\" "+ line1_attach + "\
                   FROM Teams AS S1 INNER JOIN\
                        (SELECT MAX(W) AS maxW\
                         FROM Teams\
                         WHERE "+ ligue + " AND (yearID=" + annee + ")) AS tb\
                         "+ table_C1 + "\
                   WHERE "+ ligue + " AND (yearID=" + annee + ")\
                   AND (divID=\""+ div + "\")" + lineF_attach + "\
                   ORDER BY S1.W DESC;";

    $("#table1Title").text("Division Est (" + annee + ")");
    $("#table2Title").text("Division Ouest (" + annee + ")");

    if (annee >= 1995) {
        $("#table3Title").text("Division Centrale (" + annee + ")");
    }

    return request;
};

// 표 생성하기
function genereTableau(donnees, id) {
    var indNational = "";
    var indAmericain = "";
    var indMondial = "";

    var nb = donnees.length;
    if (nb > 0) {
        var nbattributs = donnees[0].length;
        var htmltable = "<tr>";
        for (var attr in donnees[0]) {

            htmltable = htmltable + "<th>" + attr + "</th>";
        };
        htmltable = htmltable + "</tr>";
        for (var x = 0; x < nb; x++) {
            htmltable = htmltable + "<tr id=\"" + id + "_" + x + "\">";
            for (var a in donnees[x]) {
                if (donnees[x][a] == "C") {
                    indNational = "#" + id + "_" + x;
                }
                if (donnees[x][a] == "AC") {
                    indAmericain = "#" + id + "_" + x;
                }
                if (donnees[x][a] == "W") {
                    indMondial = "#" + id + "_" + x;
                }
                htmltable = htmltable + "<td>" + donnees[x][a] + "</td>";
            }
            htmltable = htmltable + "</tr>";
        }
        $("#" + id).html(htmltable);

        $(indNational).css("background", "yellow");
        $(indAmericain).css("background", "lime");
        $(indMondial).css("border", "5px solid purple");

        for (var i = 0; i < deleteColumn; i++) {
            $("#" + id + " th:last-child," + " #" + id + " td:last-child").remove();
        }



    } else {
        alert("Le résultat retourné est vide.");
        $("#" + id).html("");
    }
};


// SQL 문 전송 및 JSON 수신
function poste(requete, table) {
    var postData = {};
    postData["db"] = "dift6800_baseball";
    postData["query"] = requete;

    $.post(
        "http://www-ens.iro.umontreal.ca/~dift6800/baseball/db.php",
        postData,
        function (reponse, status) {


            var obj = JSON.parse(reponse);

            if (obj.error == "") {
                genereTableau(obj.data, table);
            } else {
                alert("Erreur:" + obj.error);
            }
        }
    );
};

// lance 버튼 클릭시 수행할 동작 - SQL전송, 표 생성 및 결과 표시
$(document).ready(function () {

    $("#lance").click(function (event) {
        var annee = $("#anneeSel").val();
        poste(requeteSQL("E"), "tableA");
        poste(requeteSQL("W"), "tableB");
        if (annee >= 1995) {
            poste(requeteSQL("C"), "tableC");
        } else {
            $("#tableC").html("");
            $("#table3Title").html("");
        }
    });
});

// 1994 년의 경우 예외발생 처리
function blocker1994() {
    var annee = $("#anneeSel").val();
    if (annee == 1994) {

        $("#champs2ligue").prop("checked", false);
        $("#champSM").prop("checked", false);

        $("#champs2ligue").prop("disabled", true);
        $("#champSM").prop("disabled", true);


    } else {

        $("#champs2ligue").prop("disabled", false);
        $("#champSM").prop("disabled", false);
        $("#champs2ligue").prop("checked", true);
        $("#champSM").prop("checked", true);

    }

};