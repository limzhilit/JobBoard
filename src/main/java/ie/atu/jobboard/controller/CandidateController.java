package ie.atu.jobboard.controller;

import ie.atu.jobboard.services.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
  private final CandidateService candidateService;

  public CandidateController(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @GetMapping("/add")
  public ResponseEntity<Integer> add(@RequestParam int a, @RequestParam int b) {
    return ResponseEntity.ok(candidateService.add(a, b));
  }

}

