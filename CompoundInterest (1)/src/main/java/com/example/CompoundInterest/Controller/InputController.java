package com.example.CompoundInterest.Controller;

import static java.lang.String.*;
import static java.lang.System.out;

import com.example.CompoundInterest.Model.Input;
import com.example.CompoundInterest.Service.InputService;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InputController {

  @Autowired
  InputService inputService;

  Logger logger = LoggerFactory.getLogger(InputController.class);

  @GetMapping("/interest")
  public ResponseEntity getInterest(@RequestBody Input input){
    logger.info("inside getInterest Class"+this);
    return new ResponseEntity(inputService.getInterest(input), HttpStatus.OK);
  }

  @GetMapping("/monitoring")
  public String getMonitoring(){
    logger.info("inside getInterest Class"+this);
    String timeStamp="TimeStamp "+Instant.now().toString()+'\n';
    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    String memory= '\n'+
        "Memory usage"+'\n'+format("Initial memory: %.2f GB",
        (double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824) +'\n'+
        format("Used heap memory: %.2f GB",
        (double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824)+'\n'+
    format("Max heap memory: %.2f GB",
        (double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824)+'\n'+
    format("Committed memory: %.2f GB",
        (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824)+'\n';

    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    String CPUusage='\n'+"CPUusage"+'\n';
    int totalCPUtime=0;
    for(Long threadID : threadMXBean.getAllThreadIds()) {
      ThreadInfo info = threadMXBean.getThreadInfo(threadID);
      CPUusage += '\n'+"Thread name: " + info.getThreadName()+'\n'+
      "Thread State: " + info.getThreadState()+'\n'+
        format("CPU time: %s ns",
            threadMXBean.getThreadCpuTime(threadID))+'\n';
      totalCPUtime+=threadMXBean.getThreadCpuTime(threadID);
    }

    return "COMPOUND INTEREST CALCULATOR "+timeStamp+this.getClass()+memory+"Total CPU time = "+totalCPUtime+"ns" ;

  }

}
