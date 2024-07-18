import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PagerComponent } from '../../pager-component';
import { AppointmentClientService } from '../../../services/client/appointment-client.service';

@Component({
  selector: 'app-appointments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './appointments.component.html',
  styles: ``
})
export class AppointmentsComponent extends PagerComponent {

  status = signal(["Applied", "Canceled", "Finished"])
  form:FormGroup

  constructor(builder:FormBuilder, client:AppointmentClientService) {
    super(client.search)
    this.form = builder.group({
      status: '',
      keyword: '',
      from: '',
      to: ''
    })
  }
}
