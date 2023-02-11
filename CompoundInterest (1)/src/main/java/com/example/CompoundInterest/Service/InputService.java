package com.example.CompoundInterest.Service;

import com.example.CompoundInterest.Model.Input;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.stereotype.Service;

@Service
public interface InputService {


  BigDecimal getInterest(Input input);
}
