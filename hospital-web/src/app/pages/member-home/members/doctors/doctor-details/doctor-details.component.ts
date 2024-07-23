import { Component, computed, input } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DetailsComponent } from '../../../../details-component';
import { Information } from '../../../../../widgets/information-card/information-card.component';

@Component({
  selector: 'app-doctor-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './doctor-details.component.html'
})
export class DoctorDetailsComponent extends DetailsComponent {

  id = input<number>()

  doctor = computed(() => this.details()?.doctor)

  profile = computed(() => {
    return {
      id: this.doctor()?.id,
      name : this.doctor()?.name,
      image : this.doctor()?.profile,
      phone : this.doctor()?.phone,
      email: this.doctor()?.email
    }
  })

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

  constructor(client:DoctorClientService) {
    super(client)
  }
}

