import { Component, computed, input } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DepartmentState } from '../department.state';
import { DetailsComponent } from '../../../details-component';
import { DepartmentClientService } from '../../../../services/client/department-client.service';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './department-details.component.html',
  providers: [DepartmentState]
})
export class DepartmentDetailsComponent extends DetailsComponent {

  id = input<number>()
  title = computed(() => this.details() ? `${this.details()?.code} : ${this.details()?.name}` : `No Data`)
  head = computed(() => this.details()?.head)
  doctors = computed(() => this.details()?.doctors || [])
  staffs = computed(() => this.details()?.staffs || [])

  constructor(client:DepartmentClientService) {
    super(client)
  }
}
