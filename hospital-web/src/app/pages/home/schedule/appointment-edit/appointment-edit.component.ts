import { Component, effect } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { ScheduleState } from '../schedule.state';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SecurityOwner } from '../../../../services/security/security-owner.service';
import { AppointmentClientService } from '../../../../services/client/appointment-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointment-edit',
  standalone: true,
  imports: [WidgetsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './appointment-edit.component.html',
  styles: ``
})
export class AppointmentEditComponent {

  form:FormGroup

  constructor(
    public state:ScheduleState,
    builder:FormBuilder,
    security:SecurityOwner,
    private router:Router,
    private client:AppointmentClientService
  ) {
    this.form = builder.group({
      email: [security.username(), Validators.required],
      date: ['', Validators.required],
      section: ['', Validators.required],
      doctorId: ['', Validators.required],
      complain: ''
    })

    effect(() => {
      if(state.selectedSchedule()) {
        this.form.patchValue({
          date: state.selectedSchedule()?.issueAt,
          section: state.selectedSchedule()?.section,
          doctorId: state.selectedSchedule()?.doctorId
        })
      }
    })
  }

  save() {
    if(this.form.valid) {
      this.client.create(this.form.value).subscribe(_ => {
        this.router.navigate(['/member/appointments'])
      })
    }
  }


}
