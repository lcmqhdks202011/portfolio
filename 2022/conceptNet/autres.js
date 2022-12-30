var decompte;
function startTimer(secondes, ouAfficher, fonctionTerminer) {

	document.getElementById(ouAfficher).innerHTML = secondes;
	
	var millisecondes = secondes*1000;
	var tempsRestant = millisecondes;

	decompte = setInterval(function() {

		if(tempsRestant > 0) {
			tempsRestant -= 1000;
			document.getElementById(ouAfficher).innerHTML = (tempsRestant/1000);

		} else {
			document.getElementById(ouAfficher).innerHTML = 0;
			stopTimer();
			fonctionTerminer();
		}
	}, 1000);
}
function stopTimer() { clearInterval(decompte); }


var compte;
function startCountdown(secondes, ouAfficher, fonctionTerminer) {
	
	var tempsRestant = secondes*1000;
	var indicesDonnees = 0;
	var time = 0;

	compte = setInterval(function() {

		if(tempsRestant > 0) {
			tempsRestant -= 1000;
			if(time % 20 == 0 && indicesDonnees++ < 5) giveHint(time, indicesDonnees);
			time = secondes - (tempsRestant/1000)
			document.getElementById(ouAfficher).innerHTML = time;



		} else {
			stopCountdown();
			fonctionTerminer();
		}
	}, 1000);
}
function stopCountdown() { clearInterval(compte); }



document.getElementById("barreConsulter").addEventListener("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		document.getElementById("btnConsulter").click();
	}
});

document.getElementById("consigne_text").addEventListener("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		document.getElementById("btn_consigne_add").click();
	}
});

document.getElementById("text_qsj_answer").addEventListener("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		document.getElementById("btn_qsj_answer").click();
	}
});