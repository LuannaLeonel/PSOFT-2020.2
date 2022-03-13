package com.ufcg.psoft.coronavirusbrasil.repository;

import com.ufcg.psoft.coronavirusbrasil.model.ExameCovid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestesRepository extends JpaRepository<ExameCovid, Long> {

}
