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
public class SixOfFourtynineCombination {

    Integer number1;
    
    Integer number2;
    
    Integer number3;
    
    Integer number4;
    
    Integer number5;
    
    Integer number6;

    Integer checksum;
}
