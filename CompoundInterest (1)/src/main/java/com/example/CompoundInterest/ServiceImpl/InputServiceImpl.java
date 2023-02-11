package com.example.CompoundInterest.ServiceImpl;

import com.example.CompoundInterest.Model.Input;
import com.example.CompoundInterest.Service.InputService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InputServiceImpl implements InputService {
  Logger logger = LoggerFactory.getLogger(InputService.class);

  @Override
  public BigDecimal getInterest(Input input) {
    BigDecimal amount =BigDecimal.valueOf(0);
    try {
      logger.info("inside getInterest method of InputServiceImpl Class" + this);

      double ans = (double)(1 + (double)(input.getRateOfInterest()/100) / input.getFreq());
      ans = Math.pow(ans, input.getTimeElapsed() * input.getFreq());
      amount=input.getPrincipal();
      amount = amount.multiply(new BigDecimal(ans));
      amount=amount.setScale(2, RoundingMode.UP);

    }
    catch(ArithmeticException ex){
      logger.error("TimeStamp: "+Instant.now().toString());
      System.out.println("TimeStamp: "+Instant.now().toString());
      System.out.println("Class Name: "+ex.getStackTrace()[0].getClassName());
      System.out.println("File Name: "+ex.getStackTrace()[0].getFileName());

      System.out.println("Method Name: "+ex.getStackTrace()[0].getMethodName());
      System.out.println("Line Number: "+ex.getStackTrace()[0].getLineNumber());
      throw new ArithmeticException("cannot divide by zero");
    }
    return amount;
  }
}
