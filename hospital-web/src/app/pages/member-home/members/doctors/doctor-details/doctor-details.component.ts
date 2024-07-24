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
      name : this.doctor()?.name,
      image : this.doctor()?.profile,
      phone : this.doctor()?.phone,
      email: this.doctor()?.email
    }
  })

  constructor(client:DoctorClientService) {
    super(client)
  }
}

