import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { RecordApiCallsService } from '../record-api-calls.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.scss']
})
export class RecordComponent {

  @ViewChild('audioElement') audioElement!: ElementRef<HTMLAudioElement>;


 // audioPlayer: HTMLAudioElement;

  isRecording: boolean = false;
  mediaRecorder!: MediaRecorder;
  audioChunks: Blob[] = [];
  play :boolean =false
  constructor( private recordApi:RecordApiCallsService,private router:Router) { }

  ngOnInit() {
  
      // ... (other code)
   //   this.audioPlayer = this.audioPlayerRef.nativeElement;
    

  // this.getAllAudioRecordings()

    navigator.mediaDevices.getUserMedia({ audio: true })
      .then(stream => {
        this.mediaRecorder = new MediaRecorder(stream);
        this.mediaRecorder.ondataavailable = event => {
          if (event.data.size > 0) {
            this.audioChunks.push(event.data);
          }
        };
        this.mediaRecorder.onstop = () => {
          this.saveAudio();
        };
      })
      .catch(error => {
        console.error('Error accessing microphone:', error);
      });
  }

  startRecording() {
    this.audioChunks = [];
    this.mediaRecorder.start();
    this.isRecording = true;
    
   



    
  }

  stopRecording() {
    if (this.isRecording) {
      this.mediaRecorder.stop();
      this.isRecording = false;


      this.globalBlob = new Blob(this.audioChunks, { type: 'audio/wav' });
      this.globalUrl = URL.createObjectURL( this.globalBlob);
      console.log(this.globalBlob);
      console.log(this.globalUrl);
     
     
    
    }
  }
  globalUrl
  globalBlob
  saveAudio() {
    const audioBlob = new Blob(this.audioChunks, { type: 'audio/wav' });
    const audioUrl = URL.createObjectURL(audioBlob);
    this.audioElement.nativeElement.src = audioUrl;
    
    this.globalBlob=audioBlob
    this.globalUrl=audioUrl
    console.log(this.globalUrl);
    
    
  }

  recordData;any={}

  sendAudioToBackend() {
  this.recordData={
    name:this.textInput,
    type:this.selectedOption
  }

    const audioBlob = this.globalBlob
    const formData: FormData = new FormData();
    formData.append('audioFile', audioBlob, 'recorded_audio.wav');
    this.recordApi.saveRecord(formData,this.textInput,this.selectedOption).subscribe(
      (response) => {
        console.log(response);
        // Show a success message to the user
        console.log("succeess");
        alert("succesfully added Record");
        this.router.navigate(['/record-list']);
      },
      (error) => {
        console.error(error);
        // Show an error message to the user
        console.log("errrrr");
        alert("something went wrong !");

        

      }
    );                   
  
  }




  //recordings
//mic stuff
// isMicOn: boolean = false;
textInput: string = '';
selectedOption: number = 1;

// 


checkIfYouCanAddRecordToday(){
  
}

addTitle(){
  console.log(this.textInput);
  
  
}
}
