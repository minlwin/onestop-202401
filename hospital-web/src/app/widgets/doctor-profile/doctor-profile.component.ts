import { Component, computed, input } from '@angular/core';
import { Information } from '../information-card/information-card.component';
import { SecurityOwner } from '../../services/security/security-owner.service';

@Component({
  selector: 'app-doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styles: ``
})
export class DoctorProfileComponent {

  details = input<any>()

  constructor(public security:SecurityOwner) {}

  doctor = computed(() => this.details()?.doctor)

  personalInfo = computed(() => {
    const array:Information[] = []

    if(this.details()?.doctor) {
      const data = this.details()?.doctor
      array.push({label: 'Code', value: data.code})
      array.push({label: 'Name', value: data.name})
      array.push({label: 'Degree', value: data.degree})
      array.push({label: 'Assign Date', value: data.assignAt})
    }
    return array
  })

  department = computed(() => {
    const array:Information[] = []

    if(this.details()?.doctor?.department) {
      const data = this.details()?.doctor?.department
      array.push({label: 'Department Code', value: data.code})
      array.push({label: 'Department Name', value: data.name})
      array.push({label: 'Department Phone', value: data.phone})
    }
    return array
  })

  address = computed(() => {
    const array:Information[] = []

    if(this.details()?.address) {
      const data = this.details()?.address
      array.push({label: 'Address', value: data.building})
      array.push({label: 'Quarter', value: data.quarter})
      array.push({label: 'Township', value: `${data.township}, ${data.division}`})
    }
    return array
  })

  sections = computed(() => this.details()?.sections || [])

}
