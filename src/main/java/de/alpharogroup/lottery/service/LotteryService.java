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

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LotteryService
{

	TicketsRepository ticketsRepository;

	SixOfFourtynineCombinationsRepository sixOfFourtynineCombinationsRepository;

	public boolean existCombination(List<Integer> combination) {
		Optional<SixOfFourtynineCombinations> optionalSixOfFourtynineCombinations = sixOfFourtynineCombinationsRepository
			.findDistinctByNumber1AndNumber2AndNumber3AndNumber4AndNumber5AndNumber6(
				combination.get(0),
		combination.get(1),
		combination.get(2),
		 combination.get(3),
		combination.get(4),
		 combination.get(5)
			);
		return optionalSixOfFourtynineCombinations.isPresent();
	}

	/**
	 * Play the given {@link Tickets}
	 *
	 * @param playedTicket
	 *            the ticket to save for later evaluation
	 * @return the uuid from the saved {@link Tickets}
	 */
	public String playTicket(Tickets playedTicket)
	{
		Tickets saved = ticketsRepository.save(playedTicket);
		return saved.getId().toString();
	}

}
