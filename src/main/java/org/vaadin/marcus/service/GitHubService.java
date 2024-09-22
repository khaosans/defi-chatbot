package org.vaadin.marcus.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GitHubService {
    private static final Logger logger = Logger.getLogger(GitHubService.class.getName());
    private String personalAccessToken;

    public GitHubService() {
        this.personalAccessToken = promptForToken();
    }

    private String promptForToken() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your GitHub Personal Access Token: ");
        return scanner.nextLine(); // In production, consider using a secure method to handle sensitive input
    }

    public String getPersonalAccessToken() {
        return personalAccessToken;
    }

    public void pushNewFile(String repoName, String branchName, String filePath, String content, String commitMessage) {
        try {
            String apiUrl = String.format("https://api.github.com/repos/%s/%s/contents/%s", "your-username", repoName, filePath);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "token " + personalAccessToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = String.format("{\"message\": \"%s\", \"content\": \"%s\", \"branch\": \"%s\"}",
                    commitMessage, Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8)), branchName);

            try (var os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("File created successfully.");
            } else {
                String errorMessage = "Failed to create file. Response Code: " + responseCode;
                System.out.println(errorMessage);
                logger.log(Level.SEVERE, errorMessage);
            }
        } catch (IOException e) {
            String errorMessage = "IOException occurred: " + e.getMessage();
            System.out.println(errorMessage);
            logger.log(Level.SEVERE, errorMessage, e);
        }
    }

    public void updateFile(String repoName, String branchName, String filePath, String content, String commitMessage) {
        try {
            // First, get the file's SHA to update it
            String getFileUrl = String.format("https://api.github.com/repos/%s/%s/contents/%s", "your-username", repoName, filePath);
            URL url = new URL(getFileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "token " + personalAccessToken);
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response to get the SHA
                StringBuilder response = new StringBuilder();
                try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }

                // Extract the SHA from the response
                String sha = extractSHA(response.toString());

                // Now update the file
                String apiUrl = String.format("https://api.github.com/repos/%s/%s/contents/%s", "your-username", repoName, filePath);
                connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Authorization", "token " + personalAccessToken);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                String jsonInputString = String.format("{\"message\": \"%s\", \"content\": \"%s\", \"branch\": \"%s\", \"sha\": \"%s\"}",
                        commitMessage, Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8)), branchName, sha);

                try (var os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    System.out.println("File updated successfully.");
                } else {
                    String errorMessage = "Failed to update file. Response Code: " + responseCode;
                    System.out.println(errorMessage);
                    logger.log(Level.SEVERE, errorMessage);
                }
            } else {
                String errorMessage = "File not found. Response Code: " + responseCode;
                System.out.println(errorMessage);
                logger.log(Level.SEVERE, errorMessage);
            }
        } catch (IOException e) {
            String errorMessage = "IOException occurred: " + e.getMessage();
            System.out.println(errorMessage);
            logger.log(Level.SEVERE, errorMessage, e);
        }
    }

    private String extractSHA(String jsonResponse) {
        // Simple extraction logic (consider using a JSON library for production)
        String shaKey = "\"sha\":\"";
        int startIndex = jsonResponse.indexOf(shaKey) + shaKey.length();
        int endIndex = jsonResponse.indexOf("\"", startIndex);
        return jsonResponse.substring(startIndex, endIndex);
    }

    // Additional methods for GitHub API interactions will be added here
}