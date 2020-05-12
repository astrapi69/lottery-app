package de.alpharogroup.lottery.service;

import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.lottery.drawing.DrawnLotteryNumbersExtensions;
import de.alpharogroup.lottery.enums.LotteryGameType;
import de.alpharogroup.lottery.jpa.entities.Boxes;
import de.alpharogroup.lottery.jpa.entities.DrawnNumbers;
import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinations;
import de.alpharogroup.lottery.jpa.entities.Tickets;
import de.alpharogroup.lottery.jpa.repositories.BoxesRepository;
import de.alpharogroup.lottery.jpa.repositories.DrawnNumbersRepository;
import de.alpharogroup.lottery.jpa.repositories.TicketsRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log
public class LotteryServiceTest
{

	@Autowired
	BoxesRepository boxesRepository;

	@Autowired
	DrawnNumbersRepository drawnNumbersRepository;

	@SuppressWarnings("unused")
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	LotteryService lotteryService;
	@Autowired
	LotteryEvaluationService lotteryEvaluationService;

	@Autowired
	TicketsRepository ticketsRepository;

	@Test
	@Ignore
	public void testPlayTicket() throws Exception
	{
		Set<Boxes> lotteryBoxes;
		Boxes boxes;
		List<Boxes> notEvaluatedBoxes;
		Set<Integer> draw;
		SixOfFourtynineCombinations numbers;

//		LocalDateTime drawnDate = LocalDateTime.now();

		lotteryBoxes = SetFactory.newHashSet();

		numbers = new SixOfFourtynineCombinations(DrawnLotteryNumbersExtensions.draw(6, 1, 49));
		boxes = boxesRepository
			.save(Boxes.builder().index(0).gameType(LotteryGameType.SIX_OF_FOURTYNINE_NORMAL)
				.numbers(numbers).build());
		lotteryBoxes.add(boxes);
		log.info(boxes.toString() + ":" + boxes.hashCode());
		numbers = new SixOfFourtynineCombinations(DrawnLotteryNumbersExtensions.draw(6, 1, 49));
		boxes = boxesRepository
			.save(Boxes.builder().index(1).gameType(LotteryGameType.SIX_OF_FOURTYNINE_NORMAL)
				.numbers(numbers).build());
		lotteryBoxes.add(boxes);
		log.info(boxes.toString() + ":" + boxes.hashCode());

		DrawnNumbers drawnNumbers = DrawnNumbers.builder().drawnDate(LocalDateTime.now())
			.lotteryNumbers(boxes.getNumbers()).build();
		DrawnNumbers drawnNumbersEntity = drawnNumbersRepository.save(drawnNumbers);

		Tickets ticket = Tickets.builder().drawnId(drawnNumbersEntity.getId().toString())
			.lotteryBoxes(lotteryBoxes).superNumber(7).superSixNumber(4)
			.owner("38a334e6-93ab-41c6-bb54-ac12f3d04237").build();

		String uuid = lotteryService.playTicket(ticket);
		Optional<Tickets> byId = ticketsRepository.findById(UUID.fromString(uuid));
		assertTrue(byId.isPresent());
		assertEquals(ticket, byId.get());
		log.info(ticket.toString());
		lotteryEvaluationService.evaluateAllTickets(drawnNumbersEntity);
	}

}
