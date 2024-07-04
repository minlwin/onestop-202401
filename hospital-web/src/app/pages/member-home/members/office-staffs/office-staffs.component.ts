import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { WidgetsModule } from '../../../../widgets/widgets.module';

@Component({
  selector: 'app-office-staffs',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './office-staffs.component.html',
  styles: ``
})
export class OfficeStaffsComponent {

}
