import { Component, input } from '@angular/core';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styles: ``
})
export class DoctorListComponent {

  items = input.required<any[]>()
}
