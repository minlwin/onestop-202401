import { Component, effect, input, signal } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { LocationsClientService } from '../../services/client/locations-client.service';

@Component({
  selector: 'app-address-edit',
  templateUrl: './address-edit.component.html',
  styles: ``
})
export class AddressEditComponent {

  form = input.required<FormGroup>()
  divisions = signal<any[]>([])
  districts = signal<any[]>([])
  townships = signal<any[]>([])


  constructor(client:LocationsClientService) {
    client.searchDivisions({}).subscribe(result => this.divisions.set(result))

    effect(() => {
      if(this.form() != undefined) {
        this.form().get('divisionId')?.valueChanges.subscribe(value => {
          this.districts.set([])
          this.form().patchValue({'districtId' : ''})

          if(value) {
            client.searchDistrict({divisionId: value}).subscribe(result => this.districts.set(result))
          }
        })

        this.form().get('districtId')?.valueChanges.subscribe(value => {
          this.townships.set([])
          this.form().patchValue({'townshipId' : ''})

          if(value) {
            client.searchTownship({districtId: value}).subscribe(result => this.townships.set(result))
          }
        })


      }
    })
  }
}
