import { Injectable } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { RouterStateSnapshot, TitleStrategy } from "@angular/router";

@Injectable({providedIn: 'root'})
export class AppTitleStrategy extends TitleStrategy {

  constructor(private readonly title:Title) {
    super();
  }

  override updateTitle(snapshot: RouterStateSnapshot): void {
    const title = this.buildTitle(snapshot)
    this.title.setTitle(`Hospital | ${title !== undefined ? title : 'Home'}`)
  }


}
