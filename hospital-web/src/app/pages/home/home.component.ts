import { Component, signal } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { WidgetsModule } from '../../widgets/widgets.module';
import { CommonModule } from '@angular/common';
import { SecurityOwner } from '../../services/security/security-owner.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [WidgetsModule, RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent {

  showCover = signal(false)

  constructor(router:Router, route:ActivatedRoute, security:SecurityOwner) {

    if(security.username() != undefined) {
      // Login Success
      if(security.active()) {
        // Navigate to Member Home
        router.navigate(['/', 'member'])
      } else {
        // Navigate to Change Password Page
        router.navigate(['/', 'change-pass'])
      }
    } else {
      router.events.subscribe(e => {
        if(e instanceof NavigationEnd) {
          route.firstChild?.data.subscribe(data => {
            this.showCover.set(data['showCover'] || false)
          })
        }
      })
    }
  }

}
