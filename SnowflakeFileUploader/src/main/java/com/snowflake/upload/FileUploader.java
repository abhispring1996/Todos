package com.snowflake.upload;

import net.snowflake.client.jdbc.SnowflakeResultSetV1;
import net.snowflake.client.jdbc.internal.apache.commons.io.FilenameUtils;
import org.anarres.parallelgzip.ParallelGZIPEnvironment;
import org.anarres.parallelgzip.ParallelGZIPOutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileUploader {

    private static Connection connection;

    public static void main(String[] args) {
        String snowflakeAccount = "informatica.eu-central-1";
        String host = "snowflakecomputing.com";
        Integer port = 443;
        String username = "infa_dbmi_dev1";
        String password = "WyM4gGn1AZ6E";
        final String warehouse = "DBMI_WH1";
        final String database = "DBMI_DB1";
        String schemaName = "UK";
        String stageName = "stage";

//        args = new String[]{"C:\\SnowflakeUploadFiles\\OneChunkUpload\\Bulk/", "50"};

//        args = new String[]{"C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_10\\1/,C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_10\\2/,C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_10\\3/,C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_10\\4/,C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_10\\5/", "10"};
//
        args = new String[]{"C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\1/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\2/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\3/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\4/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\5/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\6/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\7/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\8/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\9/," +
                "C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\10/",
                "5"};

        String[] filesLocalPath = args[0].split(",");
        String parallelThreads = args[1];

        String connectString = String.format("jdbc:snowflake://%s.%s:%d", snowflakeAccount, host, port);
        System.out.println("************************* Upload Files Triggered *************************");
        try {
            connection = DriverManager.getConnection(connectString, username, password);

            String removeExistingData = String.format("REMOVE '@\"%s\".\"%s\"/OracleSnowFlakeUnloadPerfOptimisation_100119/LINEITEM_SF1/'", schemaName, stageName);
            String listStage = String.format("LIST '@\"%s\".\"%s\"/OracleSnowFlakeUnloadPerfOptimisation_100119/LINEITEM_SF1/'", schemaName, stageName);

            final String databaseQuery = String.format("use database \"%s\"", database);
            executeSql(databaseQuery);
            //create the target stage in certain schema
            final String stageQuery = String.format("create stage if not exists \"%s\".\"%s\" FILE_FORMAT=(type='csv' COMPRESSION=%s)", schemaName, stageName, "NONE");
            executeSql(stageQuery);
            executeListQuery(listStage);
            List<Future> futures = new ArrayList<>();
            ExecutorService threadPool = Executors.newFixedThreadPool(filesLocalPath.length);
//            int noOfIterations = 1;
//            ExecutorService threadPool = Executors.newFixedThreadPool(1);


            long startTime = System.currentTimeMillis();

            for (String localFilePath : filesLocalPath) {
                Uploader uploader = new Uploader(connection, localFilePath,
                        Integer.valueOf(parallelThreads), stageName, schemaName, false);
                futures.add(threadPool.submit(uploader));
            }


            for (Future future : futures) {
                while (true) {
                    if (future.isDone()) {
                        break;
                    }
                }
            }

            System.out.println("Total File Upload Time taken in ms : " + (System.currentTimeMillis() - startTime));

            threadPool.shutdown();

            executeListQuery(listStage);
            executeSql(removeExistingData);

            System.out.println("************************* Upload Files Done and Dusted *************************");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void zipFile(){

//        ParallelGZIPOutputStream outputStream = new ParallelGZIPOutputStream(null);

    }


    static class Uploader implements Callable<String> {

        Connection connection;
        String localPath;
        String schemaName;
        String stageName;
        Integer parallelThreads;
        boolean isBulkUpload;

        Uploader(Connection connection, String localPath,
                 Integer parallelThreads, String stageName,
                 String schemaName,
                 boolean isBulkUpload
        ) {
            this.connection = connection;
            this.localPath = localPath;
            this.parallelThreads = parallelThreads;
            this.schemaName = schemaName;
            this.stageName = stageName;
            this.isBulkUpload = isBulkUpload;
        }

        @Override
        public String call() throws Exception {
            long startTime = System.currentTimeMillis();
            upload(localPath, parallelThreads);
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("Total Bulk Time taken in ms by Thread : " + Thread.currentThread().getName() + " is " + endTime);
            return "DONE";
        }

        private void upload(String localPath, Integer parallelThreads) throws SQLException {
            if (isBulkUpload) {
                localPath = getBulkLoadPath(localPath);
            }
            String putQuery = getPutQuery(localPath, parallelThreads);
            executeUploadSql(putQuery);
        }

        private String getBulkLoadPath(String actualLocalFilePath) {
            String pattern = "/[^/]*$";
            String replacement = "/*";
            return actualLocalFilePath.replaceAll(pattern, replacement);
        }

        String getPutQuery(String localFilePath, Integer parallelThreads) {
            String compress = "AUTO_COMPRESS=false";
            String parallel = "";
            if (parallelThreads != 0) {
                parallel = String.format("PARALLEL=%d", parallelThreads);
            }
            String desiredFilePath = FilenameUtils.separatorsToUnix(localFilePath);

            return String.format("PUT 'file://%s' '@\"%s\".\"%s\"/OracleSnowFlakeUnloadPerfOptimisation_100119/LINEITEM_SF1' %s %s OVERWRITE=TRUE",
                    desiredFilePath, schemaName,
                    stageName, compress, parallel);
        }

        private void executeUploadSql(String sqlCommand) throws SQLException {
            SnowflakeResultSetV1 resultSet = (SnowflakeResultSetV1) executeSql(sqlCommand);
            final Map<String, Integer> rsColNames = getResultSetColumnNames(resultSet);
            if (resultSet.next()) {
                final String status = resultSet.getString(rsColNames.get("status"));
                System.out.println("File Upload status for PUT command : " + sqlCommand + " with queryId : " + resultSet.getQueryID() + " is : " + status);
            }
        }

        private ResultSet executeSql(String sqlCommand) throws SQLException {
            System.out.println("Executing Query : " + sqlCommand);
            return connection.createStatement().executeQuery(sqlCommand);
        }

        private Map<String, Integer> getResultSetColumnNames(ResultSet rsPutFile) throws SQLException {
            ResultSetMetaData rsMetaData = rsPutFile.getMetaData();
            final int columnsCount = rsMetaData.getColumnCount();
            Map<String, Integer> rsColNames = new HashMap<>();
            for (int colIndex = 1; colIndex <= columnsCount; colIndex++) {
                rsColNames.put(rsMetaData.getColumnName(colIndex), colIndex);
            }
            return rsColNames;
        }
    }

    private static ResultSet executeSql(String sqlCommand) throws SQLException {
        System.out.println("Executing Query : " + sqlCommand);
        return connection.createStatement().executeQuery(sqlCommand);
    }
//
//    private static Map<String, Integer> getResultSetColumnNames(ResultSet rsPutFile) throws SQLException {
//        ResultSetMetaData rsMetaData = rsPutFile.getMetaData();
//        final int columnsCount = rsMetaData.getColumnCount();
//        Map<String, Integer> rsColNames = new HashMap<>();
//        for (int colIndex = 1; colIndex <= columnsCount; colIndex++) {
//            rsColNames.put(rsMetaData.getColumnName(colIndex), colIndex);
//        }
//        return rsColNames;
//    }
//
//    private static void mergeCSVFiles(String[] csvFiles, String outputFilePath) throws IOException {
//        try (FileWriter writer = new FileWriter(outputFilePath); CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
//
//            for (String csvFile : csvFiles) {
//                try (FileReader reader = new FileReader(csvFile); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
//
//                    List<CSVRecord> records = csvParser.getRecords();
////                    // Skip the header for all files except the first one
////                    if (csvPrinter.getRecordNumber() > 0) {
////                        records = records.subList(1, records.size());
////                    }
//
//                    // Write records to the merged CSV file
//                    csvPrinter.printRecords(records);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static void executeListQuery(String query) {
        try {
            SnowflakeResultSetV1 resultSet = (SnowflakeResultSetV1) executeSql(query);
            System.out.println("Current Staging Stats : " + resultSet.getResultSetSerializables(1000000).toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

