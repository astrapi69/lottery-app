package de.alpharogroup.lottery.mapper;

import de.alpharogroup.lottery.jpa.entities.Tickets;
import de.alpharogroup.lottery.viewmodel.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface TicketMapper
{
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    Ticket entityToDto(Tickets entity);
}
