import { Component, effect } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SecurityOwner } from '../../../../services/security/security-owner.service';
import { ChangePassClientService } from '../../../../services/client/change-pass-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-pass',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './change-pass.component.html',
  styles: ``
})
export class ChangePassComponent {

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private security:SecurityOwner,
    private client:ChangePassClientService,
    private router:Router
  ) {
    this.form = builder.group({
      username: ['', Validators.required],
      oldPass: ['', Validators.required],
      newPass: ['', Validators.required]
    })

    effect(() => {
      this.form.patchValue({username: security.username()})
    })
  }

  changePassword() {
    if(this.form.valid) {
      this.client.changePass(this.form.value).subscribe(result => {
        this.security.login(result)
        this.router.navigate(['/member', 'settings', 'dash-board'])
      })
    }
  }
}
