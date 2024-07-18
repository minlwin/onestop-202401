import { Component, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DepartmentClientService } from '../../../../services/client/department-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule],
  templateUrl: './department-edit.component.html',
  styles: ``
})
export class DepartmentEditComponent {

  id = input<number | undefined>()
  headName = signal<string>('Department Head Name')
  form:FormGroup

  constructor(builder:FormBuilder, private client:DepartmentClientService, private router:Router) {
    this.form = builder.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      headCode : ''
    })

    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => {
          const {id, doctors, staffs, head, ... data} = result
          this.form.patchValue(data)
          if(head) {
            this.form.patchValue({head: head.code})
            this.headName.set(head.name)
          }
        })
      }
    }, {allowSignalWrites: true})
  }

  save() {
    if(this.form.valid) {
      const request = this.id() ? this.client.update(this.id()!, this.form.value)
        : this.client.create(this.form.value)

      request.subscribe(result => {
        this.router.navigate(
          ['/member', 'departments', 'details'],
          {queryParams: {id: result.id}})
      })
    }
  }
}
