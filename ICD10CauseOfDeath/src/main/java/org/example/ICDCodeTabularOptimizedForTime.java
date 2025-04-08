package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular{
    private Map<String, String> illnessAndDescription= new HashMap<>();


    public ICDCodeTabularOptimizedForTime() {

        Path path=Path.of("src/main/resources/icd10.txt");
        try (Stream<String> lines= Files.lines(path)) { //odczytujemy wszystkie linie

            lines.skip(88)
                    .map(String::trim)
                    .filter(s ->s.matches("[A-Z][0-9]{2}.*")) //od A-Z jedna litera, od 0 do 9 cyfra razy 2. *->reszta opisu (dowolna sekwencja znaków)
                    .map(s ->s.split(" ",2)) //dla każdej sekwencji wywołujemy funkcje którą definiujemy w środku, dzieli nam stringa na dwa stringi, rozdzielnik to spacja
                    .forEach(strings -> illnessAndDescription.put(strings[0],strings[1])); //dla każdego przetworzonego wrzuca chorobę, gdzie kluczem jest nazwa, a tym drugim opis
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription(String codeOfDisease) throws IndexOutOfBoundsException {
        return illnessAndDescription.get(codeOfDisease);
    }

}