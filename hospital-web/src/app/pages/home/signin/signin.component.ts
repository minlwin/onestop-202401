import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthClientService } from '../../../services/client/auth-client.service';
import { SecurityOwner } from '../../../services/security/security-owner.service';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './signin.component.html',
  styles: ``
})
export class SigninComponent {

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private router:Router,
    private client:AuthClientService,
    private security:SecurityOwner
  ) {
    this.form = builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signIn() {
    if(this.form.valid) {
      this.client.generateToken(this.form.value).subscribe(result => {
        this.security.login(result)
        this.router.navigate(['/member'])
      })
    }
  }
}
