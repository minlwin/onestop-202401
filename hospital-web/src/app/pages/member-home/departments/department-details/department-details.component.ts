import { Component, computed, input } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { DepartmentClientService } from '../../../../services/client/department-client.service';
import { RouterLink } from '@angular/router';
import { DetailsComponent } from '../../../details-component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './department-details.component.html',
  styles: ``
})
export class DepartmentDetailsComponent extends DetailsComponent{

  id = input<number>()

  title = computed(() => this.details() ? `${this.details()?.code} : ${this.details()?.name}` : `No Data`)
  head = computed(() => this.details()?.head)
  doctors = computed(() => this.details()?.doctors || [])
  staffs = computed(() => this.details()?.staffs || [])

  constructor(client:DepartmentClientService) {
    super(client)
  }
}
