package de.alpharogroup.lottery.jpa.repositories;

import de.alpharogroup.lottery.jpa.entities.DrawnNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DrawnNumbersRepository extends JpaRepository<DrawnNumbers, UUID>
{

}
