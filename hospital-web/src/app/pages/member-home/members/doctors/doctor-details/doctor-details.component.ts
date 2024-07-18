import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';

@Component({
  selector: 'app-doctor-details',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './doctor-details.component.html',
  styles: ``
})
export class DoctorDetailsComponent {

  id = input<number>()
  details = signal<any>(undefined)

  constructor(client:DoctorClientService) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => {
          this.details.set(result)
        })
      }
    }, {allowSignalWrites: true})
  }
}
