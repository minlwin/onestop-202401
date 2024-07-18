import { Component, input } from '@angular/core';
import { Pager } from '../../services/client/utils';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styles: ``
})
export class ListViewComponent {

  title = input.required<string>()
  icon = input<string | undefined>()
  pager = input<Pager | undefined>()
}
