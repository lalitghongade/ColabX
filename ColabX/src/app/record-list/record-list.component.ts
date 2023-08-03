import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { RecordApiCallsService } from '../record-api-calls.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Router } from '@angular/router';

@Component({
  selector: 'app-record-list',
  templateUrl: './record-list.component.html',
  styleUrls: ['./record-list.component.scss']
})
export class RecordListComponent implements OnInit {
  @ViewChild('audioPlayer') audioPlayerRef !: ElementRef<HTMLAudioElement>;



  ngOnInit(): void {
   this.getAllRecords();
    
  }



constructor(private recordApi:RecordApiCallsService, ){}

audioRecordings
//getAll reccordings
getAllRecords(){
   this.recordApi.getAllRecords().subscribe(data=>{
    this.audioRecordings=data;
    console.log(data);
    
   },err=>{
    console.log(err);
    
   })
}

audioUrl
playRecord(fileName: string) {
  this.audioUrl = `http://localhost:8080/record/getAudio/${fileName}`; // Replace with the actual backend endpoint to fetch the audio file by its name
  console.log(this.audioUrl);
  this.audioPlayerRef.nativeElement.src = this.audioUrl;
  this.audioPlayerRef.nativeElement.load();
  this.audioPlayerRef.nativeElement.play();

 
}

delete(id){
  console.log(id);
  console.log("delete called");
  this.recordApi.deleteRecordById(id).subscribe(data=>{
    console.log(data);
    this.getAllRecords();
    this.audioUrl=null


  },err=>{
    console.log(err);
    
  })
  
}

}
