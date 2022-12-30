document.getElementById("btnConsigne").onclick = consignegogo;
document.getElementById("btn_consigne_add").onclick = consigne_add;

var add_count = 0;
var solution = [];
var consigneIsRunning = false;

function consignegogo(){
	stopTimer();

	$("#div_consigne_answer_part").show();

	consigneIsRunning = true;
	document.getElementById("consigne_answers").innerHTML = "";
	document.getElementById("resultatConsigne").innerHTML = "";
	document.getElementById("consigne_text").value = "";
	add_count = 0;
	var magicnumber = Math.floor(Math.random() * faits.length);
	var sujet;
	var rel = "&rel=/r/" + faits[magicnumber].relation;
	var rn = Math.floor(Math.random() * 2);
	var req = "https://api.conceptnet.io/query?";
	var solutions = [];
	var objet;

	if(rn==0){

		sujet = faits[magicnumber].start;
		objet = faits[magicnumber].end;
		var url = req + "start=" + sujet + rel + "&end=" + objet.substring(0, objet.lastIndexOf("/"));
		console.log(url);
		$.getJSON(url).done(function(e){
			var term = e.edges[0].start.term;
			var rel = e.edges[0].rel.label;
			var solutions = [];
			for(var i = 0; i < e.edges.length; i++){
				solutions.push(e.edges[i].end.term);
			}
			console.log(solutions);
			consigne_start(term, rel, solutions, "start");
		});
	} else {

		sujet = faits[magicnumber].end;
		objet = faits[magicnumber].start;
		var url = req + "end=" + sujet + rel + "&start=" + objet.substring(0, objet.lastIndexOf("/"));
		console.log(url);
		$.getJSON(url).done(function(e){
			var term = e.edges[0].end.term;
			var rel = e.edges[0].rel.label;
			var solutions = [];
			for(var i = 0; i < e.edges.length; i++){
				solutions.push(e.edges[i].start.term);
			}
			console.log(solutions);
			consigne_start(term, rel, solutions, "end");
		});
	}

}



function consigne_start(term, rel, solutions, pos){
	stopTimer();
	var toShow;
	processingSolution(solutions);
	var t = term.substring(term.lastIndexOf("/") + 1).replace(/_/g," ");

	if (pos=="start"){

		toShow = t + " <b>" + rel + "</b> ???.";

	} else {

		toShow = "???" + " <b>" + rel + "</b> " + t;

	}

	document.getElementById("div_consigne_enonce").innerHTML = toShow;

	solution = solutions;

	startTimer(20, "div_consigne_timer", consigneCheckAnswer);
}



function processingSolution(sol){
	for (var i = 0; i<sol.length; i++){
		sol[i] = sol[i].substring(sol[i].lastIndexOf("/") + 1).replace(/_/g," ");
	}
}


function consigne_add(){
	if(!consigneIsRunning) return;
	var ans = document.getElementById("consigne_text").value;
	var table = document.getElementById("consigne_answers");
	var row = '<p class="consigne_answer_list" id="answer_row_' + add_count++ + '">' + ans + '</p>'
	table.innerHTML += row;
	document.getElementById("consigne_text").value = "";
}



function consigneCheckAnswer(){
	var answers = document.getElementsByClassName("consigne_answer_list");
	var score = 0;
	var correct = false;

	for(var j = 0; j < answers.length; j++){
		for(var i = 0; i < solution.length; i++){
			if (answers[j].innerHTML == solution[i]) {
				score++;
				correct = true;
				$("#answer_row_" + j).wrap("<b>");
			}
		}

		if(!correct){
			$("#answer_row_" + j).wrap("<strike>");
		} 

		correct = false;
	}

	console.log(score);

	document.getElementById("consigne_answers").innerHTML += 
	"Votre score : " + score + ".";

	document.getElementById("resultatConsigne").innerHTML = "Réponses : \n " +
	("" + solution).replaceAll( "," , ", ");

	consigneIsRunning = false;
	$("#div_consigne_answer_part").hide();
}