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
import { DoctorEditProfileComponent } from "./pages/member-home/members/doctors/doctor-edit/doctor-edit-profile/doctor-edit-profile.component";
import { DoctorEditAddressComponent } from "./pages/member-home/members/doctors/doctor-edit/doctor-edit-address/doctor-edit-address.component";
import { DoctorEditDepartmentComponent } from "./pages/member-home/members/doctors/doctor-edit/doctor-edit-department/doctor-edit-department.component";
import { DoctorEditSectionComponent } from "./pages/member-home/members/doctors/doctor-edit/doctor-edit-section/doctor-edit-section.component";
import { StaffEditProfileComponent } from "./pages/member-home/members/office-staffs/staff-edit/staff-edit-profile/staff-edit-profile.component";
import { StaffEditAddressComponent } from "./pages/member-home/members/office-staffs/staff-edit/staff-edit-address/staff-edit-address.component";
import { StaffEditDepartmentComponent } from "./pages/member-home/members/office-staffs/staff-edit/staff-edit-department/staff-edit-department.component";
import { PatientEditComponent } from "./pages/member-home/members/patients/patient-edit/patient-edit.component";
import { PatientEditProfileComponent } from "./pages/member-home/members/patients/patient-edit/patient-edit-profile/patient-edit-profile.component";
import { PatientEditAddressComponent } from "./pages/member-home/members/patients/patient-edit/patient-edit-address/patient-edit-address.component";

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
    {path: 'office-staffs', title: 'Office Staffs', children: [
      {path: 'list', component: OfficeStaffsComponent},
      {path: 'details', component: StaffDetailsComponent},
      {path: 'edit', component: StaffEditComponent, children: [
        {path: 'profile', component: StaffEditProfileComponent},
        {path: 'address', component: StaffEditAddressComponent},
        {path: 'department', component: StaffEditDepartmentComponent},
        {path: '', redirectTo: '/member/members/office-staffs/edit/profile', pathMatch: 'full'}
      ]},
      {path: '', redirectTo: '/member/members/office-staffs/list', pathMatch: 'full'}
    ]},
    {path: 'doctors', title: 'Doctors', children: [
      {path: 'list', component: DoctorsComponent},
      {path: 'details', component: DoctorDetailsComponent},
      {path: 'edit', component: DoctorEditComponent, children: [
        {path: 'profile', component: DoctorEditProfileComponent},
        {path: 'address', component: DoctorEditAddressComponent},
        {path: 'department', component: DoctorEditDepartmentComponent},
        {path: 'section', component: DoctorEditSectionComponent},
        {path: '', redirectTo: '/member/members/doctors/edit/profile', pathMatch: 'full'}
      ]},
      {path: '', redirectTo: '/member/members/doctors/list', pathMatch: 'full'}
    ]},
    {path: 'patients', title: 'Patients', children: [
      {path: 'list', component: PatientsComponent},
      {path: 'details', component: PatientDetailsComponent},
      {path: 'edit', component: PatientEditComponent, children: [
        {path: 'profile', component: PatientEditProfileComponent},
        {path: 'address', component: PatientEditAddressComponent},
        {path: '', redirectTo: '/member/members/patients/edit/profile', pathMatch: 'full'}
      ]},
      {path: '', redirectTo: '/member/members/patients/list', pathMatch: 'full'}
    ]},
    {path: '', redirectTo: '/member/members/patients', pathMatch: 'full'}
  ]},
  {path: '', redirectTo: '/member/top', pathMatch: 'full'}
]
