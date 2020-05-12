package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.jpa.entities.Tickets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketsRepository extends CrudRepository<Tickets, UUID>
{
	@Query(value = "SELECT t from Tickets t where t.drawnId = :drawnId")
	List<Tickets> findAllOfDrawnDate(@Param("drawnId") String drawnId);
}
