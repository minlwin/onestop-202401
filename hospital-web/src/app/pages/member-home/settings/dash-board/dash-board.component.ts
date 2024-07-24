import { Component } from '@angular/core';
import { SecurityOwner } from '../../../../services/security/security-owner.service';
import { OfficeDashComponent } from './office-dash/office-dash.component';
import { DoctorDashComponent } from './doctor-dash/doctor-dash.component';
import { PatientDashComponent } from './patient-dash/patient-dash.component';

@Component({
  selector: 'app-dash-board',
  standalone: true,
  imports: [OfficeDashComponent, DoctorDashComponent, PatientDashComponent],
  templateUrl: './dash-board.component.html',
  styles: ``
})
export class DashBoardComponent {

  constructor(public security:SecurityOwner) {
  }
}
