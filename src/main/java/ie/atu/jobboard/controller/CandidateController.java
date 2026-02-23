package ie.atu.jobboard.controller;

import ie.atu.jobboard.model.Candidate;
import ie.atu.jobboard.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/candidates")
public class CandidateController {
  private final CandidateService candidateService;

  public CandidateController(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @PostMapping
  public ResponseEntity<Candidate> addCandidate(
      @Valid @RequestBody Candidate candidate) {
    Candidate saved = candidateService.addCandidate(candidate);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping
  public ResponseEntity<List<Candidate>> getAllCandidates() {
    return ResponseEntity.ok(candidateService.getAllCandidates());
  }
}