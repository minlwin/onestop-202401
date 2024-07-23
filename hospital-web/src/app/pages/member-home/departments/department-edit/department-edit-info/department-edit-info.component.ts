import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EditableComponent } from '../../../../editable-component';
import { Router } from '@angular/router';
import { DepartmentClientService } from '../../../../../services/client/department-client.service';
import { state } from '@angular/animations';
import { DepartmentEditState } from '../department-edit.state';

@Component({
  selector: 'app-department-edit-info',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './department-edit-info.component.html',
})
export class DepartmentEditInfoComponent extends EditableComponent{

  id = input<number>()
  form:FormGroup

  headName = signal<string>('Department Head Name')

  constructor(builder:FormBuilder,
    client:DepartmentClientService,
    state:DepartmentEditState,
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

    effect(() => {
      if(state.details()) {
        this.form.patchValue(this.extractFromValue(state.details()))
      }
    })
  }

  override onSaved(result: any): void {
    this.router.navigate(['/member/departments/details'], {queryParams: {id: result.id}})
  }

  private extractFromValue(details: any) {
    const {doctors, staffs, head, ... formData} = details
    if(head) {
      this.form.patchValue({headCode: head.code})
      this.headName.set(head.name)
    }
    return formData
  }
}
