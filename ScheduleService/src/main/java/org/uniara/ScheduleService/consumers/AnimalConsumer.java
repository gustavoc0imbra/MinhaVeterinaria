package org.uniara.ScheduleService.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.uniara.ScheduleService.DTO.AppointmentAnimalDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class AnimalConsumer {
    private final String ANIMAL_URL = "http://localhost:8082/api/v0/animals/{animalId}";
    private AppointmentAnimalDTO animalDTO;

    public AppointmentAnimalDTO getById(String token, String animalId) {
        String response = doRequest(token, animalId);
        Map<String, String> json = convertResponse(response);

        if (json == null) {
            return null;
        }

        animalDTO = new AppointmentAnimalDTO(json.get("id"), json.get("name"));

        return animalDTO;
    }

    private String doRequest(String token, String animalId) {
        HttpClient client = HttpClient.newBuilder().build();
        String response = null;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ANIMAL_URL.replace("{animalId}", animalId)))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private Map<String, String> convertResponse(String response) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json = null;

        try {
            json = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            System.out.println("Erro ao converter json: ");
            e.printStackTrace();
        }

        return json;
    }
}
