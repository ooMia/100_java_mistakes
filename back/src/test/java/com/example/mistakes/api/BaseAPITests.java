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
public class BaseAPITests {

  @Autowired private MockMvc mockMvc;
  @Autowired private RequestMappingHandlerMapping handlerMapping;

  private ResultMatcher[] matchers() {
    return new ResultMatcher[] {
      status().isOk(),
      content().contentType(MediaType.APPLICATION_JSON),
      jsonPath("$.result").exists(),
      jsonPath("$.length").value(greaterThan(0))
    };
  }

  @Test
  public void testOneStatic() throws Exception {
    mockMvc.perform(get("/api/questions/t1")).andExpectAll(matchers());
  }

  @Test
  public void testSomeStatic() throws Exception {
    Iterable<String> apis =
        List.of(
            // "/api/hello",
            "/api/questions/t1", "/api/questions/t2");
    for (String api : apis) {
      mockMvc.perform(get(api)).andExpectAll(matchers());
    }
  }

  @TestFactory
  // testSingleDynamic()
  public Stream<DynamicTest> testSomeStaticAsFactory() {
    var urls = List.of("/api/questions/t1", "/api/questions/t2");
    return urls.parallelStream()
        .map(
            url ->
                DynamicTest.dynamicTest(
                    url, () -> mockMvc.perform(get(url)).andExpectAll(matchers())));
  }

  /**
   * Extract `GET /api/**` endpoints from handlerMapping
   *
   * @return List of endpoints
   */
  private List<String> getEndpoints() {

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

  @TestFactory
  public Stream<DynamicTest> testSomeDynamicAsFactory() {
    var urls = List.of("/api/questions/t1", "/api/questions/t2");
    var endpoints = getEndpoints();
    assert endpoints.containsAll(urls);

    return getEndpoints().stream()
        .filter(e -> e.startsWith("/api/questions"))
        .map(
            url ->
                DynamicTest.dynamicTest(
                    url, () -> mockMvc.perform(get(url)).andExpectAll(matchers())));
  }
}
