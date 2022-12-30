document.getElementById("btnConsulter").onclick = consulter;

var prevPage = "", nextPage = "";
document.getElementById("prevBtn").onclick = function() { getDataFrom(prevPage); }
document.getElementById("nextBtn").onclick = function() { getDataFrom(nextPage); }


function consulter() {
	var aConsulter = document.getElementById("barreConsulter").value;

	var url = "";

	if(aConsulter.indexOf("/c/") > -1 && aConsulter.indexOf("&") == -1) {
		url = "https://api.conceptnet.io/query?node=" + aConsulter;
		getDataFrom(url);

	} else if(aConsulter.indexOf("/r/") > -1 && aConsulter.indexOf("&") == -1) {
		url = "https://api.conceptnet.io/query?rel=" + aConsulter + "&limit=1000";
		getDataFrom(url)

	} else if(aConsulter.indexOf("/c/") > -1 && aConsulter.indexOf("/r/") > -1 && aConsulter.indexOf("&") > -1) {
		var aConsulter2 = document.getElementById("barreConsulter").value.replace(/\s/g, '').split("&");
		url = "https://api.conceptnet.io/query?node="+aConsulter2[0]+"&rel="+aConsulter2[1]+"&limit=1000";
		getDataFrom(url)
	}

}

function getDataFrom(url) {

	document.getElementById("nombreResultat").innerHTML = "";
	document.getElementById("resultatsConsultation").innerHTML = '<div class="spinner-border m-5" role="status"><span class="sr-only"></span></div>';

	$.getJSON(url)
	.done(function(e) {
		try {
			prevPage = ""; nextPage = "";
			prevPage = "https://api.conceptnet.io" + e["view"]["previousPage"];
			nextPage = "https://api.conceptnet.io" + e["view"]["nextPage"];
		} catch(e) { }

		var edges = e.edges;

		var table_header = `<table id="tableConsulter" class="table table-striped">
		<thead><tr><th>start</th><th>relation</th><th>end</th></tr></thead>
		<tbody>`;
		var table_body = "";
		var table_footer = '</tbody></table>';
		var compteurResultat = 0;

		for(var j=0; j<edges.length; j++) {
			var start = edges[j]["start"];
			var end = edges[j]["end"];
			var relation = edges[j]["rel"]["label"];

			if(start["language"] == "fr" && end["language"] == "fr" ||
				start["language"] == "fr" && end["language"] == "en" ||
				start["language"] == "en" && end["language"] == "en" ||
				start["language"] == "en" && end["language"] == "fr") {

				table_body += "<tr><td>"+ start["term"] +"</td><td>"+ relation +"</td><td>"+ end["term"] +"</td></tr>";
			compteurResultat++;
		}
	}

	document.getElementById("nombreResultat").innerHTML = compteurResultat + " résultats";
	document.getElementById("resultatsConsultation").innerHTML = table_header + table_body + table_footer;

	$("#prevBtn").show(); $("#nextBtn").show();
	if(prevPage.includes("undefined") || prevPage == "") { $("#prevBtn").hide(); }
	if(nextPage.includes("undefined") || nextPage == "") { $("#nextBtn").hide(); }

	Array.from(document.querySelectorAll("#tableConsulter tbody tr")).forEach(e => e.addEventListener("click", function() {
		ajouter_a_faits(this);
	}));

})
	.fail(function() {
		document.getElementById("resultatsConsultation").innerHTML = "<p>Input invalide.</p>";
	})

}


function ajouter_a_faits(f) {
	document.getElementById("tableFaitsBody").innerHTML += f.outerHTML;

	var newStart = f.querySelectorAll("td")[0].innerHTML;
	var newRelation = f.querySelectorAll("td")[1].innerHTML;
	var newEnd = f.querySelectorAll("td")[2].innerHTML;

	var nouveauFait = { "start": newStart, "relation": newRelation, "end": newEnd }

	faits.push(nouveauFait)
	document.getElementById("nombreFaits").innerHTML = analyserFaits();
}