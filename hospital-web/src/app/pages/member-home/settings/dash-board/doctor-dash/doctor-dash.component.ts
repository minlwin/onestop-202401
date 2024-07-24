import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { SecurityOwner } from '../../../../../services/security/security-owner.service';
import { ProfileClient } from '../../../../../services/client/profile-client.service';

@Component({
  selector: 'app-doctor-dash',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './doctor-dash.component.html',
  styles: ``
})
export class DoctorDashComponent {

  details = signal<any>(undefined)

  constructor(client:ProfileClient) {
    client.getDoctorProfile().subscribe(result => this.details.set(result))
  }
}
