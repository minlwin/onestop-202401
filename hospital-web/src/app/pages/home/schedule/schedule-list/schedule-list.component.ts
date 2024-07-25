import { Component } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { ScheduleState } from '../schedule.state';
import { Router, RouterLink } from '@angular/router';
import { SecurityOwner } from '../../../../services/security/security-owner.service';
import { AfterLoginNavigationSate } from '../../../../services/utils/after-login-navigation.state';

@Component({
  selector: 'app-schedule-list',
  standalone: true,
  imports: [WidgetsModule, CommonModule, RouterLink],
  templateUrl: './schedule-list.component.html',
  styles: ``
})
export class ScheduleListComponent {

  constructor(public state:ScheduleState,
    public security:SecurityOwner,
    private navState:AfterLoginNavigationSate,
    private router:Router) {}

  takeAppointment(schedule:any) {

    this.state.selectedSchedule.set(schedule)

    if(this.security.isAnonymous()) {
      // Save Navigation State
      this.navState.navigation.set({url: ['/member/schedule/edit'], queryParams: {doctor: this.state.doctor()?.id}})
      // Navigate to login page
      this.router.navigate(['/home/signin'])
    } else if(this.security.isPatient()) {
      // Navigate to appointment edit page
      this.router.navigate(['/member/schedule/edit'], {queryParams: {doctor: this.state.doctor()?.id}})
    }
  }
}
