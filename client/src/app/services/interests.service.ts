import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InterestType } from 'src/app/models/interest';
import { API_URL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InterestsService {
  url: string = API_URL;

  constructor(private http: HttpClient) {
    this.url += "/interests" ;
   }

  getAll(): Observable<InterestType[]>{
    return this.http.get(this.url) as Observable<InterestType[]>;
  }

  getById(id: number): Observable<InterestType[]>{
    return this.http.get(`${this.url}/${id}`) as Observable<InterestType[]>;
  }
}
