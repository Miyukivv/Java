package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NonFoodProduct extends Product{
    private Double[] prices;


    private NonFoodProduct(String name, String nameOfFile, Double[] prices) {
        super(name, nameOfFile);
        this.prices = prices;
    }

    public static NonFoodProduct fromCsv(Path path) {
        String name;
        Double[] prices;

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();
            scanner.nextLine();
            prices = Arrays.stream(scanner.nextLine().split(";"))
                    .map(value -> value.replace(",","."))
                    .map(Double::valueOf)
                    .toArray(Double[]::new);
            scanner.close();

            return new NonFoodProduct(name, "src/main/resources/nonfood/"+path.getFileName().toString(), prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public double getPrice(int year, int month) {
        if (year<2010  || (year>=2022 && month>3)){
            throw new IndexOutOfBoundsException(String.format("Wrong date: %d %d",year,month));
        }
        String line;
        String convertedDate=convertToRomanDate(year,month);
        Path nameOfFilePath=Path.of(getNameOfFile());
        try {
            Scanner scanner = new Scanner(nameOfFilePath);
            line=scanner.nextLine();
            line = scanner.nextLine();
            scanner.close();

            String partsOfLine[] = line.split(";");
            List<String> partsOfLineList = Arrays.asList(partsOfLine);

            int indexOfDate = partsOfLineList.indexOf(convertedDate);

            return prices[indexOfDate];
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}