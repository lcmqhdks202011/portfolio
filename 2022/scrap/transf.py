template = '''
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>%s</title>
	<style>
		#solution, #vraieSolution { display: none; }
		#solution:checked ~ #vraieSolution{ display: block; }
		#solution:checked ~ #fausseSolution{ display: none; }
	</style>
	<style>%s</style>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>

<body>
	<div class="jumbotron text-center">
		<h1>Tests W3</h1>
		<p>%s</p>
		<a class="btn btn-primary my-2" id="prevBtn" href="%s">Prev</a>
		<a class="btn btn-primary my-2" id="nextBtn" href="%s">Next</a>
	</div>

	<div class="container">

			<div class="row-sm-4 p-4 my-2 border border-dark rounded">
				<h3>CSS</h3>
				<pre><code id="css_test">%s</code></pre>
			</div>


			<div class="row-sm-4 p-4 my-2 border border-dark rounded">
				<h3>HTML</h3>
				<pre><code id="html_test">%s</code></pre>
			</div>


			<div class="row-sm-4 p-4 border border-dark rounded">
				<input type=checkbox id="solution">
				<label for="solution" class="btn btn-danger mb-4">Solution</label>

				<div id="fausseSolution">%s</div>

				<div id="vraieSolution">%s</div>

		</div>
	</div>

</body>

</html>
'''

import os, html, sys
from bs4 import BeautifulSoup

if sys.argv[1].endswith(".html") :
	dossierIn = '.'
	dossierOut = 'result'

else :
	dossierIn = sys.argv[1]
	# dossierOut = 'result'
	dossierOut = sys.argv[2]

if not os.path.exists(dossierOut):
    os.makedirs(dossierOut)

fichiers = sys.argv

for fichier in fichiers:

	if fichier.endswith('.html'):

		fichier_in = open(os.path.join(dossierIn, fichier), 'r')
		fichier_out = open(os.path.join(dossierOut, fichier), 'w+')

		soup = BeautifulSoup(fichier_in.read(), 'html.parser')

		css_extrait = soup.select('.rules')[0].string
		html_extrait = soup.select('.rules')[1].string #html en "clair" : <p>TEST</p>
		html_brut = html.escape(soup.select('.rules')[1].string) #html encodÃ© : &lt;p&gt;TEST&lt;/p&gt;


		if fichier == "css3-modsel-1.html":
			prev_page = ""
		else:
			prev_page = soup.find("link",{"rel":"prev"})['href']

		if fichier == "css3-modsel-d4.html":
			next_page = ""
		else:
			next_page = soup.find("link",{"rel":"next"})['href']


		vraiCSS = "#vraieSolution " + css_extrait.replace("}\n", "}\n#vraieSolution ").replace(",",", #vraieSolution ")
		result = template % (fichier, vraiCSS, fichier, prev_page, next_page, css_extrait, html_brut, html_extrait, html_extrait)

		fichier_out.write(result.replace("\n#vraieSolution </style>","</style>"))
		
		fichier_out.close()
		fichier_in.close()