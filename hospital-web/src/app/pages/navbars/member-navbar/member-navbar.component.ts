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
  loginUser = computed(() => this.security.loginUserName())

  masterMenu = viewChild<ElementRef>('masterMenu')
  memberMenu = viewChild<ElementRef>('memberMenu')

  constructor(private security:SecurityOwner, private router:Router) {
  }

  ngAfterViewInit(): void {

    const observer = new MutationObserver(mutations => {
      // Remove Active Class to Togglers
      mutations.filter(m => m.target)
        .map(m => m.target as HTMLElement)
        .map(element => this.getToggler(element))
        .forEach(element => element.classList.remove('active'))

      const mutantArray = mutations.map(m => m.target as HTMLElement)
        .filter(element => element.classList.contains('active'))

      const mutants = [... new Set(mutantArray)]

      mutants.forEach(element => this.getToggler(element).classList.add('active'))
    })

    if(this.masterMenu()) {
      this.register(observer, this.masterMenu()?.nativeElement)
    }

    if(this.memberMenu()) {
      this.register(observer, this.memberMenu()?.nativeElement)
    }
  }

  private getToggler(target:HTMLElement):any {
    return target?.parentElement?.parentElement?.parentElement?.firstChild
  }

  private register(observer:MutationObserver, menu:any) {
    const subMenus = Array.from(menu.children)
      .map(a => a as any).flatMap(a => Array.from(a.children))
      .map(a => a as HTMLElement)

    subMenus.forEach(a => {
      observer.observe(a, {
        attributes: true
      })
    })
  }

  signOut() {
    this.security.logout()
    this.router.navigate(['/home/top'])
  }

}
