import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-appointments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './appointments.component.html',
  styles: ``
})
export class AppointmentsComponent {

  status = signal(["Applied", "Canceled", "Finished"])
  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      status: '',
      keyword: '',
      from: '',
      to: ''
    })
  }
}
