package ToolCalling.profit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfitRepository extends JpaRepository<ProfitEntity, Long> {
    Optional<ProfitEntity> findByMonth(String month);
}
