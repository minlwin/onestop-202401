import { Component, effect, input } from '@angular/core';
import { DetailsComponent } from '../../../../details-component';
import { StaffClientService } from '../../../../../services/client/staff-client.service';
import { WidgetsModule } from '../../../../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-staff-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './staff-details.component.html',
  styles: ``
})
export class StaffDetailsComponent extends DetailsComponent {

  override id = input<number>()

  constructor(client:StaffClientService) {
    super(client)

    effect(() => {
      console.log(`Staff ID is ${this.id()}`)
    })
  }
}
