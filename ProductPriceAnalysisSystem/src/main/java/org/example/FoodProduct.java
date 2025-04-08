package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class FoodProduct extends Product{
    private String name;
    private Map<String, Double[]> provincesWithPrices;

    private FoodProduct(String name, String nameOfFile, Map<String, Double[]> provincesWithPrices) {
        super(name,nameOfFile);
        this.provincesWithPrices=provincesWithPrices;

    }

    public double getPrice(int year, int month, String province) {
        if (year<2010  || (year>=2022 && month>3)){
            throw new IndexOutOfBoundsException(String.format("Wrong date: %d %d\n",year,month));
        }

        if (!provincesWithPrices.containsKey(province)){
            throw new IndexOutOfBoundsException(String.format("Wrong province: %s\n",province));
        }

        String convertedDate = convertToRomanDate(year, month);
        String line;

        Path nameOfFilePath = Path.of(getNameOfFile());
        try {
            Scanner scanner = new Scanner(nameOfFilePath);
            line = scanner.nextLine();
            line = scanner.nextLine();
            scanner.close();

            String partsOfLine[] = line.split(";");
            List<String> partsOfLineList = Arrays.asList(partsOfLine);
            int indexOfDate = partsOfLineList.indexOf(convertedDate);


            Double[] prices = provincesWithPrices.get(province);

            return prices[indexOfDate - 1];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public double getPrice(int year, int month) {
        if (year<2010  || (year>=2022 && month>3)){
            throw new IndexOutOfBoundsException(String.format("Wrong date: %d %d\n",year,month));
        }
        String convertedDate = convertToRomanDate(year, month);
        String line;

        Path nameOfFilePath = Path.of(getNameOfFile());
        int indexOfDate=-1;
        try {
            Scanner scanner = new Scanner(nameOfFilePath);
            line = scanner.nextLine();
            line = scanner.nextLine();
            scanner.close();

            String partsOfLine[] = line.split(";");
            List<String> partsOfLineList = Arrays.asList(partsOfLine);
            indexOfDate = partsOfLineList.indexOf(convertedDate);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double valueOfPrices=0;
        for (String key : provincesWithPrices.keySet()){
            valueOfPrices+=provincesWithPrices.get(key)[indexOfDate-1];
        }
        return valueOfPrices/provincesWithPrices.size();
    }
    public static FoodProduct fromCsv(Path path){

        String name;

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();
            scanner.nextLine();
            Map<String, Double []> provincesAndPrices = new HashMap<>();
            String line;
            while (scanner.hasNextLine()){
                line=scanner.nextLine();
                String partsOfLine[]=line.split(";");

                String pricesAsString[]= Arrays.copyOfRange(partsOfLine, 1, partsOfLine.length);
                Double prices[]=Arrays.stream(pricesAsString).map(value -> value.replace(",",".")).map(Double::valueOf).toArray(Double []::new);
                provincesAndPrices.put(partsOfLine[0],prices);
            }
            scanner.close();

            return new FoodProduct(name, "src/main/resources/food/"+path.getFileName().toString(),provincesAndPrices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}