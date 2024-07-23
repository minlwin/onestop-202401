import { Component, input, signal } from '@angular/core';
import { DoctorItem } from '../../../widgets/doctor-grid-item/doctor-grid-item.component';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { PublicDoctorClientService } from '../../../services/client/public-doctor-client.service';

@Component({
  selector: 'app-top-page',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './top-page.component.html',
  styles: ``
})
export class TopPageComponent {

  showCover = input<boolean | undefined>()
  doctors = signal<DoctorItem[]>([])

  constructor(client:PublicDoctorClientService) {
    client.search({}).subscribe(result => this.doctors.set(result))
  }

}
