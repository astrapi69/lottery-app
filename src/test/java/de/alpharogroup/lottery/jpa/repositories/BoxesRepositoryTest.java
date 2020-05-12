package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.drawing.DrawnLotteryNumbersExtensions;
import de.alpharogroup.lottery.enums.LotteryGameType;
import de.alpharogroup.lottery.jpa.entities.Boxes;
import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinations;
import de.alpharogroup.lottery.service.LotteryService;
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

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log
public class BoxesRepositoryTest
{


	@Autowired
	BoxesRepository boxesRepository;
	@Autowired
	TestEntityManager entityManagerDecorator;


	@SuppressWarnings("unused")
	@Autowired
	LotteryService lotteryService;

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void testFindNotEvaluatedBoxes() throws Exception
	{
		Boxes boxes;
		List<Boxes> notEvaluatedBoxes;
		SixOfFourtynineCombinations numbers;

		numbers = new SixOfFourtynineCombinations(DrawnLotteryNumbersExtensions.draw(6, 1, 49));
		boxes = boxesRepository
			.save(Boxes.builder().index(0).gameType(LotteryGameType.SIX_OF_FOURTYNINE_NORMAL)
				.numbers(numbers).build());
		log.info(boxes.toString() + ":" + boxes.hashCode());


		numbers = new SixOfFourtynineCombinations(DrawnLotteryNumbersExtensions.draw(6, 1, 49));
		boxes = boxesRepository
			.save(Boxes.builder().index(1).gameType(LotteryGameType.SIX_OF_FOURTYNINE_NORMAL)
				.numbers(numbers).build());
		log.info(boxes.toString() + ":" + boxes.hashCode());

		notEvaluatedBoxes = boxesRepository.findNotEvaluatedBoxes();

		assertEquals(notEvaluatedBoxes.size(), 2);
		Query query = entityManagerDecorator.getEntityManager()
			.createQuery("SELECT box FROM Boxes box WHERE box.winCategory IS NOT NULL");
		List<Boxes> resultList = query.getResultList();

		assertEquals(resultList.size(), 0);

	}

}