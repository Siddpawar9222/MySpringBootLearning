package com.example.thirdpartyapis.service;

import com.example.thirdpartyapis.exception.GlobalException;
import com.example.thirdpartyapis.exception.ResourceNotFoundException;
import com.example.thirdpartyapis.model.Collaborator;
import com.example.thirdpartyapis.model.Response;
import com.example.thirdpartyapis.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CollaboratorService  {


    private final static Logger LOG = LoggerFactory.getLogger(CollaboratorService.class);

    @Value("${github.accessToken}")
    private String authToken;


    @Value("${github.owner}")
    private String owner ;

    @Value("${github.repo}")
    private String repo;

    private final String BASE_URL = "https://api.github.com/repos/{owner}/{repo}/collaborators";




    // To get response data as it is , we can use Object TypeReference.
    public Response getAllCollaboratorsRawData() {
        LOG.info("getAllCollaboratorsRawData service has been called ::: {}", new Date());

        //kept per_page as 100 to avoid api calls
        String url = BASE_URL.replace("{owner}", owner).replace("{repo}", repo) + "?per_page=40&page=" ;

        RestTemplate restTemplate = new RestTemplate();
        List<Object> collaborators = new ArrayList<>();

        try {
            // Start by fetching the first page
            int currentPage = 1;
            HttpHeaders headers = this.createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Get the first page to start
            ResponseEntity<String> response = restTemplate.exchange(url + currentPage, HttpMethod.GET, entity, String.class);  // *

            LOG.info("GitHub Collaborators Response: " + response.getStatusCode());
            LOG.info("GitHub Collaborators hasBody: " + response.hasBody());

            if (response.getStatusCode().equals(HttpStatus.OK)) {

                //Created TypeReference of Object
                TypeReference<Object> typeRef = new TypeReference<>() {
                };

                Object rawData = JsonUtil.convertStringToObject(response.getBody(), typeRef);


                collaborators.add(rawData);

                HttpHeaders responseHeader = response.getHeaders();
                int lastPage = 1;  // Default to 1 if no pagination

                // Handle pagination, check for the "Link" header
                if (responseHeader.containsKey("Link")) {
                    String linkHeader = responseHeader.getFirst("Link");
                    LOG.info("Link Header: " + linkHeader);
                    lastPage = this.extractLastPage(linkHeader);
                    LOG.info("Total pages: " + lastPage);
                } else {
                    LOG.info("No pagination found");
                }

                // Loop through all pages and fetch collaborators
                for (currentPage = 2; currentPage <= lastPage; currentPage++) {
                    LOG.info("Fetching page: " + currentPage);
                    response = restTemplate.exchange(url + currentPage, HttpMethod.GET, entity, String.class);

                    if (response.getStatusCode().equals(HttpStatus.OK)) {
                        rawData = JsonUtil.convertStringToObject(response.getBody(), typeRef);

                        collaborators.add(rawData);  // addAll giving error
                    }else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ResourceNotFoundException("No resource is found on github");
                    } else {
                        throw new GlobalException("Exception while calling github api");
                    }
                }
            } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResourceNotFoundException("No resource is found on github");
            } else {
                throw new GlobalException("Exception while calling github api");
            }

            LOG.info("getAllCollaboratorsRawData has ended {} ", new Date());
            return new Response("Collaborators fetched successfully",collaborators);
        }catch (ResourceNotFoundException e) {
            LOG.error("ResourceNotFoundException occurred at getAllCollaboratorsRawData service ::: {}", e.getMessage());
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            LOG.error("Exception occurred at getAllCollaboratorsRawData service ::: {}", e.getMessage());
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }


    public Response getAllCollaboratorsFilteredInfo() {
        LOG.info("getAllCollaboratorsFilteredInfo service has been called ::: {}", new Date());

        //kept per_page as 100 to avoid api calls
        String url = BASE_URL.replace("{owner}", owner).replace("{repo}", repo) + "?per_page=100&page=" ;

        RestTemplate restTemplate = new RestTemplate();
        List<Collaborator> collaborators = new ArrayList<>();

        try {
            // Start by fetching the first page
            int currentPage = 1;
            HttpHeaders headers = this.createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Get the first page to start
            ResponseEntity<String> response = restTemplate.exchange(url + currentPage, HttpMethod.GET, entity, String.class);

            LOG.info("GitHub Collaborators Response: " + response.getStatusCode());
            LOG.info("GitHub Collaborators hasBody: " + response.hasBody());

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                //List<Object> jsonList = this.convertStringToJsonList(response.getBody());

                JsonNode jsonNode = JsonUtil.convertStringToJsonNode(response.getBody());

                LOG.info("jsonNode: " + jsonNode);
                List<Collaborator> collaboratorList = this.getCollaboratorsFieldsFromJsonNode(jsonNode);

                collaborators.addAll(collaboratorList);

                HttpHeaders responseHeader = response.getHeaders();
                int lastPage = 1;  // Default to 1 if no pagination

                // Handle pagination, check for the "Link" header
                if (responseHeader.containsKey("Link")) {
                    String linkHeader = responseHeader.getFirst("Link");
                    LOG.info("Link Header: " + linkHeader);
                    lastPage = this.extractLastPage(linkHeader);
                    LOG.info("Total pages: " + lastPage);
                } else {
                    LOG.info("No pagination found");
                }

                // Loop through all pages and fetch collaborators
                for (currentPage = 2; currentPage <= lastPage; currentPage++) {
                    LOG.info("Fetching page: " + currentPage);
                    response = restTemplate.exchange(url + currentPage, HttpMethod.GET, entity, String.class);

                    if (response.getStatusCode().equals(HttpStatus.OK)) {
                        jsonNode = JsonUtil.convertStringToJsonNode(response.getBody());
                        LOG.info("jsonNode: " + jsonNode);

                        List<Collaborator> paginatedCollaboratorList = this.getCollaboratorsFieldsFromJsonNode(jsonNode);

                        collaborators.addAll(paginatedCollaboratorList);
                    }else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ResourceNotFoundException("No resource is found on github");
                    } else {
                        throw new GlobalException("Exception while calling github api");
                    }
                }
            } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResourceNotFoundException("No resource is found on github");
            } else {
                throw new GlobalException("Exception while calling github api");
            }

            LOG.info("getAllCollaboratorsFilteredInfo has ended {} ", new Date());
            return new Response("Collaborators fetched successfully",collaborators);
        }catch (ResourceNotFoundException e) {
            LOG.error("ResourceNotFoundException occurred at getAllCollaborators service ::: {}", e.getMessage());
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            LOG.error("Exception occurred at getAllCollaborators service ::: {}", e.getMessage());
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }




    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + this.authToken);
        return headers;
    }

    private int extractLastPage(String linkHeader) {
        String[] links = linkHeader.split(",");
        LOG.info("Links of GitHub ::: {} ", Arrays.toString(links));
        for (String link : links) {
            LOG.info("Link: " + link);
            if (link.endsWith("rel=\"last\"")) {
                int startIndex = link.indexOf("&page=");
                int endIndex = link.indexOf(">");
                String lastPage = link.substring(startIndex + 6, endIndex);
                return Integer.parseInt(lastPage);
            }
        }
        return 1;
    }

    private List<Collaborator> getCollaboratorsFieldsFromJsonNode(JsonNode jsonNode) {
        List<Collaborator> collaboratorsList = new ArrayList<>();
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();

            String username = element.path("login").asText();
            Long id = element.path("id").asLong();
            String imageUrl = element.path("avatar_url").asText();
            String gitHubUrl = element.path("html_url").asText();
            collaboratorsList.add(new Collaborator(id, username, imageUrl, gitHubUrl));
        }
        return collaboratorsList;
    }
}
/*
     // * -- >
     Using String.class provides flexibility, safety, and manual control, which are useful for handling dynamic or unknown API responses. You can then process the response based on your needs. On the other hand, directly deserializing into a List, Map, or custom object works well when the response format is known and consistent.
 */