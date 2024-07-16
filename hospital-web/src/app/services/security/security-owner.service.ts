import { computed, Injectable, signal } from "@angular/core";

const STORAGE_KEY = "com.jdc.hospital.loginuser"

@Injectable({providedIn: 'root'})
export class SecurityOwner {

  private loginUser = signal<LoginUser | undefined>(undefined)

  constructor() {
    // restore login user from local storage
    const value = localStorage.getItem(STORAGE_KEY)

    if(value) {
      this.loginUser.set(JSON.parse(value))
    }
  }

  accessToken = computed(() => this.loginUser()?.accessToken)
  refreshToken = computed(() => this.loginUser()?.refreshToken)
  username = computed(() => this.loginUser()?.username)
  roles = computed(() => this.loginUser()?.roles)
  loginUserName = computed(() => this.loginUser()?.name)
  active = computed(() => this.roles()?.filter(role => role == 'Activated').length == 1)

  login(user:LoginUser) {
    this.loginUser.set(user)
    // save to local storage
    localStorage.setItem(STORAGE_KEY, JSON.stringify(user))
  }

  logout() {
    this.loginUser.set(undefined)
  }

}

export interface LoginUser {
  name: string
  roles: string[]
  username: string
  accessToken: string
  refreshToken: string
}
