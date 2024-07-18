import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { formData, PageInfo } from './utils';

const URL = `${environment.url.member}/doctors`

@Injectable({
  providedIn: 'root'
})
export class DoctorClientService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<PageInfo>(URL, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${URL}/${id}`)
  }

  create(form:any) {
    return this.http.post<any>(URL, form)
  }

  update(id:number, form:any) {
    return this.http.put<any>(`${URL}/${id}/info`, form)
  }

  updateStatus(id:number, form:any) {
    return this.http.put<any>(`${URL}/${id}/status`, form)
  }

  updateDepartment(id:number, form:any) {
    return this.http.put<any>(`${URL}/${id}/department`, form)
  }

  updateAddress(id:number, form:any) {
    return this.http.put<any>(`${URL}/${id}/address`, form)
  }

  updateSections(id:number, form:any) {
    return this.http.put<any>(`${URL}/${id}/section`, form)
  }

  updateProfileImage(id:number, image:any) {
    return this.http.put<any>(`${URL}/${id}/profile`, formData({'file': image}))
  }
}
