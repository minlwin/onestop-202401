import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './signin.component.html',
  styles: ``
})
export class SigninComponent {

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }
}
