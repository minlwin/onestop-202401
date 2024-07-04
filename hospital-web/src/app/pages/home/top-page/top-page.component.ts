import { Component, effect, input, signal } from '@angular/core';
import { DoctorItem } from '../../../widgets/doctor-grid-item/doctor-grid-item.component';
import { WidgetsModule } from '../../../widgets/widgets.module';

@Component({
  selector: 'app-top-page',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './top-page.component.html',
  styles: ``
})
export class TopPageComponent {

  showCover = input<boolean | undefined>()
  doctors = signal<DoctorItem[]>([
    {
      id: 1,
      name: "Dr Mg Mg",
      gender: "Male",
      degree: "PHD",
      department: "Emergency",
      stars: 8
    },
    {
      id: 2,
      name: "Dr Theingi",
      gender: "Female",
      degree: "PHD",
      department: "Emergency",
      stars: 8
    },
    {
      id: 3,
      name: "Dr Nyi Nyi Aung",
      gender: "Male",
      degree: "MBBS",
      department: "General",
      stars: 8
    },
    {
      id: 4,
      name: "Dr Nilar Thein",
      gender: "Female",
      degree: "PHD",
      department: "OG",
      stars: 9
    },
    {
      id: 5,
      name: "Dr Myo Aung",
      gender: "Male",
      degree: "PHD",
      department: "Orthology",
      stars: 8
    },
  ])

  constructor() {

    effect(() => {
      console.log("Show Cover From Top Page", this.showCover())
    })
  }

}
