import { Component, input } from '@angular/core';
import { EditableComponent } from '../../../../editable-component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { StaffClientService } from '../../../../../services/client/staff-client.service';

@Component({
  selector: 'app-staff-edit',
  standalone: true,
  imports: [],
  templateUrl: './staff-edit.component.html',
  styles: ``
})
export class StaffEditComponent extends EditableComponent {

  id = input<number>()
  form:FormGroup

  constructor(builder:FormBuilder, client:StaffClientService) {
    super(client)

    this.form = builder.group({})
  }

  override onSaved(result: any): void {
  }

  override extractFromValue(details: any) {
  }
}
