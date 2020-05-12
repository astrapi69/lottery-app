package de.alpharogroup.lottery.viewmodel;

import de.alpharogroup.lottery.enums.LotteryGameType;
import de.alpharogroup.lottery.wincategories.LotteryWinCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Box {

	/** The lottery game type. */
	LotteryGameType gameType;

	/** The index of this box in the lottery ticket */
	Integer index;

	/** The selected numbers. */
	SixOfFourtynineCombination numbers;

	/** The lottery win category. */
	LotteryWinCategory winCategory;

}
