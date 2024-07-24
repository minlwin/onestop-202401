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

  isPatient = computed(() => this.roles()?.filter(a => a == 'Patient').pop() != undefined)
  isOffice = computed(() => this.roles()?.filter(a => a == 'Office').pop() != undefined)
  isDoctor = computed(() => this.roles()?.filter(a => a == 'Doctor').pop() != undefined)
  isAdmin = computed(() => this.roles()?.filter(a => a == 'Admin').pop() != undefined)

  active = computed(() => this.roles()?.filter(role => role == 'Activated').length == 1)
  memberRole = computed(() => this.roles()?.filter(role => role != 'Activated').pop())

  login(user:LoginUser) {
    this.loginUser.set(user)
    // save to local storage
    localStorage.setItem(STORAGE_KEY, JSON.stringify(user))
  }

  logout() {
    this.loginUser.set(undefined)
    localStorage.clear()
  }

}

export interface LoginUser {
  name: string
  roles: string[]
  username: string
  accessToken: string
  refreshToken: string
}
