import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { formData } from './utils';

const URL = environment.url.auth

@Injectable({
  providedIn: 'root'
})
export class AuthClientService {

  constructor(private http:HttpClient) { }

  generateToken(form:any) {
    return this.http.post<any>(`${URL}/token/generate`, formData(form))
  }

  refreshToken(form:any) {
    return this.http.post<any>(`${URL}/token/refresh`, formData(form))
  }
}
