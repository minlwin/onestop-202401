import { Component, input } from '@angular/core';
import { EditableComponent, SaveEnableClient } from '../../../../../editable-component';
import { Observable } from 'rxjs';
import { WidgetsModule } from '../../../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DoctorClientService } from '../../../../../../services/client/doctor-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-edit-address',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './doctor-edit-address.component.html',
})
export class DoctorEditAddressComponent extends EditableComponent{

  id = input<number>()
  form:FormGroup

  constructor(builder:FormBuilder, client:DoctorClientService, private router:Router) {
    super(new EditableClientAdaptor(client))
    this.form = builder.group({
      divisionId: ['', Validators.required],
      districtId: ['', Validators.required],
      townshipId: ['', Validators.required],
      quarter: ['', Validators.required],
      street: ['', Validators.required],
      building: ['', Validators.required]
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
    return this.client.updateAddress(id, form)
  }
}
