import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { PagerComponent } from '../../../pager-component';
import { DoctorClientService } from '../../../../services/client/doctor-client.service';

@Component({
  selector: 'app-doctors',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './doctors.component.html',
  styles: ``
})
export class DoctorsComponent extends PagerComponent {

  form:FormGroup

  constructor(builder:FormBuilder, client:DoctorClientService) {
    super(client.search)
    this.form = builder.group({
      status: '',
      department: '',
      name: ''
    })
  }

}
