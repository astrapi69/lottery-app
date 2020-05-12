package de.alpharogroup.lottery.service;

import de.alpharogroup.lottery.drawing.DrawnLotteryNumbersExtensions;
import de.alpharogroup.lottery.drawing.DrawnLotteryNumbersFactory;
import de.alpharogroup.lottery.drawings.DrawnLotteryNumbers;
import de.alpharogroup.lottery.enums.LotteryGameType;
import de.alpharogroup.lottery.jpa.entities.*;
import de.alpharogroup.lottery.jpa.repositories.*;
import de.alpharogroup.lottery.wincategories.LotteryWinCategory;
import de.alpharogroup.random.RandomExtensions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) public class LotteryStatisticsService
{
	LotteryNumberStatisticsRepository lotteryNumberStatisticsRepository;
	SixOfFourtynineCombinationsRepository sixOfFourtynineCombinationsRepository;

	SixOfFourtynineCombinationsStatisticsRepository sixOfFourtynineCombinationsStatisticsRepository;

	public void startStatistics(int drawExecutionCount)
	{
		Thread thread = new Thread(() -> {
			int drawCountMax = 10000;
			if (drawExecutionCount < drawCountMax)
			{
				drawCountMax = drawExecutionCount;
			}
			int drawCount = 0;
			while (drawCount < drawCountMax)
			{
				drawCount++;
				DrawnLotteryNumbers drawnLotteryNumbers = DrawnLotteryNumbersFactory
					.newRandomDrawnLotteryNumbers();
				Set<Integer> lotteryNumbers = drawnLotteryNumbers.getLotteryNumbers();
				List<Integer> combination = new ArrayList<>(lotteryNumbers);
				combination.sort(Comparator.naturalOrder());
				Optional<SixOfFourtynineCombinations> optionalSixOfFourtynineCombinations = sixOfFourtynineCombinationsRepository
					.findDistinctByNumber1AndNumber2AndNumber3AndNumber4AndNumber5AndNumber6(
						combination.get(0), combination.get(1), combination.get(2),
						combination.get(3), combination.get(4), combination.get(5));
				SixOfFourtynineCombinations sixOfFourtynineCombinations;
				Optional<SixOfFourtynineCombinationsStatistics> byOwner;
				if (optionalSixOfFourtynineCombinations.isPresent())
				{
					sixOfFourtynineCombinations = optionalSixOfFourtynineCombinations.get();
				}
				else
				{
					throw new IllegalArgumentException("Combinations of lottery not initialized.");
				}

				setSixOfFourtynineCombinationNumberStatistics(sixOfFourtynineCombinations);
				byOwner = sixOfFourtynineCombinationsStatisticsRepository
					.findByLotteryNumbers(sixOfFourtynineCombinations);
				if (byOwner.isPresent())
				{
					SixOfFourtynineCombinationsStatistics sixOfFourtynineCombinationsStatistics = byOwner
						.get();
					sixOfFourtynineCombinationsStatistics
						.setDrawnCount(sixOfFourtynineCombinationsStatistics.getDrawnCount() + 1);
				}
				else
				{
					sixOfFourtynineCombinationsStatisticsRepository.save(
						SixOfFourtynineCombinationsStatistics.builder()
							.id(sixOfFourtynineCombinations.getId())
							.lotteryNumbers(sixOfFourtynineCombinations).drawnCount(1).build());
				}

			}
		});
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	public void setSixOfFourtynineCombinationNumberStatistics(
		SixOfFourtynineCombinations sixOfFourtynineCombination)
	{
		sixOfFourtynineCombination.toSet().stream().forEach(drawnNumber -> {
			LotteryNumberStatistics lotteryNumberByDrawnNumber = lotteryNumberStatisticsRepository
				.findByDrawnNumber(drawnNumber);
			int currentDrawnNumber = lotteryNumberByDrawnNumber.getDrawnCount() + 1;
			lotteryNumberByDrawnNumber.setDrawnCount(currentDrawnNumber);
			lotteryNumberStatisticsRepository.save(lotteryNumberByDrawnNumber);
		});

	}

}
