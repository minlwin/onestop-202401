import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { RouterOutlet } from '@angular/router';
import { StaffClientService } from '../../../../../services/client/staff-client.service';
import { Profile } from '../../../../../widgets/profile/profile.component';

@Component({
  selector: 'app-staff-edit',
  standalone: true,
  imports: [WidgetsModule, RouterOutlet],
  templateUrl: './staff-edit.component.html',
  styles: ``
})
export class StaffEditComponent {

  id = input<number>()

  profile = signal<Profile | undefined>(undefined)

  constructor(client:StaffClientService) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => {
          this.profile.set({
            id: result?.id,
            name: result?.name,
            image: result?.profile,
            phone: result?.phone,
            email: result?.email
          })
        })
      }
    })
  }
}
