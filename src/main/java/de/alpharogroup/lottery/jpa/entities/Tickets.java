package de.alpharogroup.lottery.jpa.entities;

import de.alpharogroup.db.entity.uniqueable.UUIDEntity;
import de.alpharogroup.lottery.ticket.LotteryTicket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity class {@link Tickets}. Similar to the class {@link LotteryTicket} from library.
 */
@Entity
@Table
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "seq_"
	+ Tickets.TABLE_NAME, initialValue = 1, allocationSize = 1)
public class Tickets extends UUIDEntity
{
	public static final String TABLE_NAME = "tickets";

	/** The point of drawn time when this ticket is valid */
	@Column
	String drawnId;

	/** The number of the game seventy seven. */
	@Column
	Integer gameSeventySeven;

	/** The played lottery boxes. */
	@Builder.Default
	@OneToMany(mappedBy="ticket",
		cascade = CascadeType.ALL,
		orphanRemoval = true)
	Set<Boxes> lotteryBoxes = new HashSet<>();

	/** The user the own this ticket. */
	@Column
	String owner;

	/** The super number. */
	@Column
	Integer superNumber;

	/** The super six number. */
	@Column
	Integer superSixNumber;

}
