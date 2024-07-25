import { Component, effect, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PagerComponent } from '../../pager-component';
import { AppointmentClientService } from '../../../services/client/appointment-client.service';
import { SecurityOwner } from '../../../services/security/security-owner.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-appointments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './appointments.component.html',
  styles: ``
})
export class AppointmentsComponent extends PagerComponent {

  status = signal(["Applied", "Canceled", "Finished"])
  form:FormGroup

  constructor(
    builder:FormBuilder,
    security:SecurityOwner,
    client:AppointmentClientService) {
    super(client)
    this.form = builder.group({
      doctorEmail: '',
      patientEmail: '',
      status: '',
      keyword: '',
      from: '',
      to: ''
    })

    effect(() => {
      if(security.isDoctor()) {
        this.form.patchValue({doctorEmail: security.username()})
      }

      if(security.isPatient()) {
        this.form.patchValue({patientEmail: security.username()})
      }

      this.search()
    })

  }
}
