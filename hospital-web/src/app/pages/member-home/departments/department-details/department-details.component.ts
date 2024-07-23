import { Component, computed, input } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DetailsComponent } from '../../../details-component';
import { DepartmentClientService } from '../../../../services/client/department-client.service';
import { DepartmentDto } from '../../../../widgets/department-info/department-info.component';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './department-details.component.html',
})
export class DepartmentDetailsComponent extends DetailsComponent {

  id = input<number>()
  department = computed(() => new DepartmentDto(this.details()))
  title = computed(() => this.details() ? `${this.details()?.code} : ${this.details()?.name}` : `No Data`)
  doctors = computed(() => this.details()?.doctors || [])
  staffs = computed(() => this.details()?.staffs || [])

  constructor(client:DepartmentClientService) {
    super(client)
  }
}
