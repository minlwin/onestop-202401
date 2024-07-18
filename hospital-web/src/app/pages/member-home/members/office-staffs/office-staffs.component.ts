import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { StaffClientService } from '../../../../services/client/staff-client.service';
import { PagerComponent } from '../../../pager-component';

@Component({
  selector: 'app-office-staffs',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './office-staffs.component.html',
  styles: ``
})
export class OfficeStaffsComponent extends PagerComponent{

  form:FormGroup

  constructor(builder:FormBuilder, client:StaffClientService) {
    super(client.search)
    this.form = builder.group({
      status: '',
      keyword: '',
      page: 0,
      size: 10
    })
  }

}
