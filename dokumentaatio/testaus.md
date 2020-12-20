# Testausdokumentti

Ohjelmaa on testattu JUnitin automatisoiduilla yksikkö- ja integraatiotesteteillä. Järjestelmällätestejä on tehty manuaalisesti Windows ja Linux ympäristöissä.

## Yksikkö- ja integraatiotestaus

Automatisoitujen testien pääpaino on minesweeper.engine luokan testeissä. *BoardTest* luokka testaa, että pelilauta muodostetaan pelin alussa ja erilaisissa pelitilanteissa oikein. *GeneratorTest* varmistaa vielä parilla omalla testillä, että luotu pelilauta on oikean kokoinen ja miinakentässä on oikea määrä miinoja. *GameTest* luokka testaa yleisemmällä tasolla, että peli alkaa ja toimii oikein. *BoardTest*stä se eroaa siten, että sillä ei ole pääsyä *Cell*-luokan olioihin vaan testit testaavat Board-luokan palauttamaa merkkiesitystä pelitilanteesta. Lisäksi *GameTest* testaa pelin ajanottoa. CellTest on luotu projektin varhaisessa vaiheessa ja siksi testaakin lähinnä getterit ja setterit, mutta en kokenut tarpeelliseksi sitä poistaakaan.

Pelin high score -toiminnallisuus lisättiin vasta projektin loppuvaiheilla. *FileHighscoresDaoTest* voisi siksi testata kattavammin tapauksia, kun riippuen onko tiedosto jo olemassa vai ei. Nykyiset testit keskittyvät testaamaan top 10 listaan liittyviä toimenpiteitä yhden pelikerran sisällä ja testitiedosto tuhotaan aina testien suorittamisen päätteeksi.

### Testauskattavuus

Sovelluksen testauskattavuus on hyvä. Rivikattavuus on 88% ja haarautumakattavuus 96%. Tämä on tosin osin hieman petollista, koska sovelluksessa käyttäjä lähinnä availee tai liputtaa peliruutuja ja jo yksittäisen ruudun avaaminen käy useissa tilanteissa ison osan koodia läpi. Automatisoituja testejä varten on myös hieman haastavaa keksiä erilaisia pelitilanteita jotka horjuttaisivat pelin logiikkaa ja kyseiset ongelmat löytyvätkin helpommin manuaalisessa testaamisessa.

![testikattavuus](/dokumentaatio/testausraportti.jpg)

Testeistä jäi puuttumaan *Game*-luokan tiedon tallenukseen liittyvät metodit. Syynä tähän on metodien jokseenkin kömpelö toteutus johtuen top 10 listojen myöhäisestä lisäämisestä.

Graafisen käyttöliittymän rakentava *MinesweeperGui*-luokka on jätetty pois testeistä.


## Järjestelmätestaus

Sovellusta on testattu pelaamalla peliä Windows ja Linux ympäristöissä. Testatessa määrittelydokumentissa kuvattua toiminnallisuutta ja tiedon tallentumista on kokeiltu eri tilanteissa ja vaikeustasoilla.


