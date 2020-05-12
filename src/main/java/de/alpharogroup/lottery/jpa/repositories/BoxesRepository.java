package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.jpa.entities.Boxes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoxesRepository extends CrudRepository<Boxes, UUID>
{
	@Transactional
	List<Boxes> findNotEvaluatedBoxes();

}
