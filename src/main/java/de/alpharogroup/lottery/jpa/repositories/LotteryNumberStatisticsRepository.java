package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.jpa.entities.LotteryNumberStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryNumberStatisticsRepository extends CrudRepository<LotteryNumberStatistics, Long>
{
	LotteryNumberStatistics findByDrawnNumber(int drawnNumber);
}
