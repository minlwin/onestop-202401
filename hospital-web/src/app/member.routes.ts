import { Route } from "@angular/router";
import { AppointmentsComponent } from "./pages/member-home/appointments/appointments.component";
import { DashBoardComponent } from "./pages/member-home/dash-board/dash-board.component";
import { OfficeStaffsComponent } from "./pages/member-home/members/office-staffs/office-staffs.component";
import { DoctorsComponent } from "./pages/member-home/members/doctors/doctors.component";
import { PatientsComponent } from "./pages/member-home/members/patients/patients.component";
import { DepartmentsComponent } from "./pages/member-home/departments/departments.component";
import { TopPageComponent } from "./pages/home/top-page/top-page.component";
import { DepartmentEditComponent } from "./pages/member-home/departments/department-edit/department-edit.component";
import { DepartmentDetailsComponent } from "./pages/member-home/departments/department-details/department-details.component";
import { StaffDetailsComponent } from "./pages/member-home/members/office-staffs/staff-details/staff-details.component";
import { PatientDetailsComponent } from "./pages/member-home/members/patients/patient-details/patient-details.component";
import { DoctorDetailsComponent } from "./pages/member-home/members/doctors/doctor-details/doctor-details.component";
import { StaffEditComponent } from "./pages/member-home/members/office-staffs/staff-edit/staff-edit.component";
import { DoctorEditComponent } from "./pages/member-home/members/doctors/doctor-edit/doctor-edit.component";

export const routes:Route[] = [
  {path: 'top', component: TopPageComponent, data: {showCover: true}},
  {path: 'appointments', component: AppointmentsComponent, title: 'Appointments'},
  {path: 'dash-board', component: DashBoardComponent},
  {path: 'departments', children: [
    {path: 'list', component: DepartmentsComponent, title: 'Department'},
    {path: 'edit', component: DepartmentEditComponent, title: 'Department'},
    {path: 'details', component: DepartmentDetailsComponent, title: 'Department'},
    {path: '', redirectTo: '/member/departments/list', pathMatch: 'full'}
  ]},
  {path: 'members', children: [
    {path: 'office-staffs', children: [
      {path: 'list', component: OfficeStaffsComponent, title: 'Office Staffs'},
      {path: 'edit', component: StaffEditComponent, title: 'Office Staffs'},
      {path: 'details', component: StaffDetailsComponent, title: 'Office Staffs'},
      {path: '', redirectTo: '/member/members/office-staffs/list', pathMatch: 'full'}
    ]},
    {path: 'doctors', children: [
      {path: 'list', component: DoctorsComponent, title: 'Doctors'},
      {path: 'edit', component: DoctorEditComponent, title: 'Doctors'},
      {path: 'details', component: DoctorDetailsComponent, title: 'Doctors'},
      {path: '', redirectTo: '/member/members/doctors/list', pathMatch: 'full'}
    ]},
    {path: 'patients', children: [
      {path: 'list', component: PatientsComponent, title: 'Patients'},
      {path: 'details', component: PatientDetailsComponent, title: 'Patients'},
      {path: '', redirectTo: '/member/members/patients/list', pathMatch: 'full'}
    ]},
    {path: '', redirectTo: '/member/members/patients', pathMatch: 'prefix'}
  ]},
  {path: '', redirectTo: '/member/top', pathMatch: 'full'}
]
