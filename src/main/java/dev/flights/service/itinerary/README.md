# Itinerary Service

Ez a service felelős azért, hogy kettő airport között a legrövidebb útvonalakat adja vissza.

## Előfeltételek
- Minden airport-hoz hozzá kell rendelni az X és Y koordinátát

## Input-ok
- Indulási dátum (`departure_at`)
- Indulási airport (`departure_airport_id`)
- Érkezési airport (`arrival_airport_id`)

## Lépések
1. Le kell kérdezni, hogy az adott időintervallumban mely airport-okból indulnak flight-ok, ezáltal azok az airport-ok ki lesznek véve a listából, amelyek nem relevánsak
2. Kell egy olyan súlyozott graph adatbázis, amely minden airport-hoz letárolja, hogy az összes többi airport milyen távolságra van
3. Be kell járni az összes airport-ot, és számolni kell, hogy minden airport-hoz a lépések során mekkora a már megtett távolság.
4. Így amikor eljutunk a kiindulási airport-ból a végső airport-ba, akkor lesz egy olyan adatbázisunk, amelyben benne lesz minden lehetséges útvonal, és mindegyiknek lesz egy saját súlyozott értéket.
5. Ez alapján sorrendet tudunk állítani.
