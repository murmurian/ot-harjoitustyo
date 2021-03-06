# Minesweeper

Sovellus on pulmapeliklassikko Miinaharava, jossa pelaajan tehtävänä on etsiä miinat pelilaudalta. Tarkempi kuvaus pelin säännöistä löytyy esim. Wikipediasta:
[Miinaharava](https://fi.wikipedia.org/wiki/Miinaharava_(peli))

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/murmurian/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/murmurian/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/murmurian/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/murmurian/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/murmurian/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[Viikon 5 release](https://github.com/murmurian/ot-harjoitustyo/releases/tag/viikko5)

[Viikon 6 release](https://github.com/murmurian/ot-harjoitustyo/releases/tag/Viikko6)

[Loppupalautus](https://github.com/murmurian/ot-harjoitustyo/releases/tag/Loppupalautus)

## Komentorivitoiminnot

Ohjelma voidaan käynnistää komentoriviltä komennolla:

`mvn compile exec:java -Dexec.mainClass=minesweeper.Minesweeper`

### Testaus

Testit suoritetaan komennolla:

`mvn test`

Testikattavuusraportti luodaan komennolla:

`mvn test jacoco:report`

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Checkstyle

checkstyle.xml tarkistukset suoritetaan komennolla:

`mvn jxr:jxr checkstyle:checkstyle`

Virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html

### Suoritettavan jar-tiedoston luominen

Komennolla:

`mvn package`

hakemistoon *target* luodaan suoritettava jar Minesweeper-1.0-SNAPSHOT.jar

### JavaDoc

Komennolla:

`mvn javadoc:javadoc`

luodaan JavaDoc tiedosoon target/site/apidocs/index.html



