import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { PageInfo } from './utils';

const URL = `${environment.url.member}/departments`

@Injectable({
  providedIn: 'root'
})
export class DepartmentClientService {

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
    return this.http.put<any>(`${URL}/${id}`, form)
  }
}
