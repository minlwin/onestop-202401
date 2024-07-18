import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PagerComponent } from '../../pager-component';
import { DepartmentClientService } from '../../../services/client/department-client.service';

@Component({
  selector: 'app-departments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './departments.component.html',
  styles: ``
})
export class DepartmentsComponent extends PagerComponent{

  form:FormGroup

  constructor(bulder:FormBuilder, client:DepartmentClientService) {
    super(client.search)
    this.form = bulder.group({
      code: '',
      name: '',
      page: 0,
      size: 10
    })

    this.search()
  }

}
