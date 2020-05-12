package de.alpharogroup.lottery.jpa.entities;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.set.SetFactory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * The entity class {@link SixOfFourtynineCombinations} can hold a combination of the lottery game
 * six of fourtynine as you can presume from the name.
 */
@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SixOfFourtynineCombinations
{
	public static final String TABLE_NAME = "six_of_fourtynine_combinations";
	@Id
	Integer id;
	@Column
	Integer number1;
	@Column
	Integer number2;
	@Column
	Integer number3;
	@Column
	Integer number4;
	@Column
	Integer number5;
	@Column
	Integer number6;
	@Column
	Integer checksum;

	public SixOfFourtynineCombinations(@NonNull List<Integer> combination)
	{
		if (combination.size() == 6)
		{
			Collections.sort(combination, Comparator.<Integer>naturalOrder());
			number1 = combination.get(0);
			number2 = combination.get(1);
			number3 = combination.get(2);
			number4 = combination.get(3);
			number5 = combination.get(4);
			number6 = combination.get(5);
			checksum = toSet().stream().mapToInt(Integer::intValue).sum();
		}
	}

	public SixOfFourtynineCombinations(@NonNull Set<Integer> combination)
	{
		this(ListFactory.newArrayList(combination));
	}

	public Set<Integer> toSet() {
		return SetFactory.newLinkedHashSet(number1, number2, number3, number4, number5, number6);
	}

}
