import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { SecurityOwner } from "../security/security-owner.service";
import { environment } from "../../../environments/environment";
import { Profile } from "../../widgets/profile/profile.component";

const URL = `${environment.url.member}/profile`

@Injectable({providedIn: 'root'})
export class ProfileClient {

  constructor(private http:HttpClient, private security:SecurityOwner) {}

  getProfile() {
    return this.http.get<Profile>(URL, {params: {username: this.security.username()!}})
  }

  getDoctorProfile() {
    return this.http.get<Profile>(`${URL}/doctor`, {params: {username: this.security.username()!}})
  }

  getPatientProfile() {
    return this.http.get<Profile>(`${URL}/patient`, {params: {username: this.security.username()!}})
  }

  getOfficeProfile() {
    return this.http.get<Profile>(`${URL}/office`, {params: {username: this.security.username()!}})
  }

}
