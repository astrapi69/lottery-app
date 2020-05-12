package de.alpharogroup.lottery.jpa.entities;

import de.alpharogroup.db.entity.uniqueable.UUIDEntity;
import de.alpharogroup.lottery.enums.LotteryGameType;
import de.alpharogroup.lottery.wincategories.LotteryWinCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

/**
 * The entity class {@link Boxes}. Similar to the class
 * {@link de.alpharogroup.lottery.box.LotteryBox} from library.
 */
@Entity
@TypeDefs({
		@TypeDef(name = "gameTypeConverter", typeClass = de.alpharogroup.db.postgres.usertype.PGEnumUserType.class, parameters = {
				@org.hibernate.annotations.Parameter(name = "enumClassName", value = "de.alpharogroup.lottery.enums.LotteryGameType") }),
		@TypeDef(name = "winCategoryConverter", typeClass = de.alpharogroup.db.postgres.usertype.PGEnumUserType.class, parameters = {
				@org.hibernate.annotations.Parameter(name = "enumClassName", value = "de.alpharogroup.lottery.wincategories.LotteryWinCategory") }) })
@NamedQueries({
		@NamedQuery(name = Boxes.NQ_FIND_NOT_EVALUATED_BOXES, query = "SELECT box FROM Boxes box WHERE box.winCategory IS NULL") })
@Table
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Boxes extends UUIDEntity
{

	public static final String TABLE_NAME = "boxes";

	public static final String NQ_FIND_NOT_EVALUATED_BOXES = "Boxes." + "findNotEvaluatedBoxes";
	/** The lottery game type. */
	@Enumerated
	@Column
	@Type(type = "gameTypeConverter")
	LotteryGameType gameType;

	/** The index of this box in the lottery ticket */
	@Column
	int index;

	/** The lottery numbers. */
	@ManyToOne
	@JoinColumn(name = "numbers_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_boxes_six_of_fourtynine_combinations_id"))
	SixOfFourtynineCombinations numbers;

	/** The lottery win category. */
	@Enumerated
	@Column
	@Type(type = "winCategoryConverter")
	LotteryWinCategory winCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ticket_uuid", foreignKey = @ForeignKey(name = "fk_boxes_tickets_uuid"))
	Tickets ticket;

}
