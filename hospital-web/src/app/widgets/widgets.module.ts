import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroupComponent } from './form-group/form-group.component';
import { SignViewComponent } from './sign-view/sign-view.component';
import { DoctorGridItemComponent } from './doctor-grid-item/doctor-grid-item.component';
import { PageTitleComponent } from './page-title/page-title.component';
import { StarsComponent } from './stars/stars.component';
import { ListViewComponent } from './list-view/list-view.component';
import { PaginationComponent } from './pagination/pagination.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { StaffListComponent } from './staff-list/staff-list.component';
import { RouterLink } from '@angular/router';
import { KeyValueComponent } from './key-value/key-value.component';
import { InformationCardComponent } from './information-card/information-card.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    FormGroupComponent,
    SignViewComponent,
    DoctorGridItemComponent,
    PageTitleComponent,
    StarsComponent,
    ListViewComponent,
    PaginationComponent,
    DoctorListComponent,
    StaffListComponent,
    KeyValueComponent,
    InformationCardComponent,
    ProfileComponent,
  ],
  imports: [
    CommonModule,
    RouterLink
  ],
  exports: [
    FormGroupComponent,
    SignViewComponent,
    DoctorGridItemComponent,
    PageTitleComponent,
    StarsComponent,
    ListViewComponent,
    PaginationComponent,
    DoctorListComponent,
    StaffListComponent,
    KeyValueComponent,
    InformationCardComponent,
    ProfileComponent
  ]
})
export class WidgetsModule { }
