package com.example.pocs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FileWriteInvoker {

    public static void main(String[] args) throws InterruptedException, ParseException {

        String a  = "UNLOAD-KAUSTAV-UNLOAD_PERFORMANCE_TEST Async Object Distributor  (0)";
        String b = "Distributor" + "  (0)";

        System.out.println(a.endsWith(b));


//        List<FileWriter> futureList = new ArrayList<>();
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
//
//        for (int i = 1; i <= 3; i++) {
//            String destination = "C:\\DBMI\\DataMappingTest\\" + "FileWriter" + i;
//            FileWriter fileWriter = new FileWriter(destination, queue);
//            executorService.submit(fileWriter);
//            futureList.add(fileWriter);
//        }
//
//        for (int i = 1; i <= 50; i++) {
//            try {
//                queue.put("Hey I am here " + Math.random());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//        Thread.sleep(5000);
//        System.out.println("Stopping all the file writers");
//
//        for (FileWriter fileWriter : futureList) {
//            fileWriter.shutdown = true;
//        }
//        executorService.shutdown();


//        // 5 paritions -> 2
        calculateTimeDiffInMinAndSec("2023-11-21 03:48:44,048", "2023-11-21 03:59:18,314");
//        // 5 paritions -> 3
//        calculateTimeDiffInMinAndSec("2023-10-19 12:28:18,203", "2023-10-19 12:40:56,478");
//        // 5 paritions -> 4
//        calculateTimeDiffInMinAndSec("2023-10-19 13:40:18,387", "2023-10-19 13:52:27,496");
//        calculateTimeDiffInMinAndSec("2023-10-19 14:20:30,934", "2023-10-19 14:32:40,025");
//
//        calculateTimeDiffInMinAndSec("2023-10-19 13:57:01,515", "2023-10-19 14:09:16,456");
//        calculateTimeDiffInMinAndSec("2023-10-19 14:54:46,732", "2023-10-19 15:03:52,280");
//        calculateTimeDiffInMinAndSec("2023-10-19 15:05:29,280", "2023-10-19 15:14:22,206");
//        calculateTimeDiffInMinAndSec("2023-10-19 15:47:24,202", "2023-10-19 15:55:36,074");

//        String originalString = "file:///home/absharma/mrel/apps/Database_Ingestion/data/tasks/OracleSnowFlakeUnloadPerfOptimisation_98755.UNLOAD-KAUSTAV-UNLOAD_PERFORMANCE_TEST-7d32411c.W-TMP/UNLOAD_PERFORMANCE_TEST/UNLOAD_PERFORMANCE_TEST_20231103_0741581322118464937.csv";
//
//        // Use a regular expression to match everything after the last "/"
//        String pattern = "/[^/]*$";
//        String replacement = "/*"; // 30 asterisks
//
//        String modifiedString = originalString.replaceAll(pattern, replacement);
//        System.out.println(modifiedString);

    }

    private static void calculateTimeDiffInMinAndSec(String date1,String date2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);

        Duration duration = Duration.between(dateTime1,dateTime2);
        System.out.println(duration.toMinutes() + " minutes and "+duration.toSecondsPart()+" seconds");
    }
}
