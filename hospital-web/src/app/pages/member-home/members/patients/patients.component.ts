import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { WidgetsModule } from '../../../../widgets/widgets.module';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './patients.component.html',
  styles: ``
})
export class PatientsComponent {

}
