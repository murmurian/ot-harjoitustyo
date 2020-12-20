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
  - Mukautettu, käyttäjä voi määrittää itse laudan koon ja miinojen määrän. (9-30 x 9-20, miinoja 10-200)
- Käyttäjä voi aloittaa uuden pelin
- Käyttäjä voi avata ruudun
  - Jos avatussa ruudussa on miina, peli päättyy ja pelilauta paljastetaan käyttäjälle
  - Jos avatun ruudun viereisissä ruuduissa on miinoja, näytetään näiden miinojen määrä
  - Jos avattu ruutu on tyhjä, avataan myös muut ruutuun yhteydessä olevat miinattomat ruudut
- Käyttäjä voi merkitä ruudun lipulla ja poistaa lipun ruudusta
- Peli päättyy:
  - Voittoon, jos kaikki ruudut joissa ei ole miinaa saadaan avattua
  - Tappioon, jos pelaaja osuu miinaan
- Peli näyttää käyttäjälle kuluneen ajan sekunteina ensimmäisen ruudun avaamisesta tai lipun asettamisesta
- Jokaiselle vaikeustasolle tallennetaan 10 parasta tulosta tiedostoon
- Parhaat tulokset saa esille "High scores" -painikkeella.

## Jatkokehitysideoita

- Graafisen käyttöliittymän rakentavan luokan voisi kirjoittaa uusiksi ja samalla ulkoasua kehittää.
- Parhaat tulokset voisi tallentaa tiedoston sijaan tietokantaan. Nykyinen tallennettava tiedon määrä on tosin vähäistä ja binäärihakupuina toteutetut listat ajavat asian hyvin, joten tietokantaan voisi tallentaa muutakin tietoja. Pelissä on esimerkiksi mahdollisuus arpoa miinat siemenarvon perusteella ja tämän voisi tallentaa jolloin pelaajat voisivat pelata kenttiä uudestaan ja kilpailla kuka on nopein samassa kentässä..
