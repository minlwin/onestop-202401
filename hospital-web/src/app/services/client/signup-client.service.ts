import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const URL = `${environment.url.member}/public/signup`

@Injectable({
  providedIn: 'root'
})
export class SignupClientService {

  constructor(private http:HttpClient) { }

  signUp(form:any) {
    return this.http.post<any>(URL, form)
  }
}
