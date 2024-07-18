import { Component, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DepartmentClientService } from '../../../../services/client/department-client.service';
import { Router } from '@angular/router';
import { EditableComponent } from '../../../editable-component';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './department-edit.component.html',
  styles: ``
})
export class DepartmentEditComponent extends EditableComponent{

  id = input<number>()
  form:FormGroup

  headName = signal<string>('Department Head Name')

  constructor(builder:FormBuilder,
    client:DepartmentClientService,
    private router:Router
  ) {
    super(client)

    this.form = builder.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      headCode : ''
    })
  }

  override onSaved(result: any): void {
    this.router.navigate(['/member/departments'], {queryParams: {id: result.id}})
  }

  override extractFromValue(details: any) {
    const {doctors, staffs, head, ... formData} = details
    if(head) {
      this.form.patchValue({headCode: head.code})
      this.headName.set(head.name)
    }
    return formData
  }

}
