import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroupComponent } from './form-group/form-group.component';
import { SignViewComponent } from './sign-view/sign-view.component';



@NgModule({
  declarations: [
    FormGroupComponent,
    SignViewComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormGroupComponent,
    SignViewComponent
  ]
})
export class WidgetsModule { }
