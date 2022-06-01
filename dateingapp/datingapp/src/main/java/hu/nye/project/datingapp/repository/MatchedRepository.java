package hu.nye.project.datingapp.repository;

import hu.nye.project.datingapp.entity.Matched;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchedRepository extends JpaRepository<Matched, Long> {

    List<Matched> findAllUserMatchedByUserId(Long userId);
}
