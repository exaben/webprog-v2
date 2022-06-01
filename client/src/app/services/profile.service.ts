import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProfileType } from 'src/app/models/profile';
import { API_URL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  url: string = API_URL;

  constructor(private http: HttpClient) {
    this.url += "/profile" ;
   }

  getAll(): Observable<ProfileType[]>{
    return this.http.get(this.url) as Observable<ProfileType[]>;
  }

  getById(id: number): Observable<ProfileType>{
    return this.http.get(`${this.url}/${id}`) as Observable<ProfileType>;
  }

  put(profile: ProfileType): Observable<ProfileType>{
    return this.http.put<ProfileType>(this.url, profile);
  }
}
