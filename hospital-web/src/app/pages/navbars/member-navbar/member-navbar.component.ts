import { AfterViewInit, Component, computed, ElementRef, viewChild } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { SecurityOwner } from '../../../services/security/security-owner.service';

@Component({
  selector: 'app-member-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './member-navbar.component.html',
  styles: ``
})
export class MemberNavbarComponent implements AfterViewInit{

  activated = computed(() => this.security.active())
  role = computed(() => this.security.memberRole())

  toggler = viewChild<ElementRef>('dropdownToggler')
  menu = viewChild<ElementRef>('dropdownMenu')

  constructor(private security:SecurityOwner, private router:Router) {
  }

  ngAfterViewInit(): void {

    const observer = new MutationObserver(mutations => {
      const menuHolder = this.toggler()?.nativeElement as HTMLElement
      const active = mutations.map(m => m.target as HTMLElement).flatMap(a => Array.from(a.classList))
        .filter(a => a == 'active').pop()

      if(active) {
        menuHolder.classList.add('active')
      } else {
        menuHolder.classList.remove('active')
      }
    })

    if(this.toggler() && this.menu()) {
      const subMenus = Array.from(this.menu()?.nativeElement.children)
          .map(a => a as any).flatMap(a => Array.from(a.children))
          .map(a => a as HTMLElement)

      subMenus.forEach(a => {
        observer.observe(a, {
          attributes: true
        })
      })
    }
  }

  signOut() {
    this.security.logout()
    this.router.navigate(['/home/top'])
  }

}
