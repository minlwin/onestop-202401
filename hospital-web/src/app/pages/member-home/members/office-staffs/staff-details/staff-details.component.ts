import { Component, computed, effect, input } from '@angular/core';
import { DetailsComponent } from '../../../../details-component';
import { StaffClientService } from '../../../../../services/client/staff-client.service';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Information } from '../../../../../widgets/information-card/information-card.component';
import { consumerPollProducersForChange } from '@angular/core/primitives/signals';

@Component({
  selector: 'app-staff-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './staff-details.component.html',
  styles: ``
})
export class StaffDetailsComponent extends DetailsComponent {

  override id = input<number>()

  profile = computed(() => {
    return {
      name : this.details()?.name,
      image : undefined,
      phone : this.details()?.phone,
      email: this.details()?.email
    }
  })

  personalInfo = computed(() => {
    const array:Information[] = []

    if(this.details()) {
      const data = this.details()!
      array.push({label: 'Code', value: data.code})
      array.push({label: 'Name', value: data.name})
      array.push({label: 'Position', value: data.position})
      array.push({label: 'Assign Date', value: data.assignAt})
    }
    return array
  })

  department = computed(() => {
    const array:Information[] = []

    if(this.details()?.department) {
      const data = this.details()?.department
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
      array.push({label: 'Address', value: data.code})
      array.push({label: 'Quarter', value: data.name})
      array.push({label: 'Township', value: data.phone})
    }
    return array
  })

  constructor(client:StaffClientService) {
    super(client)

    effect(() => {
      console.log(`Staff ID is ${this.id()}`)
    })
  }
}
