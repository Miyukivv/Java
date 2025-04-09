# Java 
Wszystkie projekty zamieszczone w niniejszym repozytorium zostały zrealizowane w ramach zajęć dydaktycznych na kierunku Informatyka.
(All projects included in this repository were implemented as part of teaching classes at the Computer Science faculty.)

### Spis projektów(list of projects)
  - [ICD10 Cause of Death](ICD10CauseOfDeath/)
  - [MusicShelf](MusicShelf/)
  - [Pandemic Statistic Analyze](PandemicStatisticsAnalyze/)
  - [Product Price Analysis System](ProductPriceAnalysisSystem/)
  - [TreeMinder](TreeMinder/)
  - [VectorFlow](VectorFlow/)
  - [ParallelImageEditor](ParallelImageEditor/)
  - [PixelGuardApplication](PixelGuardApplication/)
  - [FilterWords](FilterWords/)


 PixelGuardApplication

## Opisy projektów (project descriptions)
 ### [ICD10 Cause of Death](ICD10CauseOfDeath/)
 
ICD10CauseOfDeath to mały projekt demonstrujący pracę z danymi o zgonach w podziale na grupy wiekowe i choroby (przy użyciu kodów ICD10).
Głównym założeniem jest wczytanie danych z pliku CSV, stworzenie obiektów reprezentujących statystyki umieralności, 
a następnie umożliwienie analizy — np. wskazanie chorób powodujących najwięcej zgonów w wybranej grupie wiekowej. 
Osobne moduły odpowiadają za dostarczanie opisów chorób z pliku ICD10: jeden ładuje je do pamięci i udostępnia szybko, 
drugi zaś przy każdym wywołaniu wczytuje dane z dysku, oszczędzając pamięć. 

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
ICD10CauseOfDeath is a small  project demonstrating the handling and analysis of mortality data, categorized by age groups and diseases (using ICD10 codes). 
The primary objective is to read data from a CSV file, create objects representing mortality statistics, 
and enable subsequent analysis—such as identifying the diseases causing the highest number of deaths within a selected age group.
The project includes separate modules responsible for providing disease descriptions based on the ICD10 codes: one loads descriptions into memory for quick access, 
while the other reads data from disk each time a description is requested, conserving memory. 

 ### [MusicShelf](MusicShelf/)
 
MusicShelf to prosta aplikacja Java wykorzystująca bazę SQLite do zarządzania użytkownikami, piosenkami oraz playlistami. 
Umożliwia rejestrację i logowanie użytkowników, przechowywanie informacji o posiadanych utworach oraz tworzenie playlist z utworami wczytanymi z plików CSV oraz z bazy danych. 
Dodatkowo projekt posiada zestaw testów jednostkowych (JUnit), które sprawdzają poprawność operacji na playlistach, kontach użytkowników oraz utworach muzycznych. 

 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
MusicShelf is a simple Java application that uses an SQLite database to manage users, songs, and playlists.
It allows user registration and login, stores information about owned tracks, and enables the creation of playlists using songs loaded from CSV files and the database.
Additionally, the project includes a set of unit tests (JUnit) that verify the correctness of operations related to playlists, user accounts, and music tracks.
 
 ### [Pandemic Statistic Analyze](PandemicStatisticsAnalyze/)
 
Projekt PandemicStatisticsAnalyze umożliwia analizę danych dotyczących COVID-19 na poziomie krajów i ich prowincji. 
Korzysta z plików CSV zawierających dane o liczbie przypadków zakażeń oraz zgonów, a następnie przetwarza te informacje, 
aby dostarczyć szczegółowe statystyki według daty i regionu.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 The PandemicStatisticsAnalyze project enables the analysis of COVID-19 data at the country and provincial levels. 
 It utilizes CSV files containing information on infection cases and deaths, processing this data to provide detailed statistics by date and region.
 
 ### [Product Price Analysis System](ProductPriceAnalysisSystem/)
 
 ProductPriceAnalysisSystem służy do analizy cen produktów na podstawie historycznych danych w formacie CSV. 
 Pozwala wczytywać informacje o towarach spożywczych i niespożywczych z różnych regionów i okresów, 
 a następnie obliczać łączną wartość koszyka zakupowego oraz zmiany cen w czasie. Dodatkowo umożliwia szacowanie inflacji, 
 wyszukiwanie produktów po fragmencie nazwy oraz obsługę wyjątków związanych z błędnymi lub niejednoznacznymi danymi. 

 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
The project enables the analysis of product prices based on historical data in CSV format. 
It allows users to load information about food and non-food items from various regions and time periods, 
and subsequently calculates the total value of a shopping basket as well as price fluctuations over time. 
Additionally, it provides features for estimating inflation, searching products by partial name matches, 
and handling exceptions caused by incorrect or ambiguous data.

 ### [TreeMinder](TreeMinder/)
 
TreeMinder to mały projekt, który wczytuje dane genealogiczne z pliku CSV i umożliwia ich analizę. 
Pozwala sprawdzić informacje o osobach, takie jak wiek, status życia, rodzice, a także oblicza średnią długość życia zmarłych. 
Program wykrywa błędy w danych, np. niepoprawne daty czy zbyt młody wiek rodzica. 
Dodatkowo generuje proste diagramy relacji rodzinnych.

 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
TreeMinder is a small project that reads genealogical data from a CSV file and allows for its analysis.
It provides information about individuals, such as age, life status, and parents, and also calculates the average lifespan of deceased people.
The program detects data issues, such as incorrect dates or parents being too young. 
Additionally, it generates simple diagrams of family relationships.
 
 ### [VectorFlow](VectorFlow/)
 
 VectorFlow to mały projekt demonstrujący tworzenie grafiki wektorowej w formacie SVG z wykorzystaniem wzorców projektowych (m.in. Decorator, Builder i Singleton). 
 Głównym założeniem jest, by użytkownik definiował proste kształty (np. wielokąty, elipsy czy odcinki), 
 a następnie „opakowywał” je dekoratorami zapewniającymi dodatkowe funkcjonalności, takie jak cienie (DropShadow), 
 wypełnienia gradientowe (GradientFill) czy transformacje (skalowanie, obrót, przesunięcie). 
 Kody SVG poszczególnych figur są generowane przez klasy implementujące interfejs Shape, a całość umieszczana jest w centralnym obiekcie SvgScene (Singleton), 
 który zarządza kolekcją kształtów i umożliwia zapis gotowej kompozycji do plików .svg lub .html. 


 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 VectorFlow is a small demonstration project showcasing how to create vector graphics in SVG format using design patterns (such as Decorator, Builder, and Singleton). 
 The main idea is for the user to define simple shapes (like polygons, ellipses, or line segments), and then wrap them in decorators that add additional visual effects, 
 such as drop shadows, gradient fills, or transformations (scaling, rotation, translation).
 SVG code for each shape is generated by classes implementing the Shape interface, and all shapes are managed by a central object called SvgScene (implemented as a Singleton). 
 This object maintains the collection of shapes and enables exporting the entire composition to .svg or .html files. 

### [ParallelImageEditor](ParallelImageEditor/) 

  ParallelImageEditor to niewielki projekt demonstracyjny, którego celem jest pokazanie przetwarzania obrazów w języku Java z wykorzystaniem różnych podejść do programowania współbieżnego. 
  Główne funkcjonalności to rozjaśnianie obrazów oraz generowanie histogramów jasności poszczególnych kanałów RGB. 
  Program wczytuje obraz z pliku, modyfikuje jego jasność (w trybie jednowątkowym, wielowątkowym oraz z użyciem puli wątków), 
  a następnie zapisuje wynik do nowego pliku. Dodatkowym elementem jest analiza histogramów — projekt pozwala wybrać kanał kolorystyczny (czerwony, zielony lub niebieski), 
  obliczyć rozkład pikseli w tym kanale i wygenerować obraz graficzny przedstawiający histogram.
  
 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  ParallelImageEditor is a small demonstration project designed to showcase image processing in Java using various approaches to concurrent programming. 
  Its core functionalities include increasing image brightness and generating histograms for the intensity distribution of individual RGB channels. 
  The program reads an image from a file, adjusts its brightness (using single-threaded, multi-threaded, and thread pool techniques), and saves the modified image to a new file.
  An additional feature is histogram analysis — the project allows the user to select a color channel (red, green, or blue), 
  calculate the distribution of pixel values within that channel, and generate a graphical representation of the histogram.

### [PixelGuardApplication](PixelGuardApplication/)

PixelGuard to aplikacja serwerowa napisana w Javie z użyciem Spring Boot, która umożliwia użytkownikom kolorowanie wspólnego obrazu pikselowego za pomocą zapytań HTTP. 
Każdy użytkownik może zarejestrować tymczasowy token (ważny przez 5 minut), który uprawnia go do ustawienia koloru jednego piksela na płótnie, wskazując współrzędne x i y oraz kolor w formacie HEX. 
Zmienione piksele są zapisywane w bazie danych, a obraz generowany jest dynamicznie na ich podstawie.

Tokeny rejestruje się poprzez wysłanie zapytania POST na /register, np. za pomocą Postmana lub curl. Aby ustawić piksel, należy wysłać POST na /pixel z parametrami id, x, y i hexColor. 
Istnieje też konsola administracyjna działająca na porcie 5000, w której po zalogowaniu (login: admin, hasło: admin123) można wykonywać polecenia, takie jak ban <id>, 
aby unieważnić token i usunąć wszystkie związane z nim dane. Obraz zostaje wtedy automatycznie odświeżony. 
Użytkownicy mogą również podejrzeć aktywne tokeny przez endpoint /tokens.

 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

PixelGuardApplication is a server-side application written in Java using Spring Boot that allows users to color a shared pixel-based image through HTTP requests. 
Each user can register a temporary token (valid for 5 minutes), which grants permission to set the color of a single pixel on the canvas by specifying the x and y coordinates and a HEX color value. 
The modified pixels are stored in a database, and the image is generated dynamically based on this data.

Tokens are registered by sending a POST request to /register, for example using Postman or curl. To set a pixel, a POST request must be sent to /pixel with the parameters id, x, y, and hexColor. 
There is also an admin console available on port 5000, where after logging in (login: admin, password: admin123), commands such as ban <id> can be executed to invalidate a token and remove all data associated with it. 
The image is then automatically refreshed. Users can also view all active tokens via the /tokens endpoint.

### [FilterWords](FilterWords/)

FilterWords to projekt, którego celem jest przesyłanie słów z serwera do klientów w czasie rzeczywistym. 
Serwer losuje słowa z pliku tekstowego i co kilka sekund wysyła je do wszystkich połączonych klientów. 
Po stronie klienta słowa są odbierane, oznaczane czasem przyjęcia, a następnie filtrowane — domyślnie przepuszczane są tylko te zaczynające się na litery A–E.
Klient zapisuje zarówno zaakceptowane, jak i odrzucone słowa, pozwala na ich podgląd w konsolowym interfejsie oraz umożliwia dynamiczną zmianę filtra w trakcie działania programu.

 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

FilterWords is a project designed to transmit words from a server to clients in real time. 
The server randomly selects words from a text file and sends them to all connected clients every few seconds. 
On the client side, the words are received, timestamped upon arrival, and then filtered — by default, only those starting with letters A–E are accepted. 
The client stores both accepted and rejected words, allows users to view them through a console interface, and enables dynamic changes to the filter while the program is running.


