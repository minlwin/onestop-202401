import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const URL = environment.url.location

@Injectable({
  providedIn: 'root'
})
export class LocationsClientService {

  constructor(private http:HttpClient) { }

  searchDivisions(form:any) {
    return this.http.get<any[]>(`${URL}/division`, {params: form})
  }

  searchDistrict(form:any) {
    return this.http.get<any[]>(`${URL}/district`, {params: form})
  }

  searchTownship(form:any) {
    return this.http.get<any[]>(`${URL}/township`, {params: form})
  }
}
