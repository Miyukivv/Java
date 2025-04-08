package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("<CAUSE-OF-DEATH ANALYZER>\n");

        System.out.println("<1> Reading a single record (A02.1) from zgony.csv");
        DeathCauseStatistic singleStat = DeathCauseStatistic.fromCsvLine("src/main/resources/zgony.csv", "A02.1");
        System.out.println("Result: " + singleStat);
        System.out.println();

        System.out.println("<2> Checking bracket for age 67");
        System.out.println("Bracket info: " + singleStat.getBracketForAge(67));
        System.out.println();

        System.out.println("<3> Populating DeathCauseStatisticList from zgony.csv");
        DeathCauseStatisticList stats = new DeathCauseStatisticList();
        stats.repopulate("src/main/resources/zgony.csv");
        System.out.println("Loaded statistics count: " + stats.getStats().size());
        System.out.println();

        System.out.println("<4> Displaying 4 most deadly diseases for age 37");
        for (int i = 0; i < 4; i++){
            System.out.println(" - " + stats.mostDeadlyDiseases(37, 4).get(i));
        }
        System.out.println();

        System.out.println("<5> Testing description lookup (Memory-Optimized)");
        ICDCodeTabular testMemory = new ICDCodeTabularOptimizedForMemory();
        System.out.println("A18.4 --> " + testMemory.getDescription("A18.4"));
        System.out.println("A02.1 --> " + testMemory.getDescription("A02.1"));
        System.out.println();

        System.out.println("<6> Testing description lookup (Time-Optimized)");
        ICDCodeTabular testForTime = new ICDCodeTabularOptimizedForTime();
        System.out.println("A18.4 --> " + testForTime.getDescription("A18.4"));
        System.out.println();

        System.out.println("<7> Performance comparison between 'Memory' and 'Time' approaches");

        long startTimeMemory = System.nanoTime();
        System.out.println("   A18.4 --> " + testMemory.getDescription("A18.4"));
        long endTimeMemory = System.nanoTime();
        System.out.println("   [Memory] Elapsed time (ns): " + (endTimeMemory - startTimeMemory));

        long startTimeTime = System.nanoTime();
        System.out.println("   A18.4 --> " + testForTime.getDescription("A18.4"));
        long endTimeTime = System.nanoTime();
        System.out.println("  [Time] Elapsed time (ns): " + (endTimeTime - startTimeTime));
    }
}
