package com.example.mistakes.api;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class BaseAPITests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private RequestMappingHandlerMapping handlerMapping;

  /**
   * Extract `GET /api/**` endpoints from handlerMapping
   *
   * @return List of endpoints
   */
  List<String> getEndpoints() {

    var keys = handlerMapping.getHandlerMethods().keySet().stream().toList();

    Function<RequestMappingInfo, String> getPattern = (info) -> {
      if (info == null) {
        return Collections.emptySet().toString();
      }

      // Try path patterns first
      PathPatternsRequestCondition pathPatterns = info.getPathPatternsCondition();
      if (pathPatterns != null) {
        return pathPatterns.getPatternValues().toArray()[0].toString();
      }

      // Fall back to legacy patterns
      PatternsRequestCondition patterns = info.getPatternsCondition();
      if (patterns != null) {
        return patterns.getPatterns().toArray()[0].toString();
      }

      return Collections.emptySet().toString();
    };

    var endpoints = keys.stream().map(getPattern).toList();

    return endpoints;
  }

  @Test
  void testEndPoints() {
    List<String> endpoints = List.of(
        "/error",
        // swagger
        "/v3/api-docs.yaml",
        "/v3/api-docs",
        "/v3/api-docs/swagger-config",
        "/swagger-ui.html",
        // examples
        "/api/examples/",
        "/api/examples/{id}",
        // chapters
        "/api/{chapterName}/{mistakeId}/{exampleId}",
        "/api/{chapterName}",
        "/api/{chapterName}/{mistakeId}",
        // legacy
        "/api/hello");

    assertEquals(Set.copyOf(endpoints), Set.copyOf(getEndpoints()));
  }

  @TestFactory
  Stream<DynamicTest> testSomeDynamicAsFactory() {
    var matchers = new ResultMatcher[] {
        status().isOk(),
        content().contentType(MediaType.APPLICATION_JSON),
        jsonPath("$.result").exists(),
        jsonPath("$.length").value(greaterThan(0))
    };

    return getEndpoints().stream()
        .filter(e -> e.startsWith("/api") && !e.equals("/api/hello"))
        .map(
            url -> {
              String requestUrl = url.replace("examples/{id}", "examples/2")
                  .replace("{chapterName}", "expression")
                  .replace("{mistakeId}", "2")
                  .replace("{exampleId}", "2");
              return DynamicTest.dynamicTest(
                  url, () -> mockMvc.perform(get(requestUrl)).andExpectAll(matchers));
            });
  }
}
