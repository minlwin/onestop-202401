import { Component, effect, input, signal } from '@angular/core';
import { DoctorItem } from '../../../widgets/doctor-grid-item/doctor-grid-item.component';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { PublicDoctorClientService } from '../../../services/client/public-doctor-client.service';
import { SecurityOwner } from '../../../services/security/security-owner.service';
import { Router } from '@angular/router';

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

  constructor(
    client:PublicDoctorClientService,
    security:SecurityOwner,
    router:Router
  ) {
    client.search({}).subscribe(result => this.doctors.set(result))

    effect(() => {
      if(security.username() && !security.active()) {
        router.navigate(['/member', 'settings', 'change-pass'])
      }
    })
  }

}
