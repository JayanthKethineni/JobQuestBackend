package com.jobquest.backend.controller;

import com.jobquest.backend.model.JobApplication;
import com.jobquest.backend.model.User;
import com.jobquest.backend.repository.UserRepository;
import com.jobquest.backend.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public JobApplication create(@RequestBody JobApplication job, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);
        job.setUser(user);
        return jobService.addApplication(job);
    }

    @GetMapping
    public List<JobApplication> getAll(Authentication authentication) {
        String email = authentication.getName();
        return jobService.getApplicationsByUser(email);
    }

    @GetMapping("/{id}")
    public JobApplication getById(@PathVariable Long id) {
        return jobService.getApplicationById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable Long id, @RequestBody JobApplication updated) {
        return jobService.updateApplication(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobService.deleteApplication(id);
    }
}
