import { Component, computed } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { SecurityOwner } from '../../../services/security/security-owner.service';
import { computeMsgId } from '@angular/compiler';

@Component({
  selector: 'app-member-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './member-navbar.component.html',
  styles: ``
})
export class MemberNavbarComponent {

  activated = computed(() => this.security.active())
  role = computed(() => this.security.memberRole())

  constructor(private security:SecurityOwner, private router:Router) {

  }

  signOut() {
    this.security.logout()
    this.router.navigate(['/home/top'])
  }

}
