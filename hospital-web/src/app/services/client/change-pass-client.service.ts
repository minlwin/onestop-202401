import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const URL = `${environment.url.member}/change-pass`

@Injectable({
  providedIn: 'root'
})
export class ChangePassClientService {

  constructor(private http:HttpClient) { }

  changePass(form:any) {
    return this.http.post<any>(URL, form)
  }
}
