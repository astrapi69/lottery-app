package de.alpharogroup.lottery.mapper;

import de.alpharogroup.bean.mapper.GenericMapper;
import de.alpharogroup.lottery.jpa.entities.DrawnNumbers;
import de.alpharogroup.lottery.viewmodel.Drawing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface DrawingMapper extends GenericMapper<DrawnNumbers, Drawing>
{
}
