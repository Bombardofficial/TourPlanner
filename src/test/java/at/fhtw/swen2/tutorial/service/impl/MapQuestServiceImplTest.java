package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.service.MapQuestService;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.web.servlet.function.RequestPredicates.method;

class MapQuestServiceImplTest {

    @Test
    public void testGetMap() {

        // Arrange
        String start = "Vienna";
        String end = "Belgrade";
        MapQuestServiceImpl map = new MapQuestServiceImpl();

        // Act
        Image mapImage = map.getMap(start, end);

        // Assert
        assertNotNull(mapImage);
    }

}