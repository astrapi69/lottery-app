package de.alpharogroup.lottery.service;

import de.alpharogroup.lottery.drawing.DrawnLotteryNumbersExtensions;
import de.alpharogroup.lottery.drawing.DrawnLotteryNumbersFactory;
import de.alpharogroup.lottery.drawings.DrawnLotteryNumbers;
import de.alpharogroup.lottery.enums.LotteryGameType;
import de.alpharogroup.lottery.jpa.entities.*;
import de.alpharogroup.lottery.jpa.repositories.*;
import de.alpharogroup.lottery.wincategories.LotteryWinCategory;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.random.number.RandomPrimitivesExtensions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LotteryDrawService
{

	DrawnNumbersRepository drawnNumbersRepository;

	LotteryNumberStatisticsRepository lotteryNumberStatisticsRepository;

	SixOfFourtynineCombinationsRepository sixOfFourtynineCombinationsRepository;

	public DrawnNumbers drawNewLotteryNumbers()
	{
		DrawnNumbers drawnNumbers = newRandomDrawnLotteryNumbers();
		return drawnNumbersRepository.save(drawnNumbers);
	}

	/**
	 * Draws random lottery numbers from the given arguments. This method can draw several lottery
	 * games. <br>
	 * For examples see the unit tests of DrawnLotteryNumbersExtensions
	 *
	 * @param maxNumbers
	 *            the maximum of numbers to draw
	 * @param minVolume
	 *            the min volume
	 * @param maxVolume
	 *            the max volume
	 * @return the sets of the drawn numbers
	 */
	@SneakyThrows
	private DrawnNumbers drawRandomLotteryNumbers(int maxNumbers, int minVolume, int maxVolume)
	{
		LotteryNumberStatistics lotteryNumberStatistics = LotteryNumberStatistics.builder()
			.id(1L)
			.drawnNumber(1)
			.gameType(LotteryGameType.SIX_OF_FOURTYNINE_NORMAL)
			.drawnCount(0)
			.build();
		lotteryNumberStatisticsRepository.save(lotteryNumberStatistics);
		Set<Integer> drawnNumbers = DrawnLotteryNumbersExtensions.draw(maxNumbers, minVolume,
			maxVolume);
		SixOfFourtynineCombinations entity = new SixOfFourtynineCombinations(
			drawnNumbers);
		Optional<SixOfFourtynineCombinations> saved = sixOfFourtynineCombinationsRepository
			.findDistinctByNumber1AndNumber2AndNumber3AndNumber4AndNumber5AndNumber6(entity.getNumber1(),
				entity.getNumber2(),
				entity.getNumber3(),
				entity.getNumber4(),
				entity.getNumber5(),
				entity.getNumber6()
			);
		if(!saved.isPresent()){
			throw new IllegalArgumentException("lottery numbers combination are not initialized.");
		}
		return DrawnNumbers.builder().lotteryNumbers(saved.get())
			.superNumber(
				DrawnLotteryNumbersExtensions.drawSuperNumber(drawnNumbers, minVolume, maxVolume))
			.gameSeventySeven(RandomPrimitivesExtensions.randomIntBetween(0,9999999))
			.superSixNumber(RandomPrimitivesExtensions.randomIntBetween(1, 10))
			.drawnDate(LocalDateTime.now())
			.build();
	}

	private DrawnNumbers newRandomDrawnLotteryNumbers()
	{
		return drawRandomLotteryNumbers(6, 1, 49);
	}

}
