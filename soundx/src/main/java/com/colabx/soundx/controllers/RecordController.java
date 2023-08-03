package com.colabx.soundx.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.colabx.soundx.repo.RecordRepo;
import com.colabx.soundx.service.RecordService;

import jakarta.servlet.http.HttpServletRequest;

import java.nio.file.Path;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.colabx.soundx.model.Record;


@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("record")
@RestController
public class RecordController {
       
    @Autowired
        RecordRepo recordRepo;

    @Autowired
    RecordService recordService;
   
    @PostMapping("/uploadAudio/{input}/{selectedType}")
    public ResponseEntity<Record> uploadAudio(@RequestParam("audioFile") MultipartFile audioFile ,@PathVariable String input,@PathVariable int selectedType ) throws IllegalStateException, IOException {
        
            return new ResponseEntity<>(recordService.creatRecord(audioFile, input, selectedType), HttpStatus.OK);
     
    }




    @GetMapping("/getAudio/{fileName}")
    public ResponseEntity<Resource> getRecord(@PathVariable String fileName) throws IOException {
       Resource resource = recordService.getRecord(fileName);
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       headers.setContentDispositionFormData("attachment", fileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    
    }

    

    @GetMapping("/getAllAudioRecordings")
    public ResponseEntity<List<Record>> getAllAudioRecordings() {
        return new ResponseEntity<>(recordService.getAllRecords(), HttpStatus.OK);
        
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteRecordById(@PathVariable Long id) {
        System.out.println("===================== id");
        return recordService.deleteRecordById(id);
    
    }

    
}
