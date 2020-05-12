package de.alpharogroup.lottery.mapper;

import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinations;
import de.alpharogroup.lottery.viewmodel.SixOfFourtynineCombination;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface SixOfFourtynineCombinationMapper {

    SixOfFourtynineCombinationMapper INSTANCE = Mappers.getMapper(SixOfFourtynineCombinationMapper.class);
    SixOfFourtynineCombination entityToDto(SixOfFourtynineCombinations entity);
}
