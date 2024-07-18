import { Component, input } from '@angular/core';
import { Pager } from '../../services/client/utils';
import { PagerListener } from '../pagination/pagination.component';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styles: ``
})
export class ListViewComponent {

  icon = input<string>()
  title = input.required<string>()

  listener = input.required<PagerListener>()
  pager = input<Pager>()
}
