import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';
import { DetailsComponent } from '../../../../details-component';

@Component({
  selector: 'app-doctor-details',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './doctor-details.component.html',
  styles: ``
})
export class DoctorDetailsComponent extends DetailsComponent{

  id = input<number>()

  constructor(client:DoctorClientService) {
    super(client)
  }
}
