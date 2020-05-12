package de.alpharogroup.lottery.service;

import de.alpharogroup.lottery.jpa.entities.Boxes;
import de.alpharogroup.lottery.jpa.entities.DrawnNumbers;
import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinations;
import de.alpharogroup.lottery.jpa.entities.Tickets;
import de.alpharogroup.lottery.jpa.repositories.BoxesRepository;
import de.alpharogroup.lottery.jpa.repositories.SixOfFourtynineCombinationsRepository;
import de.alpharogroup.lottery.jpa.repositories.TicketsRepository;
import de.alpharogroup.lottery.wincategories.LotteryWinCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LotteryEvaluationService
{
	BoxesRepository boxesRepository;

	TicketsRepository ticketsRepository;

	/**
	 * Evaluate the given lottery ticket from the given drawn numbers and sets the WinCategory to
	 * the Boxes if there is one.
	 *
	 * @param drawnLotteryNumbers
	 *            the drawn lottery numbers
	 * @param playedLotteryTicket
	 *            the played lottery ticket
	 */
	public void evaluate(Tickets playedLotteryTicket, DrawnNumbers drawnLotteryNumbers)
	{
		Set<Boxes> lotteryBoxes = playedLotteryTicket.getLotteryBoxes();

		for (Boxes lotteryBox : lotteryBoxes)
		{
			boolean withSuperNumber = lotteryBox.getNumbers().toSet()
				.contains(drawnLotteryNumbers.getSuperNumber());
			Optional<LotteryWinCategory> lotteryWinCategory = LotteryWinCategory
				.getLotteryWinCategory(drawnLotteryNumbers.getLotteryNumbers().toSet(),
					lotteryBox.getNumbers().toSet(), withSuperNumber);
			if (lotteryWinCategory.isPresent())
			{
				lotteryBox.setWinCategory(lotteryWinCategory.get());
				boxesRepository.save(lotteryBox);
			}
		}
	}

	public void evaluateAllTickets(DrawnNumbers drawnLotteryNumbers)
	{
		// TODO fix
		Iterable<Tickets> all = ticketsRepository
			.findAllOfDrawnDate(drawnLotteryNumbers.getDrawnDate().toString());
		all.forEach(ticket -> evaluate(ticket, drawnLotteryNumbers));
	}

}
