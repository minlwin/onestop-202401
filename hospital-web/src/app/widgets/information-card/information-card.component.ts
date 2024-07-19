import { Component, input } from '@angular/core';

@Component({
  selector: 'app-information-card',
  templateUrl: './information-card.component.html',
  styles: ``
})
export class InformationCardComponent {

  informations = input<Information[]>()
  noDataMessage = input<string>()
}

export interface Information {
  label:string
  value:string
}
