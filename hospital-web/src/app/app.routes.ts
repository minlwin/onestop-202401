import { Routes } from '@angular/router';
import { SigninComponent } from './pages/home/signin/signin.component';
import { SignupComponent } from './pages/home/signup/signup.component';
import { TopPageComponent } from './pages/home/top-page/top-page.component';
import { ChangePassComponent } from './pages/member-home/settings/change-pass/change-pass.component';
import { ScheduleComponent } from './pages/home/schedule/schedule.component';
import { ScheduleListComponent } from './pages/home/schedule/schedule-list/schedule-list.component';
import { AppointmentEditComponent } from './pages/home/schedule/appointment-edit/appointment-edit.component';

export const routes: Routes = [
  {path: "home", children: [
    {path: "top", component: TopPageComponent, data: {showCover: true}},
    {path: "schedule", component: ScheduleComponent, title: 'Appointment', children: [
      {path: 'list', component: ScheduleListComponent},
      {path: 'edit', component: AppointmentEditComponent},
      {path: '', redirectTo: '/home/schedule/list', pathMatch: 'full'}
    ]},
    {path: "signin", component: SigninComponent, title: 'Sign In'},
    {path: "signup", component: SignupComponent, title: 'Sign Up'},
    {path: "", redirectTo: "/home/top", pathMatch: 'full'}
  ]},
  {
    path: "member",
    loadChildren: () => import('./member.routes').then(m => m.routes)
  },
  {path: "", redirectTo: "/home", pathMatch: 'full'}
];
