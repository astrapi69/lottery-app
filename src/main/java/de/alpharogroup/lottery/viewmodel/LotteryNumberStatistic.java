package de.alpharogroup.lottery.viewmodel;

import de.alpharogroup.lottery.enums.LotteryGameType;
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
public class LotteryNumberStatistic
{
	LotteryGameType gameType;

	int drawnNumber;

	int drawnCount;
}
