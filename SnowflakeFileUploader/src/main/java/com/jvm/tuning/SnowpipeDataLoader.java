package com.jvm.tuning;

import net.snowflake.ingest.streaming.OpenChannelRequest;
import net.snowflake.ingest.streaming.SnowflakeStreamingIngestChannel;
import net.snowflake.ingest.streaming.SnowflakeStreamingIngestClient;
import net.snowflake.ingest.streaming.SnowflakeStreamingIngestClientFactory;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// SnowpipeDataLoader simulates a high-throughput data loading scenario using Snowflake's streaming ingest API.
public class SnowpipeDataLoader {
    private final static String database = "DBMI_DB1";
    private final static String schemaName = "GEETHA";
    private final static String tableName = "MYTABLE_10M_BASE";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting object allocation load...");
        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int jobId = 1; jobId <= 24; jobId++) {
            service.submit(new DataLoader(jobId));
        }

        service.shutdown();
        boolean finished = service.awaitTermination(1, TimeUnit.HOURS);
        if (finished) {
            System.out.println("All tasks completed. Starting monitoring phase...");
        } else {
            System.err.println("Timeout while waiting for tasks to finish.");
        }
    }

    static class DataLoader implements Runnable {
        private final int jobId;

        DataLoader(int jobId) {
            this.jobId = jobId;
        }

        @Override
        public void run() {
            SnowflakeStreamingIngestClient streamingClient = null;
            SnowflakeStreamingIngestChannel channel = null;
            try {
                System.out.printf("Job with id:" + jobId + " started.%n");
                streamingClient = createStreamingClient();
                channel = createChannel(streamingClient);
                insertData(channel);
                System.out.printf("Job with id:" + jobId + " completed.%n");
            } catch (Exception e) {
                System.err.printf("Job with id: %d was interrupted. Exception: %s%n", jobId, e.getMessage());
                e.printStackTrace(System.err);
            } finally {
                try {
                    if (channel != null) {
                        channel.close().get();
                    }
                    if (streamingClient != null) {
                        streamingClient.close();
                    }
                } catch (Exception e) {
                    System.err.printf("Error closing resources for job %d: %s%n", jobId, e.getMessage());
                }
            }
        }

        private SnowflakeStreamingIngestClient createStreamingClient() throws Exception {
            Properties props = new Properties();
            String streamingClientName = "INFORMATICA_CMI_SUPERPIPE_CLIENT";
            // Basic connection & authentication properties
            props.put("user", "DBMIQA");
            props.put("private_key_passphrase", "123456789");
            props.put("host", "informatica.eu-central-1.snowflakecomputing.com");
            props.put("port", "443");
            props.put("url", "https://informatica.eu-central-1.snowflakecomputing.com:443");
            props.put("schema", "GEETHA");

            Path privateKeyPath = Paths.get("C:\\Users\\absharma\\Downloads\\DbmiJars\\rsa_key1016.p8");
            String privateKeyPEM = new String(Files.readAllBytes(privateKeyPath), StandardCharsets.UTF_8);
            props.put("private_key", privateKeyPEM);

            return SnowflakeStreamingIngestClientFactory.builder(streamingClientName)
                    .setProperties(props)
                    .build();
        }

        // Target: 1 GB = 1,000 MB
        // 476,000 records × 2.1 KB/record ≈ 1,000 MB (1 GB)
        private void insertData(SnowflakeStreamingIngestChannel channel) throws Exception {
            for (int i = 1; i <= 476_000; i++) {
                Map<String, Object> record = generateRecord(i);
                String offsetToken = "offset_" + i + "_" + jobId;
                channel.insertRow(record, offsetToken);
                if (i % 50000 == 0) {
                    System.out.printf("JobId: " + jobId + " inserted %d records...%n", i);
                }
            }
        }

        // 2.1 KB per record
        private static Map<String, Object> generateRecord(int index) {
            Map<String, Object> record = new HashMap<>();
            record.put("COL01", String.valueOf(1555 + index));
            record.put("COL02", "2");
            record.put("COL03", "3");
            record.put("COL04", "4");
            record.put("COL05", "5");

            int longStrLen = 64;

            record.put("COL06", longString('A', longStrLen));
            record.put("COL07", longString('B', longStrLen));
            record.put("COL08", longString('C', longStrLen));
            record.put("COL09", longString('D', longStrLen));
            record.put("COL10", longString('E', longStrLen));

            record.put("COL11", LocalDateTime.of(2022, 1, 1, 0, 0));
            record.put("COL12", LocalDateTime.of(2022, 2, 1, 0, 0));
            record.put("COL13", LocalDateTime.of(2022, 3, 1, 0, 0));
            record.put("COL14", LocalDateTime.of(2022, 4, 1, 0, 0));
            record.put("COL15", LocalDateTime.of(2022, 5, 1, 0, 0));
            record.put("COL16", LocalDateTime.of(2022, 1, 1, 1, 12, 13));
            record.put("COL17", LocalDateTime.of(2022, 1, 1, 2, 12, 13));
            record.put("COL18", LocalDateTime.of(2022, 1, 1, 3, 12, 13));
            record.put("COL19", LocalDateTime.of(2022, 1, 1, 4, 12, 13));
            record.put("COL20", LocalDateTime.of(2022, 1, 1, 5, 12, 13));

            BigDecimal baseNumber = new BigDecimal("100000.000");
            record.put("COL21", baseNumber.add(new BigDecimal(index * 10000 + 3456.789)));
            record.put("COL22", baseNumber.add(new BigDecimal(index * 20000 + 3456.789)));
            record.put("COL23", baseNumber.add(new BigDecimal(index * 30000 + 3456.789)));
            record.put("COL24", baseNumber.add(new BigDecimal(index * 40000 + 3456.789)));
            record.put("COL25", baseNumber.add(new BigDecimal(index * 50000 + 3456.789)));

            record.put("COL26", longString('F', longStrLen));
            record.put("COL27", longString('G', longStrLen));
            record.put("COL28", longString('H', longStrLen));
            record.put("COL29", longString('I', longStrLen));

            return record;
        }

        private static String longString(char prefixChar, int length) {
            StringBuilder sb = new StringBuilder(length);
            sb.append(prefixChar);
            for (int i = 1; i < length - 2; i++) {
                sb.append('_');
            }
            sb.append("64");
            return sb.toString();
        }

        private SnowflakeStreamingIngestChannel createChannel(SnowflakeStreamingIngestClient client) throws Exception {
            String channelName = tableName + Math.random();
            OpenChannelRequest channelRequest = OpenChannelRequest.builder(channelName).setDBName(
                            database).setSchemaName(schemaName).setTableName(
                            tableName).setOnErrorOption(
                            OpenChannelRequest.OnErrorOption.ABORT)
                    .build();

            SnowflakeStreamingIngestChannel streamingIngestChannel = client.openChannel(channelRequest);
            System.out.println("Snowflake streaming channel: " + channelName + " for jobId: " + jobId + " opened successfully.");
            return streamingIngestChannel;
        }
    }
}
