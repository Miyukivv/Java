package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class DeathCauseStatisticList{
    private List<DeathCauseStatistic> stats=new ArrayList<>();

    public List<DeathCauseStatistic> getStats() {
        return stats;
    }

    public void repopulate(String path) {
        Function<String, String> removeTab = s -> s.replace(" ", "");
        Function<String, Integer> toZero = s -> "-".equals(s) ? 0 : Integer.parseInt(s);

        stats.clear();
        File file=new File(path);

        try (BufferedReader br=new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            line = br.readLine();
            line=br.readLine();

            while (line != null) {
                line = removeTab.apply(line);
                String partsOfLine[] = line.split(",");

                int deaths[] = new int[partsOfLine.length - 2];

                for (int i = 0; i < partsOfLine.length - 2; i++) {
                    deaths[i] = toZero.apply(partsOfLine[i + 2]);
                }
                int totalDeaths = toZero.apply(partsOfLine[1]);
                stats.add(new DeathCauseStatistic(partsOfLine[0], deaths, totalDeaths));
                line = br.readLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n){

        int index=age/5;
        if (n<stats.size()){

            List<DeathCauseStatistic> results=new ArrayList<>(stats);

            results.sort((DeathCauseStatistic disease1, DeathCauseStatistic disease2)->Integer.compare(
                    disease1.getBracketForAge(age).getDeathCount(),
                    disease2.getBracketForAge(age).getDeathCount()
            ));
            Collections.reverse(results);

            return results.subList(0,n);
        }
        return null;
    }

}