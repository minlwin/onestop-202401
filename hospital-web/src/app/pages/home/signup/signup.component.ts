import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [WidgetsModule, RouterLink, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styles: ``
})
export class SignupComponent {

  form:FormGroup

  constructor(builder:FormBuilder, private router:Router) {
    this.form = builder.group({
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signUp() {
    this.router.navigate(['/home', 'signin'])
  }
}
