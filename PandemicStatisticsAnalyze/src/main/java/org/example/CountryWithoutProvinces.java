package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CountryWithoutProvinces extends Country{
    Map<LocalDate,DailyStatistics> stats=new HashMap<>();
    public CountryWithoutProvinces(String name) {
        super(name);
    }

    public int [] howManyConfirmedCasesAndDeaths(String path1, String path2, LocalDate date) throws CountryNotFoundException {

        int confirmedCasesAndDeaths[]=new int[2];
        confirmedCasesAndDeaths[0]=getValueForCountryAndDate(path1,date);
        confirmedCasesAndDeaths[1]=getValueForCountryAndDate(path2,date);
        return confirmedCasesAndDeaths;
    }

    private int getValueForCountryAndDate(String path, LocalDate date) throws CountryNotFoundException {
        File file=new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String[] partsOfCountries = line.split(";");
            List<String> countriesList = Arrays.asList(partsOfCountries);

            int indexOfCountry=countriesList.indexOf(getName());

            if (indexOfCountry == -1){
                throw new CountryNotFoundException(getName());
            }

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("M/dd/yy");
            String formattedDate =date.format(pattern);

            while (line!=null){

                String[] partsOfLine = line.split(";");

                if (partsOfLine[0].equals(formattedDate)){
                    return Integer.valueOf(partsOfLine[indexOfCountry]);
                }
                line = br.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getValueForProvincesAndDate(String path, LocalDate date) throws CountryNotFoundException {
        File file=new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();

            String[] partsOfCountries = line.split(";");

            line = br.readLine();

            List<String> countriesList = Arrays.asList(partsOfCountries);

            int indexOfCountry=countriesList.indexOf(getName());


            if (indexOfCountry == -1){
                throw new CountryNotFoundException(getName());
            }

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("M/dd/yy");
            String formattedDate =date.format(pattern);

            while (line!=null){

                String[] partsOfLine = line.split(";");

                if (partsOfLine[0].equals(formattedDate)){
                    return Integer.valueOf(partsOfLine[indexOfCountry]);
                }
                line = br.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public class DailyStatistics{
        private int illnesCases;
        private int deaths;

        public DailyStatistics(int illnesCases, int deaths) {
            this.illnesCases = illnesCases;
            this.deaths = deaths;
        }

        public int getIllnesCases() {
            return illnesCases;
        }

        public int getDeaths() {
            return deaths;
        }
    }

    public void addDailyStatistic(LocalDate date, int illnesCases, int deaths){
        stats.put(date, new DailyStatistics(illnesCases,deaths));
    }

    @Override
    public int getConfirmedCases(LocalDate date) {
        DailyStatistics statsForDate = stats.get(date);
        if (statsForDate == null) {
            return 0;
        }
        return statsForDate.illnesCases;
    }

    @Override
    public int getDeaths(LocalDate date) {

        DailyStatistics statsForDate = stats.get(date);
        if (statsForDate == null) {
            return 0;
        }
        return statsForDate.deaths;
    }
}
