import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageType } from 'src/app/models/message';
import { API_URL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  url: string = API_URL;
  contactId!: number;

  constructor(private http: HttpClient) {
    this.url += "/messages" ;
   }

  getById(senderId: number, receivedId: number): Observable<MessageType[]>{
    return this.http.get(`${this.url}/${senderId}/${receivedId}`) as Observable<MessageType[]>;
  }

  post(message: MessageType): Observable<MessageType> {
    return this.http.post<MessageType>(this.url,message);
  }

  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`)
  }
}
