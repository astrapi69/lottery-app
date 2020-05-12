package de.alpharogroup.lottery.viewmodel;

import java.util.Set;

public class Ticket
{

	/** The point of drawn time when this ticket is valid */
	String drawnId;

	/** The number of the game seventy seven. */
	Integer gameSeventySeven;

	/** The played lottery boxes. */
	Set<Box> lotteryBoxes;

	/** The user the own this ticket. */
	String owner;

	/** The super number. */
	Integer superNumber;

	/** The super six number. */
	Integer superSixNumber;
}
