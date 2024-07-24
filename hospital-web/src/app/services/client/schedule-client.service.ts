import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { PageInfo } from './utils';

const URL = `${environment.url.appointment}/schedules`

@Injectable({
  providedIn: 'root'
})
export class ScheduleClientService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<PageInfo>(URL, {params: form})
  }

}
