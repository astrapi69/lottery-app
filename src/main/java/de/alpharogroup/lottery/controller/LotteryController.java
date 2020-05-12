package de.alpharogroup.lottery.controller;

import de.alpharogroup.lottery.jpa.entities.DrawnNumbers;
import de.alpharogroup.lottery.mapper.DrawingMapper;
import de.alpharogroup.lottery.service.LotteryDrawService;
import de.alpharogroup.lottery.service.LotteryService;
import de.alpharogroup.lottery.service.LotteryStatisticsService;
import de.alpharogroup.lottery.viewmodel.Drawing;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

@RestController
@RequestMapping("v1/lottery")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LotteryController
{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    DrawingMapper drawingMapper;

    LotteryService lotteryService;
    LotteryDrawService lotteryDrawService;
    LotteryStatisticsService lotteryStatisticsService;

    /**
     * statistics
     */
    @RequestMapping("/statistics")
    public void statistics()
    {
        lotteryStatisticsService.startStatistics(9999);
    }

    /**
     * statistics
     */
    @RequestMapping("/newdraw")
    public ResponseEntity<Drawing> newDrawOfLotteryNumbers(){
        DrawnNumbers drawnNumbers = lotteryDrawService.drawNewLotteryNumbers();
        Drawing drawing = getMapper().apply(drawnNumbers);
        return ResponseEntity.status(drawing !=null ? HttpStatus.OK.value(): HttpStatus.BAD_REQUEST.value()).body(drawing);
    }

    protected Function<DrawnNumbers, Drawing> getMapper() {
        return drawingMapper::toDto;
    }

}
