import { Component, computed, effect, input, signal } from '@angular/core';
import { EditableComponent } from '../../../../editable-component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { StaffClientService } from '../../../../../services/client/staff-client.service';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { Router } from '@angular/router';
import { DepartmentClientService } from '../../../../../services/client/department-client.service';

@Component({
  selector: 'app-staff-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './staff-edit.component.html',
  styles: ``
})
export class StaffEditComponent extends EditableComponent {

  id = input<number>()
  form:FormGroup

  departmentId = input<number>()
  department = signal<any>({})

  title = computed(() => this.id() ? 'Edit Staff' : 'Add New Staff')


  constructor(
    builder:FormBuilder,
    client:StaffClientService,
    departmentClient:DepartmentClientService,
    private router:Router
  ) {
    super(client)

    this.form = builder.group({
      department: [undefined, Validators.required],
      email: ['', Validators.required],
      name: ['', Validators.required],
      position: ['', Validators.required],
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

  override onSaved(result: any): void {
    this.router.navigate(['/member/members/office-staffs/details'], {queryParams: {id: result.id}})
  }

  override extractFromValue(details: any) {

    this.department.set(details.department)
    this.form.patchValue({department: details.department.id})

    return {
      department: details.department.id,
      email: details.email,
      name: details.name,
      position: details.position,
      phone: details.phone,
      assignAt: details.assignAt
    }
  }
}
