package de.alpharogroup.lottery.viewmodel;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Drawing {

	/** The point of time when this drawn has been made */
	LocalDateTime drawnDate;

	/** The point of drawn time when this ticket is valid. This is the same as the drawnDate. */
	String drawnId;

	/** The number of the current drawn seventy seven. */
	Integer gameSeventySeven;

	/** The set with the drawn lottery numbers. */
	SixOfFourtynineCombination lotteryNumbers;

	/** The drawn super number. */
	Integer superNumber;

	/** The drawn super six number. */
	Integer superSixNumber;
}
