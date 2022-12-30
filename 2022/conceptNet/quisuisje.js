document.getElementById("btn_qsj_lancer").onclick = quisuisje;
document.getElementById("btn_qsj_answer").onclick = checkAnswer;

var givenHints;
var arr;
var concept;
var isFinished;
var isRunning = false;


function quisuisje(){
	if(!isFinished) stopCountdown(); 
	isFinished = false;
	document.getElementById("qsj_2").innerHTML = "";
	document.getElementById("text_qsj_answer").value = "";
	var table = '<table id="qsj_3" class="table table-striped"></table>';
	document.getElementById("qsj_2").innerHTML = table;
	concept = findEligibleConcept();
	var arr1 = processingStart(searchByStart(concept));
	var arr2 = processingEnd(searchByEnd(concept));
	arr = arr1.concat(arr2);

	$("#qsj_answer").show();

	startCountdown(120, "qsj_body", qsjLost);
}


function giveHint(time, nbr){
	var count = nbr-1;

	var id_row = "qsj_row_" + count;
	var id_col0 = id_row + "_ind";
	var id_col1 = id_row + "_start";
	var id_col2 = id_row + "_relation";
	var id_col3 = id_row + "_end";

	var text2 = '<tr id="' + id_row + '">' 
	+ '<td id="' + id_col0 + '"></td>'
	+ '<td id="' + id_col1 + '"></td>'
	+	'<td id="' + id_col2 + '"></td>'
	+	'<td id="' + id_col3 + '"></td>'
	+ '</tr>';

	givenHints = count;

	document.getElementById("qsj_3").innerHTML += text2;
	document.getElementById(id_col0).innerHTML = time;
	document.getElementById(id_col1).innerHTML = arr[count].start.substring(arr[count].start.lastIndexOf("/") + 1).replace(/_/g," ");
	document.getElementById(id_col2).innerHTML = arr[count].relation;
	document.getElementById(id_col3).innerHTML = arr[count].end.substring(arr[count].end.lastIndexOf("/") + 1).replace(/_/g," ");
}


function searchByStart(start){
	var res = [];
	for(var i=0; i<faits.length ; i++){
		if(faits[i].start == start)
			res.push(faits[i]);
	}
	return res;
}


function searchByEnd(end){
	var res = [];
	for(var i=0; i<faits.length ; i++){
		if(faits[i].end == end)
			res.push(faits[i]);

	}
	return res;
}


function processingStart(arr){
	res = [];
	for (var i=0; i<arr.length;i++){
		res.push( {start: "???", 
			relation: arr[i].relation, 
			end: arr[i].end});
	}
	return res;
}


function processingEnd(arr){
	res = [];
	for (var i=0; i<arr.length;i++){
		res.push( {start: arr[i].start, 
			relation: arr[i].relation, 
			end: "???"});
	}
	return res;
}


function findEligibleConcept(){
	var total = faits.length;
	while(true){
		var magicNumber = Math.floor(Math.random() * total);
		var concept = faits[magicNumber].start;
		var arr1 = searchByStart(concept);
		var arr2 = searchByEnd(concept);
		if (arr1.length + arr2.length >= 5)
			return concept;
		else {
			concept = faits[magicNumber].end;
			arr1 = searchByStart(concept);
			arr2 = searchByEnd(concept);
			if (arr1.length + arr2.length >= 5)
				return concept;
		}
	}
}


function qsjSuccess(minus){
	document.getElementById("qsj_2").innerHTML +=  "Votre score : " + (8-minus) + ".";
	isFinished = true;
	stopCountdown();
}


function qsjLost(){
	document.getElementById("qsj_2").innerHTML += "Temps écoulé, vous avez perdu.";
	isFinished = true;
	stopCountdown();
}


function checkAnswer(){
	if(isFinished == true) return;
	var ans = document.getElementById("text_qsj_answer").value;
	var model = concept.substring(concept.lastIndexOf("/")+1).replace(/_/g," ");
	if(model == ans){
		qsjSuccess(givenHints);
	} 
	else qsjWrong();
}


function qsjWrong(){
	document.getElementById("qsj_2").innerHTML += "Mauvaise réponse, vous avez perdu."
	isFinished = true;
	stopCountdown();
}