var questionOuiNon;

document.getElementById("btnLancerOuiNon").onclick = launchOuiNon;
document.getElementById("btnOui").onclick = function() {
	validerReponse(1, questionOuiNon[1]); stopTimer();
	$('#btnNon').css('pointer-events','none')
};
document.getElementById("btnNon").onclick = function() {
	validerReponse(0, questionOuiNon[1]); stopTimer();
	$('#btnOui').css('pointer-events','none');
};


function launchOuiNon() {
	stopTimer();
	questionOuiNon = creerQuestionOuiNon(faits);

	console.log(questionOuiNon);

	$("#unePartieOuiNon").show();
	$('#btnOui').css('pointer-events',''); $('#btnNon').css('pointer-events','');

	document.getElementById("resultatOuiNon").innerHTML = "";
	document.getElementById("laQuestionOuiNon").innerHTML = questionOuiNon[0];

	startTimer(5, "div_ouinon_timer", ouiNonTempsEcoule);
}


function validerReponse(r, c) {
	if(r == c) {
		document.getElementById("resultatOuiNon").innerHTML = "Bravo ! Bonne réponse !";
	} else {
		document.getElementById("resultatOuiNon").innerHTML = "Dommage, mauvaise réponse...";
	}
}


function ouiNonTempsEcoule() {
	$('#btnOui').css('pointer-events','none'); $('#btnNon').css('pointer-events','none');
	document.getElementById("resultatOuiNon").innerHTML = "Temps écoulé, vous avez perdu.";
}


function creerQuestionOuiNon(faits) {
	var numeroAleatoire = Math.floor(Math.random() * faits.length);
	var numeroAleatoire2 = Math.floor(Math.random() * faits.length);
	while(numeroAleatoire == numeroAleatoire2) {
		numeroAleatoire2 = Math.floor(Math.random() * faits.length);
	}

	var faitChoisi = faits[numeroAleatoire];

	var reponseEst = Math.random() < 0.5; //aléatoire entre true ou false

	var startChoisi = faitChoisi.start.substring(faitChoisi.end.lastIndexOf("/") + 1).replace(/_/g," ");
	var endChoisi = faitChoisi.end.substring(faitChoisi.end.lastIndexOf("/") + 1).replace(/_/g," ");

	if(reponseEst) {
		return [startChoisi + " <b>" + faitChoisi.relation + "</b> " + endChoisi + " ?", 1];
	} else {
		var faitChoisi2 = faits[numeroAleatoire2];
		var endChoisi2 = faitChoisi2.end.substring(faitChoisi2.end.lastIndexOf("/") + 1).replace(/_/g," ");

		return [startChoisi + " <b>" + faitChoisi.relation + "</b> " + endChoisi2 + " ?", 0];
	}
}