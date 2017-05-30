# 1 Okazjo-łowca (occasionFinderAndroid)
Głównym zadaniem aplikacji jest zbieranie informacji o okazjach (zwykle zwanymi 'Gorący strzał' luib 'Okazja Dnia') z wybranych sklepów internetowych w Polsce i przedstawienie ich użytkownikowi w jednym miejscu. Aplikacja wymaga do działania połączenia z internetem w trakcie uruchamiania, jeśli takiego połączenia nie ma, użytkownik zostanie o tym odpowiednio poinformowany.

# 2. Funkcjonalności aplikacji
Aplikacja posiada następujące funkcjonalności:
- zbieranie danych z poszczególnych sklepów internetowych
- prezentowanie tych odpowiednio przetworzonych danych użytkownikowi
- kliknięcie w logo sklepu otwiera witrynę sklepu w przeglądarce
- dłuższe kliknięcie w obrazek produktu otwiera jego kartę w przeglądarce

<b>Aplikacja korzysta z technologii YQL (Yahoo! Query Language), która pozwala na parsowanie wybranego (przy pomocy języka XPath i stworzenia odpowiedniego zapytania) wybranego fragmentu strony www i wygenerowanie linka do prezentacji tych danych w formacie obiektu JSON.</b>

# 3. Opis klas
- ExceptionHandler - obiekt tej klasy będzie domyślnym exception handlerem dla aplikacji. W przypadku błędu wywoła aktywność CrashActivity, zamiast wyłączyć aplikację.
- CrashActivity - klasa, która ma za zadanie poinformowanie użytkownika o błędzie.
- DataGatherer - klasa odpowiedzialna za wybieranie konkretnych danych z pliku JSON i umieszczanie ich w kolekcji.
- PriceReductionCalculator - klasa dostarczająca statyczną metodę pozwalająca na wyliczenie różnicy starej i nowej ceny produktu.
- SplashActivity - klasa, która ma za zadanie ukrycie bezczynności aplikacji w trakcie pobierania danych z internetu.
- ***Fragment - fragmenty aplikacji ładowane przy jej starcie.
- GetJSONTask - klasa będąca zdarzeniem asynchronicznym w celu pobrania danych o promocji w formacie JSON
- DownloadImageTask - klasa będąca zdarzeniem asynchronicznym w celu pobrania obrazka ze sklepu
- MainActivity - klasa, która ma za zadanie załadowanie wszystkich fragmentów aplikacji.

# 4. Zrzuty ekranu z aplikacji
<p align="center">
  <img src="http://i.imgur.com/jtrYEKB.png" width="270" height="480"/>
  <img src="http://i.imgur.com/fEyFZPR.png" width="270" height="480"/>
  <img src="http://i.imgur.com/CEf6UoZ.png" width="270" height="480"/>
  <img src="http://i.imgur.com/SWL00f9.png" width="270" height="480"/>
  <img src="http://i.imgur.com/xvpdjiA.png" width="270" height="480"/>
  <img src="http://i.imgur.com/ok1HrK8.png" width="270" height="480"/>
  <img src="http://i.imgur.com/1zvcVCC.png" width="270" height="480"/>
  <img src="http://i.imgur.com/5xC3821.png" width="270" height="480"/>
</p>

# 5. Dane autora
1. Mateusz Zając, numer albumu: 188810, grupa dziekańska: KrDZIs3012Io, studia dzienne
