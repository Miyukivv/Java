package com.example.klient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.Scanner;


@SpringBootApplication
public class KlientApplication {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("<Klient>");
		System.out.println("Dostępne filtry:");
		System.out.println("1. Słowa zaczynające się na A-E");
		System.out.println("2. Wszystkie słowa (bez filtra)");
		System.out.print("Wybierz filtr: ");

		int choice = scanner.nextInt();
		scanner.nextLine();

		WordFilter filter = switch (choice) {
			case 1 -> new FirstLetterIsABCDE();
			case 2 -> word -> true;
			default -> {
				System.out.println("Nieznana opcja. Używam filtra domyślnego (A-E)");
				yield new FirstLetterIsABCDE();
			}
		};

		WordContainer wordContainer = new WordContainer(filter);

		try {
			ConnectionThread connectionThread = new ConnectionThread("localhost", 5000, wordContainer);
			connectionThread.start();

			while (true) {
				System.out.println("\n<Menu>");
				System.out.println("1. Pokaż słowa dodane");
				System.out.println("2. Pokaż słowa odrzucone");
				System.out.println("3. Licznik słów: " + wordContainer.getWordCountLabel());
				System.out.println("4. Wyjście");
				System.out.println("5. Zmień filtr");
				System.out.print("Wybierz: ");

				int opt = scanner.nextInt();
				scanner.nextLine();

				switch (opt) {
					case 1 -> System.out.println(wordContainer);
					case 2 -> System.out.println(wordContainer.getWordsNotAddedString());
					case 3 -> System.out.println("Licznik słów dodanych: " + wordContainer.getWordCountLabel());
					case 4 -> {
						System.out.println("Zamykam program...");
						System.exit(0);
					}
					case 5 -> changeFilter(scanner, wordContainer);
					default -> System.out.println("Nieznana opcja.");
				}
			}

		} catch (IOException e) {
			System.err.println("Błąd połączenia z serwerem: " + e.getMessage());
		}
	}

	private static void changeFilter(Scanner scanner, WordContainer wordContainer) {
		System.out.println("\n=== ZMIANA FILTRA ===");
		System.out.println("1. A-E");
		System.out.println("2. Wszystkie słowa");
		System.out.print("Wybierz: ");
		int opt = scanner.nextInt();
		scanner.nextLine();
		if (opt == 1)
			wordContainer.setFilter(new FirstLetterIsABCDE());
		else
			wordContainer.setFilter(word -> true);
		System.out.println("Zmieniono filtr.");
	}

	private static WordFilter createFilter() {
		return new FirstLetterIsABCDE();

	}

}