package com.colabx.soundx.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import com.colabx.soundx.model.Record;

import com.colabx.soundx.repo.RecordRepo;
import com.colabx.soundx.service.RecordService;

import jakarta.transaction.Transactional;
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepo recordRepo;

    @Transactional
    @Override
    public boolean deleteRecordById(Long id) {
        Record record = recordRepo.findRecordById(id); 
        System.out.println("===================="+record);
//todo delete file from storage
        boolean status;
        Path audioFilePath = Paths.get("C:\\Users\\Prime\\Documents\\stuff\\CoLabx\\soundx\\src\\main\\resources\\storedrecords\\");

    try {
      Path file = audioFilePath.resolve(record.getFilename());
      //System.out.println(file.exists());

        Files.deleteIfExists(file);
        System.out.println("status=================");
        recordRepo.deleteById(id);
      //  return status;
      return true;

    } catch (IOException e) {
        System.out.println(e.getMessage());
        return false;
     // throw new RuntimeException("Error: " + e.getMessage());
    }
  

//then delete in db
   

    
 }
    

    @Override
    public Record creatRecord( MultipartFile audioFile ,String input, int selectedType) throws IllegalStateException, IOException {
        
            String name = "ColabX"+Math.random()+audioFile.getOriginalFilename();
                // Save the audio file to the server
            // Replace "/path/to/your/audio/storage/directory" with the actual path where you want to store the audio files
           
            String filePath = "C:\\Users\\Prime\\Documents\\stuff\\CoLabx\\soundx\\src\\main\\resources\\storedrecords\\";
           // String realPathtoUploads =  request.getServletContext().getRealPath("\\storedrecords\\");

           // audioFile.transferTo(new File(realPathtoUploads+audioFile.getOriginalFilename()));
            File file1 = new File(filePath, name);
            //if(!file1.exists())
            //    file1.createNewFile();
            audioFile.transferTo(file1);
            System.out.println("===================================="+file1.exists());

            
            // Save the audio recording metadata to the database
            
            Record record = new Record();
            record.setFilename(name);
            record.setName(input);
            record.setType(selectedType);
           return recordRepo.save(record);
       
    }

    @Override
    public List<Record> getAllRecords() {
       return recordRepo.findAll();
    }

    @Override
    public Resource getRecord(String fileName) throws IOException {
         // Replace "/path/to/your/audio/storage/directory" with the actual path where audio files are stored on the server
        Path audioFilePath = Paths.get("C:\\Users\\Prime\\Documents\\stuff\\CoLabx\\soundx\\src\\main\\resources\\storedrecords\\", fileName);
       
       // Path audioFilePath = Paths.get(request.getServletContext().getRealPath("\\storedrecords\\"), fileName);
        System.out.println(audioFilePath);
    
        //String realPathtoUploads =  request.getServletContext().getRealPath("\\storedrecords\\");

        byte[] audioBytes = Files.readAllBytes(audioFilePath);
        ByteArrayResource resource = new ByteArrayResource(audioBytes);

       
        return resource;
    }

    
    
}
