import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { RouterOutlet } from '@angular/router';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';
import { DoctorEditState } from './doctor-edit.state';

@Component({
  selector: 'app-doctor-edit',
  standalone: true,
  imports: [WidgetsModule, RouterOutlet],
  templateUrl: './doctor-edit.component.html',
  providers: [DoctorEditState]
})
export class DoctorEditComponent {

  id = input<number>()

  profile = computed(() => {
    return {
      id: this.state.details()?.doctor.id,
      name : this.state.details()?.doctor.name,
      image : this.state.details()?.doctor.profile,
      phone : this.state.details()?.doctor.phone,
      email: this.state.details()?.doctor.email
    }
  })

  constructor(client:DoctorClientService, private state:DoctorEditState) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => this.state.details.set(result))
      }
    }, {allowSignalWrites: true})
  }
}
