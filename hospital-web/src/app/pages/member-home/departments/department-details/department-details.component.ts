import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { DepartmentClientService } from '../../../../services/client/department-client.service';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './department-details.component.html',
  styles: ``
})
export class DepartmentDetailsComponent {

  id = input<number>()
  details = signal<any>(undefined)

  title = computed(() => this.details() ? `${this.details()?.code} : ${this.details()?.name}` : `No Data`)
  head = computed(() => this.details()?.head)
  constructor(client:DepartmentClientService) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => this.details.set(result))
      }
    }, {allowSignalWrites: true})
  }
}
