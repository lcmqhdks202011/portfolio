import os, sys

from bs4 import BeautifulSoup



dossier = '.'

fichier = sys.argv[1]



if fichier.endswith('.html'):
  fichier_in_html = open(os.path.join(dossier, fichier), 'r')
  soup = BeautifulSoup(fichier_in_html.read(), 'html.parser')
  fis = soup.style.string.split(sep='\n')

  for (i,f) in enumerate(fis):
    fichier_out_css = open(fichier.replace(".html", "-" + str(i) + ".css"), 'w')
    fichier_out_css.write(f)
    fichier_out_css.close()

  fichier_in_html.close()