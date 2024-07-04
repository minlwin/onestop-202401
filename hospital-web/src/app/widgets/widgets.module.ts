import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroupComponent } from './form-group/form-group.component';
import { SignViewComponent } from './sign-view/sign-view.component';
import { DoctorGridItemComponent } from './doctor-grid-item/doctor-grid-item.component';
import { PageTitleComponent } from './page-title/page-title.component';
import { StarsComponent } from './stars/stars.component';



@NgModule({
  declarations: [
    FormGroupComponent,
    SignViewComponent,
    DoctorGridItemComponent,
    PageTitleComponent,
    StarsComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormGroupComponent,
    SignViewComponent,
    DoctorGridItemComponent,
    PageTitleComponent,
    StarsComponent
  ]
})
export class WidgetsModule { }
