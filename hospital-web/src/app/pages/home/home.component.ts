import { Component, signal } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { WidgetsModule } from '../../widgets/widgets.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [WidgetsModule, RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent {

  showCover = signal(false)

  constructor(router:Router, route:ActivatedRoute) {
    router.events.subscribe(e => {
      if(e instanceof NavigationEnd) {
        console.log(e.url)
        route.firstChild?.data.subscribe(data => {
          this.showCover.set(data['showCover'] || false)
        })
      }
    })
  }

}
