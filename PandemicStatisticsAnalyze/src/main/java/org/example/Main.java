package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Country.setFiles("src/main/resources/confirmed_cases.csv", "src/main/resources/deaths.csv");

            String[] countriesToAnalyze = {
                    "Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
                    "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Bahamas"
            };

            List<Country> countriesList = new ArrayList<>();

            for (String countryName : countriesToAnalyze) {
                Country country = Country.fromCsv(countryName);

                if (country instanceof CountryWithoutProvinces) {
                    System.out.println(countryName + " to kraj bez prowincji.");
                } else {
                    System.out.println(countryName + " to kraj z prowincjami.");
                }

                countriesList.add(country);
            }
            LocalDate date = LocalDate.of(2020, 2, 24);

            for (Country country : countriesList) {
                CountryWithoutProvinces countryWithoutProvinces = new CountryWithoutProvinces(country.getName());
                int[] countryStatistics = countryWithoutProvinces.howManyConfirmedCasesAndDeaths(Country.getPath1(), Country.getPath2(), date);

                System.out.println("Statystyki dla " + country.getName() + " na dzień " + date + ":");
                System.out.println("Liczba potwierdzonych przypadków: " + countryStatistics[0]);
                System.out.println("Liczba zgonów: " + countryStatistics[1]);
                System.out.println();
            }

            LocalDate startDate = LocalDate.of(2020, 1, 1);
            LocalDate endDate = LocalDate.of(2020, 3, 1);

            List<Country> sortedCountries = Country.sortByDeaths(countriesList, startDate, endDate);

            System.out.println("\nKraje posortowane według liczby zgonów (od najwyższego):");
            for (Country country : sortedCountries) {
                System.out.println(country.getName() + " - Zgony: " + country.getDeaths(endDate));
            }

        } catch (FileNotFoundException e) {
            System.err.println("Błąd: Plik nie został znaleziony. Upewnij się, że pliki CSV są w odpowiednim folderze.");
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
        } catch (CountryNotFoundException e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}