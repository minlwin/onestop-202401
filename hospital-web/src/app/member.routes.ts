import { Route } from "@angular/router";
import { AppointmentsComponent } from "./pages/member-home/appointments/appointments.component";
import { DashBoardComponent } from "./pages/member-home/settings/dash-board/dash-board.component";
import { DepartmentsComponent } from "./pages/member-home/departments/departments.component";
import { TopPageComponent } from "./pages/home/top-page/top-page.component";
import { DepartmentEditComponent } from "./pages/member-home/departments/department-edit/department-edit.component";
import { DepartmentDetailsComponent } from "./pages/member-home/departments/department-details/department-details.component";
import { DepartmentEditInfoComponent } from "./pages/member-home/departments/department-edit/department-edit-info/department-edit-info.component";
import { ScheduleComponent } from "./pages/home/schedule/schedule.component";
import { ScheduleListComponent } from "./pages/home/schedule/schedule-list/schedule-list.component";
import { AppointmentEditComponent } from "./pages/home/schedule/appointment-edit/appointment-edit.component";
import { SchedulesComponent } from "./pages/member-home/schedules/schedules.component";
import { ChangePassComponent } from "./pages/member-home/settings/change-pass/change-pass.component";
import { SettingsComponent } from "./pages/member-home/settings/settings.component";

export const routes:Route[] = [
  {path: 'top', component: TopPageComponent, data: {showCover: true}},
  {path: "schedule", component: ScheduleComponent, title: 'Appointment', children: [
    {path: 'list', component: ScheduleListComponent},
    {path: 'edit', component: AppointmentEditComponent},
    {path: '', redirectTo: '/member/schedule/list', pathMatch: 'full'}
  ]},
  {path: 'appointments', component: AppointmentsComponent, title: 'Appointments'},
  {path: 'schedules', component: SchedulesComponent, title: 'Schedules'},
  {path: 'departments', children: [
    {path: 'list', component: DepartmentsComponent, title: 'Department'},
    {path: 'edit', component: DepartmentEditComponent, title: 'Department', children: [
      {path: 'info', component: DepartmentEditInfoComponent},
      {path: '', redirectTo: '/member/departments/edit/info', pathMatch: 'full'}
    ]},
    {path: 'details', component: DepartmentDetailsComponent, title: 'Department'},
    {path: '', redirectTo: '/member/departments/list', pathMatch: 'full'}
  ]},
  {path: 'members', loadChildren: () => import('./members.routes').then(m => m.routes)},
  {path: 'settings', component: SettingsComponent, title: "Member", children: [
    {path: 'dash-board', component: DashBoardComponent},
    {path: 'change-pass', component: ChangePassComponent},
  ]},
  {path: '', redirectTo: '/member/top', pathMatch: 'full'}
]
