# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne jakautuu kolmelle tasolle oheisen pakkausrakenteen mukaisesti:

![pakkauskaavio](/dokumentaatio/pakkaus.jpg)

JavaFX:llä toteutettu käyttöliittymä on pakkauksessa *minesweeper.ui*. Sovelluslogiikka on pakkauksessa *minesweeper.engine*. Sovelluksen high score listojen pysyväistallennus ja käsittely on toteutettu pakkauksessa *minesweeper.dao*.

## Käyttöliittymä

Käyttöliittymä on pyritty toteuttamaan yksinkertaisena ja selkeänä. Se koostuu yhdestä sovelluksen *stageen* sijoitetusta *scene*-oliosta josta löytyy kaikki käyttöliittymän elementit. High score listat aukeavat omaan JavaFX:n *Alert*-oliolla toteutettuun pop-up -ikkunaan. Pelaajan päästessä high score listalle aukeaa *TextInputDialog*-oliolla toteutettu pop-up -ikkuna joka kysyy pelaajan nimeä.

Käyttöliittymä on eriytetty sovelluslogiikasta. Käyttöliittymällä itsellään ei ole tietoja pelin tilanteesta vaan se kutsuu *Game*-luokan metodeja tarvitessaan. Pelilaudan nappulat piirretään uudestaan pelaajan avatessa ruudun. Käyttöliitymässä näkyvä aika päivittyy *AnimationTimer*-luokan metodeilla.

## Sovelluslogiikka



## Toiminnallisuus

Kuvattuna sovelluksen ehkä keskeisin toiminto solun avaaminen. Kaavio kuvaa tilannetta jossa avataan aiemmin avaamaton miinaton solu. Koska solu on kolmen miinan vieressä, ei viereisiä tyhjiä soluja aleta avaamaan rekursiivisesti.

![sekvenssikaavio](/dokumentaatio/openCell.png)
