import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
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

  constructor(builder:FormBuilder, private router:Router) {
    this.form = builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signIn() {
    this.router.navigate(['/member'])
  }
}
