package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SixOfFourtynineCombinationsRepository extends JpaRepository<SixOfFourtynineCombinations, Integer>
{

	Optional<SixOfFourtynineCombinations> findDistinctByNumber1AndNumber2AndNumber3AndNumber4AndNumber5AndNumber6(Integer firstNumber,
		Integer secondNumber, Integer thrirdNumber, Integer fourthNumber,Integer fifthNumber, Integer sixthNumber);
}
