import { signal } from "@angular/core"
import { FormGroup } from "@angular/forms"
import { PageInfo, Pager } from "../services/client/utils"
import { Observable } from "rxjs"

export abstract class PagerComponent {

  abstract get form():FormGroup

  contents = signal<any[]>([])
  pager = signal<Pager | undefined>(undefined)

  constructor(private searchFunc:PageSearchFunc) {}

  search() {
    this.searchFunc(this.form.value).subscribe(result => {
      const {contents, ... pager} = result
      this.contents.set(contents)
      this.pager.set(pager)
    })
  }

  onSizeChange(size:number) {
    this.form.patchValue({size: size, page: 0})
    this.search()
  }

  onPageChage(page:number) {
    this.form.patchValue({page: page})
    this.search()
  }
}

export interface PageSearchFunc {
  (form:any):Observable<PageInfo>
}
