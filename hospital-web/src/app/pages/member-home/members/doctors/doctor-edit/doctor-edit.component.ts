import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { RouterOutlet } from '@angular/router';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';
import { Profile } from '../../../../../widgets/profile/profile.component';
import { DoctorEditState } from './doctor-edit.state';

@Component({
  selector: 'app-doctor-edit',
  standalone: true,
  providers: [
    DoctorEditState
  ],
  imports: [WidgetsModule, RouterOutlet],
  templateUrl: './doctor-edit.component.html',
  styles: ``
})
export class DoctorEditComponent {

  id = input<number>()
  profile = signal<Profile | undefined>(undefined)

  constructor(client:DoctorClientService, state:DoctorEditState) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => {
          this.profile.set({
            name: result?.doctor?.name,
            image: result?.doctor?.profile,
            phone: result?.doctor?.phone,
            email: result?.doctor?.email
          })
        })
      }
    })
  }
}
