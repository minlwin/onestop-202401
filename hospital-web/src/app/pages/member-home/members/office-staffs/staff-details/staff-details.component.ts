import { Component, input } from '@angular/core';
import { DetailsComponent } from '../../../../details-component';
import { StaffClientService } from '../../../../../services/client/staff-client.service';

@Component({
  selector: 'app-staff-details',
  standalone: true,
  imports: [],
  templateUrl: './staff-details.component.html',
  styles: ``
})
export class StaffDetailsComponent extends DetailsComponent {

  id = input<number>()

  constructor(client:StaffClientService) {
    super(client)
  }
}
