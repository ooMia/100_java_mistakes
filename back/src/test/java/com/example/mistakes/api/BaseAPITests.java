package com.example.mistakes.api;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
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

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class BaseAPITests {

  @Autowired private MockMvc mockMvc;
  @Autowired private RequestMappingHandlerMapping handlerMapping;

  ResultMatcher[] matchers() {
    return new ResultMatcher[] {
      status().isOk(),
      content().contentType(MediaType.APPLICATION_JSON),
      jsonPath("$.result").exists(),
      jsonPath("$.length").value(greaterThan(0))
    };
  }

  /**
   * Extract `GET /api/**` endpoints from handlerMapping
   *
   * @return List of endpoints
   */
  List<String> getEndpoints() {

    var keys = handlerMapping.getHandlerMethods().keySet().stream().toList();

    Function<RequestMappingInfo, String> getPattern =
        (info) -> {
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
  void testLogEndpoints() {
    log.info("Endpoints: {}", getEndpoints());
    // [
    // /v3/api-docs, /v3/api-docs.yaml, /error, /v3/api-docs/swagger-config, /error,
    // /swagger-ui.html,
    // /api/questions/t3,
    // /api/questions/{id}, /api/hello, /api/expression/{index}]
  }

  @TestFactory
  Stream<DynamicTest> testSomeDynamicAsFactory() {
    List<String> endpoints =
        List.of(
            "/api/questions/t3", "/api/questions/{id}", "/api/hello", "/api/expression/{index}");

    assert endpoints.containsAll(endpoints);

    return getEndpoints().stream()
        .filter(e -> e.startsWith("/api") && !e.equals("/api/hello"))
        .map(
            url -> {
              String requestUrl = url.replace("{id}", "2").replace("{index}", "2");
              return DynamicTest.dynamicTest(
                  url, () -> mockMvc.perform(get(requestUrl)).andExpectAll(matchers()));
            });

    // return getEndpoints().stream()
    //     .filter(e -> e.startsWith("/api/questions"))
    //     .map(
    //         url ->
    //             DynamicTest.dynamicTest(
    //                 url, () -> mockMvc.perform(get(url)).andExpectAll(matchers())));
  }
}
