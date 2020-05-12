package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinations;
import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinationsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SixOfFourtynineCombinationsStatisticsRepository extends JpaRepository<SixOfFourtynineCombinationsStatistics, Integer>
{
	@Transactional
	Optional<SixOfFourtynineCombinationsStatistics> findByLotteryNumbers(SixOfFourtynineCombinations lotteryNumbers);

}
