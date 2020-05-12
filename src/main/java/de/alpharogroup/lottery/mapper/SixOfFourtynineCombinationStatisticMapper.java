package de.alpharogroup.lottery.mapper;

import de.alpharogroup.lottery.jpa.entities.SixOfFourtynineCombinationsStatistics;
import de.alpharogroup.lottery.viewmodel.SixOfFourtynineCombinationStatistic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface SixOfFourtynineCombinationStatisticMapper
{
    SixOfFourtynineCombinationStatisticMapper INSTANCE = Mappers.getMapper(SixOfFourtynineCombinationStatisticMapper.class);
    SixOfFourtynineCombinationStatistic entityToDto(SixOfFourtynineCombinationsStatistics entity);
}
