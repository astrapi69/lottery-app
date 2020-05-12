package de.alpharogroup.lottery.jpa.entities;

import de.alpharogroup.lottery.enums.LotteryGameType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@TypeDefs({
	@TypeDef(name = "gameTypeConverter", typeClass = de.alpharogroup.db.postgres.usertype.PGEnumUserType.class, parameters = {
		@org.hibernate.annotations.Parameter(name = "enumClassName", value = "de.alpharogroup.lottery.enums.LotteryGameType") }) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LotteryNumberStatistics
{
	public static final String TABLE_NAME = "lottery_number_statistics";

	@Id
	Long id;
	/** The lottery game type. */
	@Enumerated
	@Column
	@Type(type = "gameTypeConverter") LotteryGameType gameType;

	@Column
	int drawnNumber;

	@Column
	int drawnCount;

}
