package de.alpharogroup.lottery.mapper;

import de.alpharogroup.lottery.jpa.entities.LotteryNumberStatistics;
import de.alpharogroup.lottery.viewmodel.LotteryNumberStatistic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface LotteryNumberStatisticMapper
{
    LotteryNumberStatisticMapper INSTANCE = Mappers.getMapper(LotteryNumberStatisticMapper.class);
    LotteryNumberStatistic entityToDto(LotteryNumberStatistics entity);
}
