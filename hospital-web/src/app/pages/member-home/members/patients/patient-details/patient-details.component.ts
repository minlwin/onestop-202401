import { Component, computed, input } from '@angular/core';
import { PatientClientService } from '../../../../../services/client/patient-client.service';
import { DetailsComponent } from '../../../../details-component';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { Information } from '../../../../../widgets/information-card/information-card.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-patient-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './patient-details.component.html',
  styles: ``
})
export class PatientDetailsComponent extends DetailsComponent{

  id = input<number>()

  patient = computed(() => this.details()?.patient)

  personalInfo = computed(() => {
    const array:Information[] = []

    if(this.patient()) {
      const data = this.patient()!
      array.push({label: 'Code', value: data.code})
      array.push({label: 'Name', value: data.name})
      array.push({label: 'Gender', value: data.gender || 'Not Define'})
      array.push({label: 'Date of Birth', value: data.dob || 'Not Define'})
      array.push({label: 'RegisteredAt', value: data.registerAt || 'Not Define'})
    }
    return array
  })

  address = computed(() => {
    const array:Information[] = []

    if(this.details()?.address) {
      const data = this.details()?.address
      array.push({label: 'Building', value: data.code})
      array.push({label: 'Street', value: data.code})
      array.push({label: 'Quarter', value: data.name})
      array.push({label: 'Township', value: data.phone})
    }
    return array
  })

  profile = computed(() => {
    return {
      id: this.patient()?.id,
      name : this.patient()?.name,
      image : undefined,
      phone : this.patient()?.phone,
      email: this.patient()?.email
    }
  })

  constructor(client:PatientClientService) {
    super(client)
  }
}
