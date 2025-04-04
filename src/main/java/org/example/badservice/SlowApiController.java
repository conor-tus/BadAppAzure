package org.example.badservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SlowApiController {

    // Simulate a slow API endpoint
    @GetMapping("/slow-api")
    public String slowApi() {
        // Randomize delay between 3 to 10 seconds
        long delayInMillis = (new Random().nextInt(7) + 3) * 1000L; // Random delay from 3 to 10 seconds
        try {
            System.out.println("Simulating slow API response, delay: " + delayInMillis / 1000 + " seconds...");
            Thread.sleep(delayInMillis); // Simulating the delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error occurred while simulating the delay!";
        }

        // Respond after delay
        return "Slow API response after " + delayInMillis / 1000 + " seconds delay.";
    }
}
