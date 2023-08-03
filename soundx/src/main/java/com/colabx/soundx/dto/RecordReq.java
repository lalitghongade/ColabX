package com.colabx.soundx.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class RecordReq {
    
  private String name;

  private String type; 
  private String data;
  private LocalDate localDate;


}
