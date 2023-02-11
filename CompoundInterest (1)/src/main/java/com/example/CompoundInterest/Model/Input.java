package com.example.CompoundInterest.Model;

import com.example.CompoundInterest.Enum.InterestPeriod;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {

  BigDecimal principal;
  double rateOfInterest;
  InterestPeriod interestPeriod;
  int freq;
  int timeElapsed;

}
