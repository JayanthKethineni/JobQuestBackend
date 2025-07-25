package com.jobquest.backend.controller;

import com.jobquest.backend.entity.Job;
import com.jobquest.backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracked-jobs")  // ✅ Changed to avoid conflict
public class JobController {

    @Autowired
    private JobRepository jobRepo;

    @PostMapping
    public Job addJob(@RequestBody Job job) {
        return jobRepo.save(job);
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobRepo.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobRepo.deleteById(id);
    }
}
