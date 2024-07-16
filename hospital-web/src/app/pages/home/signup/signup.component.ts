import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SignupClientService } from '../../../services/client/signup-client.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [WidgetsModule, RouterLink, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styles: ``
})
export class SignupComponent {

  form:FormGroup

  constructor(builder:FormBuilder,
    private router:Router,
    private client:SignupClientService
  ) {
    this.form = builder.group({
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signUp() {
    if(this.form.valid) {
      this.client.signUp(this.form.value).subscribe(_ => {
        this.router.navigate(['/home', 'signin'])
      })
    }
  }
}
