import { Component, input } from '@angular/core';

@Component({
  selector: 'app-sign-view',
  templateUrl: './sign-view.component.html',
  styles: ``
})
export class SignViewComponent {

  icon = input.required<string>()
  title = input.required<string>()
}
