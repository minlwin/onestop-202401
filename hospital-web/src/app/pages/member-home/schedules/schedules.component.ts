import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { PagerComponent } from '../../pager-component';
import { ScheduleClientService } from '../../../services/client/schedule-client.service';

@Component({
  selector: 'app-schedules',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './schedules.component.html',
  styles: ``
})
export class SchedulesComponent extends PagerComponent{

  form:FormGroup

  constructor(builder:FormBuilder, client:ScheduleClientService) {
    super(client)
    this.form = builder.group({
      email: '',
      doctor: '',
      section: '',
      from: '',
      to: ''
    })
    this.search()
  }

}
