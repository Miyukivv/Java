package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Country {
    private final String name;

    private static String path1;
    private static String path2;

    public Country(String name) {
        this.name = name;
    }
    //set - mutator
    public String getName() {
        return name;
    }

    public static String getPath1() {
        return path1;
    }

    public static String getPath2() {
        return path2;
    }

    public static void setFiles(String path1, String path2) throws FileNotFoundException {

        File file1 = new File(path1);
        File file2= new File(path2);

        if (file1.exists()){
            Country.path1=path1;
            System.out.println("File1 exist\n");
        }
        else{
            throw new FileNotFoundException(path1);
        }

        if (file2.exists()){
            Country.path2=path2;
            System.out.println("File2 exist\n");
        }
        else{
            throw new FileNotFoundException(path1);
        }
    }

    public static Country fromCsv(String nameOfCountry) throws IOException, CountryNotFoundException {

        File file1 = new File(getPath1());
        File file2= new File(getPath2());

        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String lineWithCountries = br.readLine();
            CountryColumns countryColums=Country.getCountryColumns(lineWithCountries, nameOfCountry);
            String provinces=br.readLine();
            String line=br.readLine();

            if (countryColums.columnCount==1){
                CountryWithoutProvinces countryWithoutProvinces=new CountryWithoutProvinces(nameOfCountry);

                while (line!=null){
                    String[] partsOfLine = line.split(";");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
                    LocalDate actualDate = LocalDate.parse(partsOfLine[0], formatter);

                    int [] actualValueOfconfirmedCasesAndDeaths=countryWithoutProvinces.howManyConfirmedCasesAndDeaths(path1,path2,actualDate);

                    countryWithoutProvinces.addDailyStatistic(actualDate, actualValueOfconfirmedCasesAndDeaths[0],actualValueOfconfirmedCasesAndDeaths[1]);
                    line=br.readLine();
                }

                return countryWithoutProvinces;
            } else {
                ArrayList<Country> provincesList=new ArrayList<>();
                List<String> countries=Arrays.asList(lineWithCountries.split(";"));

                List<String> splitedProvinces = List.of(provinces.split(";"));

                for (int i=0; i<splitedProvinces.size(); i++){
                    if (splitedProvinces.get(i).equals(nameOfCountry)){
                        provincesList.add(new CountryWithoutProvinces((splitedProvinces.get(i))));
                    }
                }

                CountryWithProvinces countryWithProvinces=new CountryWithProvinces(nameOfCountry, provincesList);

                while (line!=null){
                    String[] partsOfLine = line.split(";");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
                    LocalDate actualDate = LocalDate.parse(partsOfLine[0], formatter);
                    line=br.readLine();
                }

                return new CountryWithProvinces(nameOfCountry, null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class CountryColumns{
        public final int firstColumnIndex;
        public final int columnCount;

        private CountryColumns(int firstColumnIndex, int columnCount) {
            this.firstColumnIndex = firstColumnIndex;
            this.columnCount = columnCount;
        }

    }
        private static CountryColumns getCountryColumns(String row, String nameOfCountry) throws CountryNotFoundException {
        String[] partsOfCountries = row.split(";");
        List <String> countriesList = Arrays.asList(partsOfCountries);

        int indexOfCountry=countriesList.indexOf(nameOfCountry);

        if (indexOfCountry == -1){
            throw new CountryNotFoundException(nameOfCountry);
        }

        int lastIndex = countriesList.lastIndexOf(nameOfCountry);
        int numberOfColumnForCountry=lastIndex-indexOfCountry+1;
        return new CountryColumns(indexOfCountry, numberOfColumnForCountry);
    }
    private static ArrayList<LocalDate> dates=new ArrayList<>();
    private static ArrayList<Integer> confirmedCases=new ArrayList<>();
    private static ArrayList<Integer> deaths =new ArrayList<>();
    public void addDailyStatistic(LocalDate data, int confirmedCase, int death){
        dates.add(data);
        confirmedCases.add(confirmedCase);
        deaths.add(death);
    }



    public abstract int getConfirmedCases(LocalDate date);

    public abstract int getDeaths(LocalDate date);


    public static List<Country> sortByDeaths(List<Country> listOfCountry, LocalDate date1, LocalDate date2){
        return listOfCountry.stream()
                .sorted(Comparator.comparingInt(c -> getTotalDeaths(c, date1, date2)))
                .collect(Collectors.toList());
    }

    private static int getTotalDeaths(Country country, LocalDate startDate, LocalDate endDate) {
        int totalDeaths = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) { //Mamy początkową datę i za każdym razem dodajemy dzień
            int deaths = country.getDeaths(date);
            if (deaths > 0) {
                totalDeaths += deaths;
            }
        }
        return totalDeaths;
    }

    private static Map<LocalDate, Integer> totalPerDate(String path) {
        Map<LocalDate, Integer> map = new HashMap<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path.toString()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
            String line = bf.readLine();
            line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                List<String> split = Arrays.asList(line.split(";"));
                Integer sum = split.stream().skip(1).mapToInt(Integer::parseInt).sum();
                map.put(LocalDate.parse(split.get(0), formatter), sum);
            }
            bf.close();
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToDataFile(String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()));
            Map<LocalDate, Integer> mapOfDeaths = totalPerDate(path1);
            Map<LocalDate, Integer> mapOfInfections = totalPerDate(path2);

            for (Map.Entry<LocalDate, Integer> entry : mapOfDeaths.entrySet()) {
                bw.write(entry.getKey().format(DateTimeFormatter.ofPattern("d.M.yy")));
                bw.write("\t");
                bw.write(entry.getValue().toString());
                bw.write("\t");
                bw.write(mapOfInfections.getOrDefault(entry.getKey(), 0).toString()); // GetOrDefault handles missing keys
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}