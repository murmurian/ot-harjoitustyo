# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne jakautuu kolmelle tasolle oheisen pakkausrakenteen mukaisesti:

![pakkauskaavio](/dokumentaatio/pakkaus.jpg)

JavaFX:llä toteutettu käyttöliittymä on pakkauksessa *minesweeper.ui*. Sovelluslogiikka on pakkauksessa *minesweeper.engine*. Sovelluksen high score listojen pysyväistallennus ja käsittely on toteutettu pakkauksessa *minesweeper.dao*.

## Käyttöliittymä

Käyttöliittymä on pyritty toteuttamaan yksinkertaisena ja selkeänä. Se koostuu yhdestä sovelluksen *stageen* sijoitetusta *scene*-oliosta josta löytyy kaikki käyttöliittymän elementit. High score listat aukeavat omaan JavaFX:n *Alert*-oliolla toteutettuun pop-up -ikkunaan. Pelaajan päästessä high score listalle aukeaa *TextInputDialog*-oliolla toteutettu pop-up -ikkuna joka kysyy pelaajan nimeä.

Käyttöliittymä on eriytetty sovelluslogiikasta. Käyttöliittymällä itsellään ei ole tietoja pelin tilanteesta vaan se kutsuu *Game*-luokan metodeja tarvitessaan. Pelilaudan nappulat piirretään uudestaan pelaajan avatessa ruudun. Käyttöliitymässä näkyvä aika päivittyy *AnimationTimer*-luokan metodeilla.

## Toiminnallisuus

![sekvenssikaavio](/dokumentaatio/openCell.png)

Sekvenssikaaviossa kuvattuna sovelluksen ehkä keskeisin toiminto solun avaaminen. Kaavio kuvaa tilannetta jossa avataan aiemmin avaamaton miinaton solu. Koska solu on kolmen miinan vieressä, ei viereisiä tyhjiä soluja aleta avaamaan rekursiivisesti. Toinen keskeinen toiminnallisuus käyttäjälle on peliruudun merkitseminen lipulla. Toteutus on melko suoraviivainen.

## Tietojen tallennus

Sovellus tallentaa jokaisen vaikeustason 10 parasta aikaa tiedostoon. Tallennuksesta vastaa *HighscoresDao*-luokka joka on toteutettu Data Access Object -suunnittelumallin mukaisesti. Tulokset tallennetaan muodossa:

`0;123;nimi`

Ensimmäisenä on vaikeustaso (0-3), toisena aika sekunteina ja viimeisenä pelaajan antama nimi.

Listojen käsittely ohjelmassa on toteutettu *TreeMap<Integer, String>*-tietorakenteena. *checkIfHighscore*-metodi tarkistaa onko pelaajan tulos listakelpoinen. Jos tulos lisätään listalle, järjestää binääripuu tulokset automaattisesti oikeaan järjestykseen ajan mukaan. Jos puun koko ylittää halutun 10, poistetaan huonoin tulos.

## Sovelluksessa olevia puutteita

- Graafisen käyttöliittymän toteuttava koodi on todella kömpelöä. Sovellusta tehdessä kokemusta JavaFX:n käytöstä ei ollut juuri lainkaan, onneksi tehdessä oppi nopeasti. Graafinen käyttöliittymä toteutettiin vasta projektin loppuvaiheissa joten ajanpuutteen vuoksi refaktorointiakaan ei ehtinyt tekemään. FMXL:n opettelu voisi olla hyödyksi käyttöliittymää parannellessa. Omissa testailuissa olen kuitenkin ihan tyytyväinen ulkoasuun, jota maustettiin lopulta hieman ajan hengen mukaiseksi...
- Tiedostojen kirjoittaminen oli myös uutta, joten esim. *Game*-luokan osalta mentiin nyt sellaisella ratkaisulla joka jokseenkin toimi.
- 20.12. 23:51 täytyy todeta, että dokumentaation hiomiselta loppuu aika. Erityisesti arkkitehtuurikuvausta voisi vielä paljon parannella.
