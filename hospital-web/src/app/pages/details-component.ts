import { effect, InputSignal, signal } from "@angular/core"
import { Observable } from "rxjs"

export abstract class DetailsComponent {

  abstract get id():InputSignal<number | undefined>

  details = signal<any>(undefined)

  constructor(client:DetailsSearchableClient) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()).subscribe(result => this.details.set(result))
      }
    }, {allowSignalWrites: true})
  }

}

export interface DetailsSearchableClient {
  findById(id:any):Observable<any>
}
