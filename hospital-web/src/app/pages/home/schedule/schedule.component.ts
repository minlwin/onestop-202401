import { Component, effect, input } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ScheduleState } from './schedule.state';
import { PublicDoctorClientService } from '../../../services/client/public-doctor-client.service';

@Component({
  selector: 'app-schedule',
  standalone: true,
  imports: [WidgetsModule, CommonModule, RouterOutlet],
  templateUrl: './schedule.component.html',
  providers: [ScheduleState]
})
export class ScheduleComponent {

  doctor = input.required<number>()

  constructor(client:PublicDoctorClientService, public state:ScheduleState) {
    effect(() => {
      const doctorId = this.doctor()

      if(doctorId) {
        client.findById(doctorId).subscribe(result => state.details.set(result))
      }
    }, {allowSignalWrites: true})
  }
}
