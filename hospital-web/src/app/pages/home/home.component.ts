import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { WidgetsModule } from '../../widgets/widgets.module';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [WidgetsModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent {

}
