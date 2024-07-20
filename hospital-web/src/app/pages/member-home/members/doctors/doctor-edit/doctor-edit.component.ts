import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { RouterOutlet } from '@angular/router';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';

@Component({
  selector: 'app-doctor-edit',
  standalone: true,
  imports: [WidgetsModule, RouterOutlet],
  templateUrl: './doctor-edit.component.html',
  styles: ``
})
export class DoctorEditComponent {

  id = input<number>()
  details = signal<any>(undefined)

  profile = computed(() => {
    return {
      name : this.details()?.doctor.name,
      image : this.details()?.doctor.profile,
      phone : this.details()?.doctor.phone,
      email: this.details()?.doctor.email
    }
  })

  constructor(client:DoctorClientService) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => this.details.set(result))
      }
    }, {allowSignalWrites: true})
  }
}
