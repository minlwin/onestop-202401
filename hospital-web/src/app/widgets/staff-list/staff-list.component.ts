import { Component, input } from '@angular/core';

@Component({
  selector: 'app-staff-list',
  templateUrl: './staff-list.component.html',
  styles: ``
})
export class StaffListComponent {

  items = input.required<any[]>()
}
