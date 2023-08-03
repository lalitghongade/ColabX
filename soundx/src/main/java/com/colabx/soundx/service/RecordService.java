package com.colabx.soundx.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.colabx.soundx.model.Record;

public interface RecordService {
    public boolean deleteRecordById(Long id);

    public Record creatRecord( MultipartFile audioFile ,String input, int selectedType) throws IllegalStateException, IOException ;
    public List<Record> getAllRecords();

    Resource getRecord(String fileName) throws IOException;
}
