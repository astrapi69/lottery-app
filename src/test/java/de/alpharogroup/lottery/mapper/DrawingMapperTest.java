package de.alpharogroup.lottery.mapper;

import de.alpharogroup.lottery.jpa.entities.DrawnNumbers;
import de.alpharogroup.lottery.viewmodel.Drawing;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawingMapperTest
{

	@Autowired
	DrawingMapper drawingMapper;
	@Ignore // TODO
	@Test
	public void testEntityToDto() {
		Drawing drawing = drawingMapper.toDto(DrawnNumbers.builder().build());
		assertNotNull(drawing);
	}

}
