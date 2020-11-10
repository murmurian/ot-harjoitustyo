# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on pulmapeliklassikko Miinaharava, jossa pelaajan tehtävänä on etsiä miinat pelilaudalta. Tarkempi kuvaus pelin säännöistä löytyy esim. Wikipediasta:
[Miinaharava](https://fi.wikipedia.org/wiki/Miinaharava_(peli))

## Käyttäjät

Pelissä on yksi käyttäjä eli pelaaja.

## Toiminnallisuus

- Käyttäjä voi pelata kolmella eri vaikeustasolla:
  - Helppo, laudan koko 9x9, miinoja 10
  - Keskivaikea, laudan koko 16x16, miinoja 40
  - Vaikea, laudan koko 30x16, miinoja 99
- Käyttäjä voi aloittaa uuden pelin 
- Käyttäjä voi avata ruudun
  - Jos avatussa ruudussa on miina, peli päättyy ja pelilauta paljastetaan käyttäjälle
  - Jos avatun ruudun viereisissä ruuduissa on miinoja, näytetään näiden miinojen määrä
  - Jos avattu ruutu on tyhjä, avataan myös muut ruutuun yhteydessä olevat miinattomat ruudut
- Käyttäjä voi merkitä ruudun lipulla ja poistaa lipun ruudusta
- Peli päättyy, kun:
  - Voittoon, jos kaikki ruudut joissa ei ole miinaa saadaan avattua
  - Tappioon, jos pelaaja osuu miinaan

## Jatkokehitysideoita

- Peli näyttää käyttäjälle ajan sekunteina pelin alusta
- Jos käyttäjä voittaa pelin, pyydetään käyttäjän nimimerkki ja lisätään aika pistetilastoon
- Käyttäjä voi itse määritellä sopivissa rajoissa oman laudan koon ja miinojen määrän
- Jos käyttäjä osuu ensimmäisellä arvauksella miinaan, siirretään miina johonkin toiseen ruutuun
- Graafisen ulkoasun kehittely
