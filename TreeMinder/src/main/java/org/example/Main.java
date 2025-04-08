package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("<< Wczytywanie danych rodzinnych z pliku CSV >>");
        List<Person> people = Person.fromCsvFile("src/main/resources/family.csv");
        System.out.println("Liczba wczytanych osób: " + people.size());

        System.out.println("\n<< Podstawowy przegląd >>");
        people.forEach(person -> {
            System.out.println(person.getName()
                    + " | Data ur.: " + bezpiecznaData(person.getBirthDate())
                    + " | Data śm.: " + bezpiecznaData(person.getDeathDate())
                    + " | Czy żyje: " + (person.isAlive() ? "tak" : "nie"));
        });

        long aliveCount = people.stream().filter(Person::isAlive).count();
        long deceasedCount = people.size() - aliveCount;
        System.out.println("\n<< Analiza: osoby żyjące vs. zmarłe >>");
        System.out.println("Żyjące: " + aliveCount + ", Zmarłe: " + deceasedCount);

        System.out.println("\n<< Najstarsza żyjąca osoba >>");
        Person oldestLiving = Person.getOldestLivingPerson(people);
        if (oldestLiving != null) {
            System.out.println("Najstarsza żyjąca osoba: "
                    + oldestLiving.getName()
                    + " (ur. " + bezpiecznaData(oldestLiving.getBirthDate()) + ")");
        } else {
            System.out.println("Brak żyjących osób w danych.");
        }

        List<Person> deceasedPeople = people.stream()
                .filter(p -> !p.isAlive() && p.getBirthDate() != null && p.getDeathDate() != null)
                .collect(Collectors.toList());

        double averageLife = deceasedPeople.stream()
                .mapToInt(Person::getLifeLength)
                .average()
                .orElse(0);
        System.out.println("\n<< Średnia długość życia osób zmarłych >>");
        if (!deceasedPeople.isEmpty()) {
            System.out.printf("Średnia długość życia (dla zmarłych): %.2f lat%n", averageLife);
        } else {
            System.out.println("Brak zmarłych osób z pełnymi danymi (data urodzenia i śmierci).");
        }


        List<Person> withBothParents = people.stream()
                .filter(p -> p.getMother() != null && p.getFather() != null)
                .collect(Collectors.toList());

        System.out.println("\n<< Osoby posiadające oboje rodziców w danych >>");
        if (withBothParents.isEmpty()) {
            System.out.println("Żadna osoba nie ma zdefiniowanych obojga rodziców w pliku.");
        } else {
            withBothParents.forEach(p -> {
                System.out.println("- " + p.getName()
                        + " | Matka: " + p.getMother().getName()
                        + " | Ojciec: " + p.getFather().getName());
            });
        }
    }
    private static String bezpiecznaData(LocalDate date) {
        return (date == null) ? "N/A" : date.toString();
    }
}
