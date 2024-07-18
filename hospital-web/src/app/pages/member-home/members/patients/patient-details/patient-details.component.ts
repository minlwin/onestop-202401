import { Component, input } from '@angular/core';
import { PatientClientService } from '../../../../../services/client/patient-client.service';
import { DetailsComponent } from '../../../../details-component';

@Component({
  selector: 'app-patient-details',
  standalone: true,
  imports: [],
  templateUrl: './patient-details.component.html',
  styles: ``
})
export class PatientDetailsComponent extends DetailsComponent{

  id = input<number>()

  constructor(client:PatientClientService) {
    super(client)
  }
}
