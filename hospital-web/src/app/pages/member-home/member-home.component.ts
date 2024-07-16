import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SecurityOwner } from '../../services/security/security-owner.service';

@Component({
  selector: 'app-member-home',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './member-home.component.html',
  styles: ``
})
export class MemberHomeComponent {

  constructor(private security:SecurityOwner, private router:Router) {

  }

  signOut() {
    this.security.logout()
    this.router.navigate(['/'])
  }
}
