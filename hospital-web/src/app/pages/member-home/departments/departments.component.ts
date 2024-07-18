import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PagerComponent } from '../../pager-component';
import { DepartmentClientService } from '../../../services/client/department-client.service';
import { Pager } from '../../../services/client/utils';

@Component({
  selector: 'app-departments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './departments.component.html',
  styles: ``
})
export class DepartmentsComponent extends PagerComponent{

  form:FormGroup
  contents = signal<any[]>([])
  pager = signal<Pager | undefined>(undefined)

  constructor(bulder:FormBuilder, private client:DepartmentClientService) {
    super()
    this.form = bulder.group({
      code: '',
      name: '',
      page: 0,
      size: 10
    })
  }

  override pathForm(data: any): void {
    this.form.patchValue(data)
  }

  override search() {
    this.client.search(this.form.value).subscribe(result => {
      const {contents, ... pager} = result
      this.contents.set(contents)
      this.pager.set(pager)
    })
  }

}
