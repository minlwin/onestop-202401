import { effect, InputSignal } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { Observable } from "rxjs";

export abstract class EditableComponent {

  abstract get id():InputSignal<number | undefined>
  abstract get form():FormGroup

  abstract onSaved(result:any):void
  abstract extractFromValue(details:any):any

  constructor(private client:FindAndSaveEnableClient) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()).subscribe(details => this.form.patchValue(this.extractFromValue(details)))
      }
    }, {allowSignalWrites: true})
  }

  save() {
    if(this.form.valid) {
      const request = this.id() ? this.client.update(this.id(), this.form.value) : this.client.create(this.form.value)
      request.subscribe(result => this.onSaved(result))
    }
  }
}

export interface FindAndSaveEnableClient {

  findById(id:any):Observable<any>

  create(form:any):Observable<any>

  update(id:any, form:any):Observable<any>
}
