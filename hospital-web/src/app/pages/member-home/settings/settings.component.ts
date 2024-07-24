import { Component, computed, signal } from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SecurityOwner } from '../../../services/security/security-owner.service';
import { ProfileClient } from '../../../services/client/profile-client.service';
import { Profile } from '../../../widgets/profile/profile.component';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [WidgetsModule, RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './settings.component.html',
  styles: ``
})
export class SettingsComponent {

  profile = signal<Profile | undefined>(undefined)
  activated = computed(() => this.security.active())

  constructor(public security:SecurityOwner, client:ProfileClient) {
    client.getProfile().subscribe(result => this.profile.set(result))
  }
}
