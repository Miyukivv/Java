# Java 
Wszystkie projekty zamieszczone w niniejszym repozytorium zostały zrealizowane w ramach zajęć dydaktycznych na kierunku Informatyka.
All projects included in this repository were implemented as part of teaching classes at the Computer Science faculty.

### Spis projektów(list of projects)
  - [ICD10 Cause of Death](ICD10CauseOfDeath/)
  - [MusicShelf](MusicShelf/)
  - [Pandemic Statistic Analyze](PandemicStatisticsAnalyze/)
  - [Product Price Analysis System](ProductPriceAnalysisSystem/)
  - [TreeMinder](TreeMinder/)
  - [VectorFlow](VectorFlow/)

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
