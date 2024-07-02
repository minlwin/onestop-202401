import { Component, effect, input } from '@angular/core';

@Component({
  selector: 'app-top-page',
  standalone: true,
  imports: [],
  templateUrl: './top-page.component.html',
  styles: ``
})
export class TopPageComponent {

  showCover = input<boolean | undefined>()

  constructor() {

    effect(() => {
      console.log("Show Cover From Top Page", this.showCover())
    })
  }

}
