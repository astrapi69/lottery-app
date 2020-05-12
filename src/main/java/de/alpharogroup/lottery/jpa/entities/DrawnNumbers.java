package de.alpharogroup.lottery.jpa.entities;

import de.alpharogroup.db.entity.uniqueable.UUIDEntity;
import de.alpharogroup.lottery.drawings.DrawnLotteryNumbers;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The entity class {@link DrawnNumbers}. Similar to the class {@link DrawnLotteryNumbers} from
 * library.
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
public class DrawnNumbers extends UUIDEntity
{

	public static final String TABLE_NAME = "drawn_numbers";

	/** The point of time when this drawn has been made */
	@Column
	LocalDateTime drawnDate;

	/** The point of drawn time when this ticket is valid. This is the same as the drawnDate. */
	@Column
	String drawnId;

	/** The number of the current drawn seventy seven. */
	@Column
	Integer gameSeventySeven;

	/** The set with the drawn lottery numbers. */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="owner", unique = false, foreignKey = @ForeignKey(name = "fk_drawn_numbers_six_of_fourtynine_combinations_id"))
	SixOfFourtynineCombinations lotteryNumbers;

	/** The drawn super number. */
	@Column
	Integer superNumber;

	/** The drawn super six number. */
	@Column
	Integer superSixNumber;

}