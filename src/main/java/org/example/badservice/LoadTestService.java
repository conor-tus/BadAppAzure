package org.example.badservice;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LoadTestService {

    private final long minDelay = 30 * 1000L;  // 1 minute
    private final long maxDelay = 2 * 60 * 1000L;

    // Simulate querying another application (e.g., REST API)
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @Scheduled(fixedRateString = "1000")
    public void queryOwnApi() {
        // URL of the slow API in the same application
        String externalServiceUrl = "http://localhost:80/slow-api"; // Make sure to use the correct port

        // Make the API call to its own slow endpoint
        String response = restTemplate.getForObject(externalServiceUrl, String.class);

        System.out.println("Response from own slow API: " + response);

        // Randomize and simulate high CPU usage
        simulateRandomCpuUsage();

        // Randomize and simulate high memory usage
        simulateRandomMemoryUsage();

        // Randomize the frequency of next API call
        try {
            long nextCallDelay = random.nextInt((int) (maxDelay - minDelay)) + minDelay;
            System.out.println("Next API call will happen in: " + nextCallDelay / 1000 + " seconds.");
            Thread.sleep(nextCallDelay);  // Random delay between API calls
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRateString = "1000")
    public void queryAnotherApp() {
        System.out.println("Querying another application...");

        // Query the external application (replace with your API URL)
        String externalServiceUrl = "https://libraryservice-gngxd7dphwadg3hh.canadacentral-01.azurewebsites.net/api/book";
        String response = restTemplate.getForObject(externalServiceUrl, String.class);

        System.out.println("Response from external app: " + response);

        // Randomize and simulate high CPU usage
        simulateRandomCpuUsage();

        // Randomize and simulate high memory usage
        simulateRandomMemoryUsage();

        // Randomize the frequency of next API call
        try {
            long nextCallDelay = random.nextInt((int) (maxDelay - minDelay)) + minDelay;
            System.out.println("Next API call will happen in: " + nextCallDelay / 1000 + " seconds.");
            Thread.sleep(nextCallDelay);  // Random delay between API calls
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Randomize memory usage (vary the amount of memory allocated)
    private void simulateRandomMemoryUsage() {
        int allocations = random.nextInt(500) + 100;  // Random between 100 to 500 memory allocations
        System.out.println("Simulating memory usage with " + allocations + " allocations...");

        List<byte[]> memoryHog = new ArrayList<>();
        for (int i = 0; i < allocations; i++) {
            memoryHog.add(new byte[random.nextInt(1024 * 1024 * 5) + 1024 * 1024]); // 1MB to 5MB per allocation
        }
        System.out.println("Allocated " + memoryHog.size() + " memory blocks.");
    }

    private void simulateRandomCpuUsage() {
        int iterations = random.nextInt(10_000_000) + 1;  // Random iterations from 1 to 10 million
        System.out.println("Simulating CPU usage with " + iterations + " iterations...");

        long sum = 0;
        for (long i = 0; i < iterations; i++) {
            sum += i * i;
        }
        System.out.println("Completed CPU-intensive task with sum: " + sum);
    }

}
