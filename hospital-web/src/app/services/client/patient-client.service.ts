import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { PageInfo } from './utils';

const URL = `${environment.url.member}/patients`
const PROFILE_URL = `${environment.url.member}/patient/profile`

@Injectable({
  providedIn: 'root'
})
export class PatientClientService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<PageInfo>(URL, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${URL}/${id}`)
  }

  updateInfo(id:number, form:any) {
    return this.http.put<any>(`${PROFILE_URL}/${id}`, form)
  }

  updateAddress(id:number, form:any) {
    return this.http.put<any>(`${PROFILE_URL}/${id}/address`, form)
  }
}
