# Käyttöohje

lataa tiedosto [Minesweeper](https://github.com/murmurian/ot-harjoitustyo/releases/download/Loppupalautus/Minesweeper.jar)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla:

`java -jar Minesweeper.jar`

## Pelaaminen

![Alkutila](/dokumentaatio/aloitus.jpg)

Tehtävänäsi on avata kaikki ruudut joissa ei ole miinaa. Voit pelata peliä kolmella valmiiksi määritellyllä vaikeustasolla:

- Helppo, laudan koko 9x9, miinoja 10
- Keskivaikea, laudan koko 16x16, miinoja 40 
- Vaikea, laudan koko 30x16, miinoja 99
- Mukautettu, käyttäjä voi määrittää itse laudan koon ja miinojen määrän. (9-30 x 9-20, miinoja 10-200)

Peli käynnistyy helpoimmalla vaikeustasolla. Vasemman reunan nappuloista voit vaihtaa halutun vaikeustason. Klikkaamalla *New Game* painiketta käynnistyy uusi peli. Liukusäätimillä voit säätää mukautetun kentän leveyden, korkeuden ja miinojen määrää. Jos asetat enemmän miinoja kuin pelilaudalle mahtuu, säätää peli automaattisesti miinojen määrää pienemmäksi.

*High scores* painiketta painamalla näet 10 parasta suoritusta valitun vaikeustasonappulan mukaisella tasolla.

Käytössäsi on kaksi toimintoa. Hiiren vasemmalla napilla peli avaa ruudun:

![Vasen](/dokumentaatio/vasen.jpg)

Ruudussa oleva numero ilmaisee monessako viereisessä ruudussa on miina. Jos osut ruutuun jonka vieressä ei ole miinoja, avaa peli automaattisesti viereiset tyhjät ruudut. Jos aavistat, että ruudussa on miina, voit merkitä sen lipulla oikealla hiiren napilla:

![Oikea](/dokumentaatio/oikea.jpg)

Liputettua ruutua ei voi avata, mutta voit poistaa lipun oikealla napilla. Kun kaikki miinattomat ruudut on avattu, peli liputtaa loput ruudut automaattisesti:

![Voitto](/dokumentaatio/voitto.jpg)

Pelin oikeassa yläkulmassa näet käyttämäsi ajan. Kello käynnistyy, kun avaat tai liputat ensimmäisen ruudun. Jos voitat pelin nopeasti, voit päästä top 10 listalle. Tälloin peli kysyy nimeäsi:

![High score](/dokumentaatio/highscore.jpg)

Jos osut miinaan, häviät. Peli näyttää tälloin laudalla olevat miinat. Miina johon osuit merkitään pääkallolla:

![Tappio](/dokumentaatio/tappio.jpg)

Hauskaa pelailua!
