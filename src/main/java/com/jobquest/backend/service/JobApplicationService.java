package com.jobquest.backend.service;

import com.jobquest.backend.model.JobApplication;
import com.jobquest.backend.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobRepo;

    public JobApplication addApplication(JobApplication job) {
        return jobRepo.save(job);
    }

    public List<JobApplication> getAllApplications() {
        return jobRepo.findAll();
    }

    public Optional<JobApplication> getApplicationById(Long id) {
        return jobRepo.findById(id);
    }

    public void deleteApplication(Long id) {
        jobRepo.deleteById(id);
    }

    public JobApplication updateApplication(Long id, JobApplication updated) {
        return jobRepo.findById(id).map(existing -> {
            existing.setCompanyName(updated.getCompanyName());
            existing.setJobTitle(updated.getJobTitle());
            existing.setApplicationDate(updated.getApplicationDate());
            existing.setStatus(updated.getStatus());
            existing.setNotes(updated.getNotes());
            return jobRepo.save(existing);
        }).orElse(null);
    }

    // ✅ NEW METHOD to fetch jobs for logged-in user only
    public List<JobApplication> getApplicationsByUser(String email) {
        return jobRepo.findAll().stream()
            .filter(job -> job.getUser() != null && job.getUser().getEmail().equals(email))
            .toList();
    }
}
