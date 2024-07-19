import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DepartmentClientService } from '../../../../../../services/client/department-client.service';
import { DoctorClientService } from '../../../../../../services/client/doctor-client.service';
import { EditableComponent } from '../../../../../editable-component';

@Component({
  selector: 'app-doctor-edit-profile',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './doctor-edit-profile.component.html',
  styles: ``
})
export class DoctorEditProfileComponent extends EditableComponent {

  id = input<number>()
  form:FormGroup

  departmentId = input<number>()
  department = signal<any>({})

  constructor(builder:FormBuilder,
    client:DoctorClientService,
    departmentClient:DepartmentClientService,
    private router:Router
  ) {
    super(client)
    this.form = builder.group({
      department: [undefined, Validators.required],
      email: ['', Validators.required],
      name: ['', Validators.required],
      degree: ['', Validators.required],
      phone: ['', Validators.required],
      assignAt: ['', Validators.required],
      reason: ''
    })

    effect(() => {
      if(this.departmentId()) {
        departmentClient.findById(this.departmentId()!).subscribe(dep => {
          this.department.set(dep)
          this.form.patchValue({department: dep.id})
        })
      }
    }, {allowSignalWrites: true})
  }

  override extractFromValue(details: any) {
    const {doctor, ... _} = details
    const {id, department, ... formData} = doctor
    this.department.set(department)
    this.form.patchValue({department: department.id})
    return formData
  }

  override onSaved(result: any): void {
    this.router.navigate(['/member/members/doctors/details'], {queryParams: {id: result.doctor.id}})
  }
}
