import { Route } from "@angular/router";
import { AppointmentsComponent } from "./pages/member-home/appointments/appointments.component";
import { DashBoardComponent } from "./pages/member-home/dash-board/dash-board.component";
import { OfficeStaffsComponent } from "./pages/member-home/members/office-staffs/office-staffs.component";
import { DoctorsComponent } from "./pages/member-home/members/doctors/doctors.component";
import { PatientsComponent } from "./pages/member-home/members/patients/patients.component";
import { DepartmentsComponent } from "./pages/member-home/departments/departments.component";
import { TopPageComponent } from "./pages/home/top-page/top-page.component";

export const routes:Route[] = [
  {path: 'top', component: TopPageComponent, data: {showCover: true}},
  {path: 'appointments', component: AppointmentsComponent},
  {path: 'dash-board', component: DashBoardComponent},
  {path: 'departments', component: DepartmentsComponent},
  {path: 'members', children: [
    {path: 'office-staffs', component: OfficeStaffsComponent},
    {path: 'doctors', component: DoctorsComponent},
    {path: 'patients', component: PatientsComponent},
    {path: '', redirectTo: '/member/members/patients', pathMatch: 'full'}
  ]},
  {path: '', redirectTo: '/member/top', pathMatch: 'full'}
]
