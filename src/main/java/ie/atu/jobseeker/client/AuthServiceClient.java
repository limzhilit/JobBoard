package ie.atu.jobseeker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthServiceClient {

  private final RestClient restClient;

  public AuthServiceClient(@Value("${auth.service.url}") String authServiceUrl) {
    this.restClient = RestClient.builder()
        .baseUrl(authServiceUrl)
        .build();
  }

  public String refresh(String refreshToken) {
    return restClient.post()
        .uri("/auth/refresh")
        .header("Refresh-Token", refreshToken)
        .retrieve()
        .body(String.class);
  }
}
