export abstract class PagerComponent {

  abstract search():void
  abstract pathForm(data:any):void

  onSizeChange(size:number) {
    this.pathForm({size: size, page: 0})
    this.search()
  }

  onPageChage(page:number) {
    this.pathForm({page: page})
    this.search()
  }
}
