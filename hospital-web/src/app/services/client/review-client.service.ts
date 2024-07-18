import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PageInfo } from './utils';

const URL = `${environment.url.review}/review`

@Injectable({
  providedIn: 'root'
})
export class ReviewClientService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<PageInfo>(URL, {params: form})
  }

  create(form:any) {
    return this.http.put<any>(URL, form)
  }


}
