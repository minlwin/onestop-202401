import { Component, computed, input } from '@angular/core';

@Component({
  selector: 'app-doctor-grid-item',
  templateUrl: './doctor-grid-item.component.html',
  styleUrl: './doctor-grid-item.component.scss'
})
export class DoctorGridItemComponent {

  doctor = input.required<DoctorItem>()
  profile = computed(() => this.doctor().profile !== undefined
    ? this.doctor().profile : this.getProfileForNoPhoto(this.doctor().gender))

  private getProfileForNoPhoto(gender:string) {
    return gender == 'Male' ? '/images/male-doctor.png' : '/images/female-doctor.jpg'
  }
}

export interface DoctorItem {
  id:number
  name:string
  gender: string
  degree:string
  department:string
  stars:number
  profile?:string
}
