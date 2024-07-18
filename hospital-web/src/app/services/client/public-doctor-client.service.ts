import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PageInfo } from './utils';

const URL = `${environment.url.member}/doctors`

@Injectable({
  providedIn: 'root'
})
export class PublicDoctorClientService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<PageInfo>(URL, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${URL}/${id}`)
  }

}
