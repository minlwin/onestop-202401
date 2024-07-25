import { Injectable, signal } from "@angular/core"

@Injectable({providedIn: 'root'})
export class AfterLoginNavigationSate {
  navigation = signal<NavigationState | undefined>(undefined)
}

export interface NavigationState {
  url: string[]
  queryParams : any
}
