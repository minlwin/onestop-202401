import { Component, input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DoctorClientService } from '../../../../../services/client/doctor-client.service';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './doctor-edit.component.html',
  styles: ``
})
export class DoctorEditComponent {

  id = input<number>()
  departmentId = input<number>()

  form:FormGroup

  constructor(builder:FormBuilder,
    private client:DoctorClientService,
    private router:Router
  ) {
    this.form = builder.group({})
  }

  save() {
    if(this.form.valid) {
      const request = this.id() ? this.client.updateInfo(this.id()!, this.form.value)
        : this.client.create(this.form.value)

      request.subscribe(result => {
        this.router.navigate(['member/members/doctors/details'], {queryParams: {id : result.id}})
      })
    }
  }
}
