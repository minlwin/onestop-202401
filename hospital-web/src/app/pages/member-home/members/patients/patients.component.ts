import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { PagerComponent } from '../../../pager-component';
import { PatientClientService } from '../../../../services/client/patient-client.service';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './patients.component.html',
  styles: ``
})
export class PatientsComponent extends PagerComponent {

  form:FormGroup

  constructor(builder:FormBuilder, client:PatientClientService) {
    super(client)
    this.form = builder.group({
      gender: '',
      keyword: '',
      page: 0,
      size: 10
    })

    this.search()
  }
}
