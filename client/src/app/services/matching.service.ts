import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MatchedType } from 'src/app/models/matched';
import { API_URL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MatchingService {
  url: string = API_URL;

  constructor(private http: HttpClient) {
    this.url += "/matched" ;
   }

  getAll(): Observable<MatchedType[]>{
    return this.http.get(this.url) as Observable<MatchedType[]>;
  }

  getById(id: number): Observable<MatchedType[]>{
    return this.http.get(`${this.url}/${id}`) as Observable<MatchedType[]>;
  }

  post(matched: MatchedType): Observable<MatchedType> {
    return this.http.post<MatchedType>(this.url, matched);
  }

  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`)
  }
}