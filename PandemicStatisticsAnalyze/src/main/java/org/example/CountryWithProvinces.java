package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class CountryWithProvinces extends Country{

    private ArrayList<Country> provinces;

    public CountryWithProvinces(String name, ArrayList<Country> provinces) {
        super(name);
        if (provinces == null){
            this.provinces=new ArrayList<>();
        }
        else {
            this.provinces=provinces;
        }
    }

    public int provincesGet(String path) {

        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String[] partsOfCountries = line.split(";");

            line = br.readLine();

            String[] partsOfProvinces = line.split(";");

            for (int i = 0; i < partsOfCountries.length; i++) {
                if (partsOfCountries[i].equals(getName())) {
                    if (partsOfProvinces[i] != null) {
                        CountryWithoutProvinces province = new CountryWithoutProvinces(partsOfProvinces[i]);
                        provinces.add(province);
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public int getConfirmedCases(LocalDate date) {
        int totalConfirmedCases=0;
        for (int i=0; i<provinces.size(); i++){
            totalConfirmedCases+=provinces.get(i).getConfirmedCases(date);
        }
        return totalConfirmedCases;
    }

    @Override
    public int getDeaths(LocalDate date) {
        int totalDeaths=0;
        for (int i=0; i<provinces.size(); i++){
            totalDeaths+=provinces.get(i).getDeaths(date);
        }
        return totalDeaths;
    }
}