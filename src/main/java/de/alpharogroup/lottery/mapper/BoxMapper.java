package de.alpharogroup.lottery.mapper;

import de.alpharogroup.lottery.jpa.entities.Boxes;
import de.alpharogroup.lottery.viewmodel.Box;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface BoxMapper {
    BoxMapper INSTANCE = Mappers.getMapper(BoxMapper.class);
    Box entityToDto(Boxes entity);
}
