import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { UserType } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url: string = API_URL;

  constructor(private http: HttpClient) { }

  login(user: any): Observable<UserType>{
    return this.http.post<UserType>(this.url + '/authentication',user);
  }
}
