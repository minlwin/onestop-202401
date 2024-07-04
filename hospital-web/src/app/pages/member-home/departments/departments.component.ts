import { Component } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-departments',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './departments.component.html',
  styles: ``
})
export class DepartmentsComponent {

}
