package com.jaruiz.examples.observability.adapters.persistence.repository;

import com.jaruiz.examples.observability.adapters.persistence.model.ProcessDataPM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<ProcessDataPM, Long> {
}
