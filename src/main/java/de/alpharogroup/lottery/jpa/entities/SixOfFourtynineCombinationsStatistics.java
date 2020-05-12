package de.alpharogroup.lottery.jpa.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SixOfFourtynineCombinationsStatistics
{
	public static final String TABLE_NAME = "six_of_fourtynine_combinations_statistics";

	@Id
	Integer id;

	/** The set with the drawn lottery numbers. */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="owner", foreignKey = @ForeignKey(name = "fk_sofncs_six_of_fourtynine_combinations_id"))
	SixOfFourtynineCombinations lotteryNumbers;

	@Column
	int drawnCount;

}
