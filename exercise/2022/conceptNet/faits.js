var faits = [
{
	start: "/c/en/water",
	relation: "UsedFor",
	end: "/c/en/drink"
},
{
	start: "/c/en/fire",
	relation: "CapableOf",
	end: "/c/en/burn_things"
},
{
	start: "/c/en/apple",
	relation: "Synonym",
	end: "/c/fr/pomme"
},

{
	start : "/c/en/water",
	relation : "UsedFor",
	end: "/c/en/drink"
},
{
	start : "/c/en/fish",
	relation : "AtLocation",
	end : "/c/en/water"
},
{
	start : "/c/en/water",
	relation : "HasProperty",
	end : "/c/en/transparent"
},
{
	start : "/c/en/hydrogen",
	relation : "PartOf",
	end : "/c/en/water"
},
{
	start : "/c/en/ice",
	relation : "RelatedTo",
	end : "/c/en/water"
},
{
	start : "/c/en/cake",
	relation : "RelatedTo",
	end : "/c/en/birthday"
},
{
	start : "/c/en/cake",
	relation : "UsedFor",
	end : "/c/en/celebrate"
},
{
	start : "/c/en/fruitcake",
	relation : "IsA",
	end : "/c/en/cake"
},
{
	start : "/c/fr/beurre",
	relation : "Synonym",
	end : "/c/en/cake"
},
{
	start : "/c/en/person",
	relation : "Desires",
	end : "/c/en/cake"
},
{
	start : "/c/en/playing",
	relation : "Causes",
	end : "/c/en/fun"
},
{
	start : "/c/en/ride_horse",
	relation : "MotivatedByGoal",
	end : "/c/en/fun"
},
{
	start : "/c/en/play_games",
	relation : "HasSubevent",
	end : "/c/en/fun"
},
{
	start : "/c/en/sailing",
	relation : "HasProperty",
	end : "/c/en/fun"
},
{
	start : "/c/en/play",
	relation : "RelatedTo",
	end : "/c/en/fun"
},
{
	start : "/c/en/teacher",
	relation : "CapableOf",
	end : "/c/en/school_students"
},
{
	start : "/c/en/teacher",
	relation : "Desires",
	end : "/c/en/teach"
},
{
	start : "/c/en/attending_school",
	relation : "HasPrerequisite",
	end : "/c/en/teacher"
},
{
	start : "/c/fr/enseignant",
	relation : "Synonym",
	end : "/c/en/teacher"
},
{
	start : "/c/en/teacher",
	relation : "CapableOf",
	end : "/c/en/mark_papers"
},
{
	start : "/c/en/digital",
	relation : "RelatedTo",
	end : "/c/en/digit"
},
{
	start : "/c/fr/numérique",
	relation : "Synonym",
	end : "/c/en/digital"
},
{
	start : "/c/en/analog",
	relation : "Antonym",
	end : "/c/en/digital"
},
{
	start : "/c/en/digitalism",
	relation : "DerivedFrom",
	end : "/c/en/digital"
},
{
	start : "/c/en/digital",
	relation : "HasContext",
	end : "/c/en/electronics"
},
{
	start : "/c/en/hunger",
	relation : "CausesDesire",
	end : "/c/en/eat"
},
{
	start : "/c/en/cook_dinner",
	relation : "MotivatedByGoal",
	end : "/c/en/eat"
},
{
	start : "/c/en/eat",
	relation : "HasPrerequisite",
	end : "/c/en/prepare_food"
},
{
	start : "/c/en/animal",
	relation : "Desires",
	end : "/c/en/eat"
},
{
	start : "/c/en/plate",
	relation : "RelatedTo",
	end : "/c/en/eat"
},
{
	start : "/c/en/good",
	relation : "CapableOf",
	end : "/c/en/battle_evil"
},
{
	start : "/c/en/best",
	relation : "RelatedTo",
	end : "/c/en/good"
},
{
	start : "/c/en/life",
	relation : "HasProperty",
	end : "/c/en/good"
},
{
	start : "/c/en/bad",
	relation : "DistinctFrom",
	end : "/c/en/good"
},
{
	start : "/c/en/good",
	relation : "DefinedAs",
	end : "/c/en/opposite_of_evil"
},
{
	start : "/c/en/blunted",
	relation : "SimilarTo",
	end : "/c/en/dull"
}
,
{
	start : "/c/en/blunted",
	relation : "RelatedTo",
	end : "/c/en/high"
},
{
	start : "/c/en/blunted",
	relation : "HasContext",
	end : "/c/en/slang"
},
{
	start : "/c/en/unblunted",
	relation : "DerivedFrom",
	end : "/c/en/blunted"
},
{
	start : "/c/en/stub",
	relation : "RelatedTo",
	end : "/c/en/blunted"
},
{
	start : "/c/en/camera",
	relation : "UsedFor",
	end : "/c/en/take_pictures"
},
{
	start : "/c/en/cameras",
	relation : "FormOf",
	end : "/c/en/camera"
},
{
	start : "/c/en/aperture",
	relation : "PartOf",
	end : "/c/en/camera"
},
{
	start : "/c/en/flash",
	relation : "RelatedTo",
	end : "/c/en/camera"
},
{
	start : "/c/en/camera",
	relation : "HasA",
	end : "/c/en/flash"
},
{
	start : "/c/en/weapon",
	relation : "UsedFor",
	end : "/c/en/kill"
},
{
	start : "/c/en/rifle",
	relation : "IsA",
	end : "/c/en/weapon"
},
{
	start : "/c/en/club",
	relation : "RelatedTo",
	end : "/c/en/weapon"
},
{
	start : "/c/en/mine",
	relation : "IsA",
	end : "/c/en/weapon"
},
{
	start : "/c/en/weapon",
	relation : "UsedFor",
	end : "/c/en/threatening"
},
{
	start : "/c/en/donut",
	relation : "AtLocation",
	end : "/c/en/bakery"
},
{
	start : "/c/fr/doughnut",
	relation : "FormOf",
	end : "/c/en/donut"
},
{
	start : "/c/en/hole",
	relation : "AtLocation",
	end : "/c/en/donut"
},
{
	start : "/c/en/donut",
	relation : "HasContext",
	end : "/c/en/north_america"
},
{
	start : "/c/en/cruller",
	relation : "RelatedTo",
	end : "/c/en/donut"
},
{
	start : "/c/en/rabbit",
	relation : "RelatedTo",
	end : "/c/en/animal"
},
{
	start : "/c/en/rabbit",
	relation : "RelatedTo",
	end : "/c/en/rodent"
}
,
{
	start : "/c/en/rabbit",
	relation : "RelatedTo",
	end : "/c/en/big_ears"
},
{
	start : "/c/en/rabbit",
	relation : "RelatedTo",
	end : "/c/en/carrots"
},
{
	start : "/c/en/rabbit",
	relation : "RelatedTo",
	end : "/c/en/hare"
},
{
	start : "/c/fr/roi",
	relation : "Synonym",
	end : "/c/en/king"
},
{
	start : "/c/fr/roi",
	relation : "RelatedTo",
	end : "/c/fr/régalien"
},
{
	start : "/c/fr/roi",
	relation : "RelatedTo",
	end : "/c/fr/royaume"
},
{
	start : "/c/fr/roi",
	relation : "Synonym",
	end : "/c/en/baron"
},
{
	start : "/c/fr/roi",
	relation : "Synonym",
	end : "/c/en/martin_luther_king_jr"
},
{
	start : "/c/fr/exclusion_numérique",
	relation : "RelatedTo",
	end : "/c/fr/internet"
},
{
	start : "/c/fr/viralité",
	relation : "HasContext",
	end : "/c/fr/internet"
},
{
	start : "/c/en/podcast",
	relation : "HasContext",
	end : "/c/fr/internet"
},
{
	start : "/c/en/webcomic",
	relation : "RelatedTo",
	end : "/c/fr/internet"
},
{
	start : "/c/fr/internet",
	relation : "EtymologicallyDerivedFrom",
	end : "/c/en/internet"
},
{
	start : "/c/fr/chum",
	relation : "HasContext",
	end : "/c/fr/québec"
},
{
	start : "/c/fr/chum",
	relation : "EtymologicallyDerivedFrom",
	end : "/c/en/chum"
},
{
	start : "/c/fr/chum",
	relation : "RelatedTo",
	end : "/c/fr/ami"
},
{
	start : "/c/fr/chum",
	relation : "RelatedTo",
	end : "/c/en/boyfriend"
},
{
	start : "/c/fr/chum",
	relation : "RelatedTo",
	end : "/c/fr/camarade"
},
{
	start : "/c/en/advancing_into_battle",
	relation : "Causes",
	end : "/c/en/death"
},
{
	start : "/c/en/poison",
	relation : "RelatedTo",
	end : "/c/en/death"
},
{
	start : "/c/en/jumping_out_of_window",
	relation : "Causes",
	end : "/c/en/death"
},
{
	start : "/c/en/death",
	relation : "HasProperty",
	end : "/c/en/inevitable"
},
{
	start : "/c/en/death",
	relation : "PartOf",
	end : "/c/en/life"
},
{
	start : "/c/en/snow",
	relation : "AtLocation",
	end : "/c/en/canada"
},
{
	start : "/c/en/canada",
	relation : "IsA",
	end : "/c/en/country_in_north_america"
},
{
	start : "/c/en/canada",
	relation : "HasProperty",
	end : "/c/en/cold"
},
{
	start : "/c/en/canada",
	relation : "IsA",
	end : "/c/en/large_country_with_cold_winters"
},
{
	start : "/c/en/canada",
	relation : "DefinedAs",
	end : "/c/en/second_largest_country_in_world"
},
{
	start : "/c/en/taxi",
	relation : "MannerOf",
	end : "/c/en/ride"
},
{
	start : "/c/en/cab",
	relation : "Synonym",
	end : "/c/en/taxi"
},
{
	start : "/c/en/taxi",
	relation : "AtLocation",
	end : "/c/en/train_station"
},
{
	start : "/c/en/taxi",
	relation : "AtLocation",
	end : "/c/en/airport"
},
{
	start : "/c/en/taxi",
	relation : "MannerOf",
	end : "/c/en/travel"
},
{
	start : "/c/en/socks",
	relation : "ReceivesAction",
	end : "/c/en/worn_on_feet"
},
{
	start : "/c/en/socks",
	relation : "AtLocation",
	end : "/c/en/shoes"
},
{
	start : "/c/en/socks",
	relation : "AtLocation",
	end : "/c/en/drawer"
},
{
	start : "/c/en/socks",
	relation : "UsedFor",
	end : "/c/en/cover_cold_feet"
},
{
	start : "/c/en/socks",
	relation : "ReceivesAction",
	end : "/c/en/found_in_drawer"
},
{
	start : "/c/en/remember",
	relation : "RelatedTo",
	end : "/c/en/memory"
},
{
	start : "/c/en/memory",
	relation : "RelatedTo",
	end : "/c/en/brain"
},
{
	start : "/c/en/socks",
	relation : "ReceivesAction",
	end : "/c/en/found_in_drawer"
},
{
	start : "/c/en/non_volatile_storage",
	relation : "IsA",
	end : "/c/en/memory"
},
{
	start : "/c/en/remembering",
	relation : "HasPrerequisite",
	end : "/c/en/memory"
},
{
	start : "/c/en/leave",
	relation : "RelatedTo",
	end : "/c/en/go"
},
{
	start : "/c/en/turn",
	relation : "RelatedTo",
	end : "/c/en/go"
},
{
	start : "/c/en/drop",
	relation : "RelatedTo",
	end : "/c/en/go"
},
{
	start : "/c/en/stop",
	relation : "DistinctFrom",
	end : "/c/en/go"
},
{
	start : "/c/en/go_to_movie",
	relation : "HasPrerequisite",
	end : "/c/en/go"
},
{
	start : "/c/fr/pile",
	relation : "Synonym",
	end : "/c/en/battery"
},
{
	start : "/c/en/fear",
	relation : "IsA",
	end : "/c/en/emotion"
},
{
	start : "/c/en/ordinary",
	relation : "RelatedTo",
	end : "/c/en/plain"
},
{
	start : "/c/en/potato",
	relation : "AtLocation",
	end : "/c/en/salad"
},
{
	start : "/c/en/birds",
	relation : "Desires",
	end : "/c/en/fly"
},
{
	start : "/c/en/age",
	relation : "RelatedTo",
	end : "/c/en/young"
},
{
	start : "/c/en/demolish",
	relation : "MannerOf",
	end : "/c/en/destroy"
},
{
	start : "/c/en/leaf",
	relation : "PartOf",
	end : "/c/en/tree"
},
{
	start : "/c/en/sneeze",
	relation : "RelatedTo",
	end : "/c/en/nose"
},
{
	start : "/c/en/program",
	relation : "HasPrerequisite",
	end : "/c/en/turn_on_computer"
}
]

function createTable(t) {
	var table_header = '<table id="tableFaits" class="table table-striped"><thead><tr><th>start</th><th>relation</th><th>end</th></tr></thead><tbody id="tableFaitsBody">';
	var table_body = "";
	var table_footer = '</tbody></table>';

	for(var i=0; i<t.length; i++) {
		table_body += "<tr><td>"+ t[i].start +"</td><td>"+ t[i].relation +"</td><td>"+ t[i].end +"</td></tr>";
	}

	return table_header + table_body + table_footer;
}


function analyserFaits() {
	var concepts = [];
	var relations = [];

	for(var i=0; i<faits.length; i++) {
		concepts.push(faits[i].start);
		concepts.push(faits[i].end);
		relations.push(faits[i].relation);
	}

	var nbFaits = faits.length;
	var nbConcepts = Array.from(new Set(concepts)).length;
	var nbRelations = Array.from(new Set(relations)).length;

	return nbFaits +" faits<br>"+ nbConcepts +" concepts uniques (start/end)<br>"+ nbRelations +" relations uniques";
}


window.onload = function () {
	document.getElementById("faits").innerHTML = "<p id='nombreFaits'>"+analyserFaits()+"</p>" + createTable(faits)
}