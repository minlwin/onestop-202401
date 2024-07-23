import { Component, computed, input } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {

  profile = input<Profile>()
  image = computed(() => this.profile()?.image || '/images/profile.png')

}

export interface Profile {
  id: number | undefined
  name: string | undefined
  image: string | undefined
  phone: string | undefined
  email: string | undefined
}
