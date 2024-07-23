import { Component, computed, effect, input } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { ReactiveFormsModule } from '@angular/forms';
import { DepartmentClientService } from '../../../../services/client/department-client.service';
import { DepartmentEditState } from './department-edit.state';
import { DepartmentDto } from '../../../../widgets/department-info/department-info.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterOutlet],
  templateUrl: './department-edit.component.html',
  providers: [DepartmentEditState]
})
export class DepartmentEditComponent {

  id = input<number>()
  department = computed(() => new DepartmentDto(this.state.details()))

  constructor(client:DepartmentClientService, private state:DepartmentEditState) {
    effect(() => {
      if(this.id()) {
        client.findById(this.id()!).subscribe(result => state.details.set(result))
      }
    })
  }

}
