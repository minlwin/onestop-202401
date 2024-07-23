import { Component, input } from '@angular/core';
import { WidgetsModule } from '../../../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EditableComponent, SaveEnableClient } from '../../../../../editable-component';
import { Observable } from 'rxjs';
import { DoctorClientService } from '../../../../../../services/client/doctor-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-edit-section',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './doctor-edit-section.component.html',
  styles: ``
})
export class DoctorEditSectionComponent extends EditableComponent{

  id = input<number>()
  form:FormGroup

  constructor(builder:FormBuilder, client:DoctorClientService, private router:Router) {
    super(new EditableClientAdaptor(client))
    this.form = builder.group({
      startDate: ['', Validators.required],
      sections: builder.array([])
    })
  }

  override onSaved(result: any): void {
    this.router.navigate(['/member/members/doctors/details'], {queryParams: {id: result.doctor.id}})
  }
}

class EditableClientAdaptor implements SaveEnableClient {

  constructor(private client:DoctorClientService) {}

  create(form: any): Observable<any> {
    return this.client.create(form)
  }

  update(id: any, form: any): Observable<any> {
    return this.client.updateSections(id, form)
  }
}
