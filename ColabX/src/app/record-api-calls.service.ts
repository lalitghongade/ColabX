import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RecordApiCallsService {
  private backendUrl = 'http://localhost:8080/record/';

  constructor(private http :HttpClient) { }

  saveRecord(formData,input,selectedType){
    return  this.http.post<any>(this.backendUrl+'uploadAudio/'+input+"/"+selectedType, formData);
  }

  getAllRecords(){
    return this.http.get<any>(this.backendUrl + 'getAllAudioRecordings');
  }


  deleteRecordById(id){
    return this.http.delete(this.backendUrl+"delete/"+id);

  }
}
