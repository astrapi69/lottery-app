package de.alpharogroup.lottery.viewmodel;

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
public class SixOfFourtynineCombinationStatistic
{
    SixOfFourtynineCombination owner;
    int drawnCount;
}
