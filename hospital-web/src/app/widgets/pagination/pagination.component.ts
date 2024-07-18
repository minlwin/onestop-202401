import { Component, computed, EventEmitter, input, Output } from '@angular/core';
import { Pager } from '../../services/client/utils';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {

  pager = input<Pager>()

  @Output() onPageChange = new EventEmitter
  @Output() onSizeChange = new EventEmitter

  showFirst = computed(() => {
    if(this.pager()) {
      return this.pager()?.page! > 3 && this.pager()?.totalPages! > 5
    }
    return false
  })

  showLast = computed(() => {
    if(this.pager()) {
      return this.pager()?.page! < 3 && this.pager()?.totalPages! > 5
    }
    return false
  })

  pageChange(page:number) {
    this.onPageChange.emit(page)
  }

  sizeChange(size:any) {
    this.onSizeChange.emit(size)
  }
}

export interface PagerListener {
  onPageChage(page:number):void
  onSizeChange(size:number):void
}
