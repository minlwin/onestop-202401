import { AfterViewInit, Component, computed, effect, ElementRef, ErrorHandler, Inject, inject, signal, viewChild } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { AppExceptionHandler } from './services/utils/app-exception-handler';
import { PublicNavbarComponent } from './pages/navbars/public-navbar/public-navbar.component';
import { MemberNavbarComponent } from './pages/navbars/member-navbar/member-navbar.component';
import { SecurityOwner } from './services/security/security-owner.service';

declare let bootstrap:any

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PublicNavbarComponent, MemberNavbarComponent],
  templateUrl: './app.component.html',
})
export class AppComponent implements AfterViewInit{

  showCover = signal(false)

  errorMessages = computed(() => this.errorHandler.errorMessages())
  showErrorDialog = computed(() => this.errorMessages().length > 0)
  showPublicNav = computed(() => this.security.username() == undefined)

  errorDialog = viewChild<ElementRef>('errorDialog')
  modalDialog:any

  constructor(
    router:Router, route:ActivatedRoute,
    private security:SecurityOwner,
    @Inject(ErrorHandler) private errorHandler:AppExceptionHandler) {

      router.events.subscribe(e => {
        if(e instanceof NavigationEnd) {
          route.firstChild?.firstChild?.data.subscribe(data => {
            this.showCover.set(data['showCover'] || false)
          })
        }
      })

    effect(() => {
      if(this.showErrorDialog()) {
        this.modalDialog?.show()
      }
    })
  }

  ngAfterViewInit(): void {
    this.modalDialog = new bootstrap.Modal(this.errorDialog()?.nativeElement)
    this.errorDialog()?.nativeElement.addEventListener('hidden.bs.modal', () => {
      this.errorHandler.errorMessages.set([])
    })
  }

  closeDialog() {
    this.modalDialog?.hide()
  }

}
