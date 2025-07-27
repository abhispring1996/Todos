package com.data.fetcher;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

public class LatencyChecker {
    private static final int TIMEOUT_MS = 3000;
    public static void main(String[] args) {
        Instant start = Instant.now();
        String hostname = "oracle19-1.cdztqno64b6m.us-east-1.rds.amazonaws.com";
        int port = 1521;
        try {
            InetAddress address = InetAddress.getByName(hostname);
            if (address.isReachable(TIMEOUT_MS)) {
                System.out.println("Reachable via ping");
            }

            start = Instant.now();
            // Fallback: Check TCP connectivity on port 443
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(hostname, port), TIMEOUT_MS);
                System.out.println("Reachable via TCP Connection");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
