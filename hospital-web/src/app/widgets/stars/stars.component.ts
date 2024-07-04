import { Component, computed, input } from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styles: ``
})
export class StarsComponent {

  value = input.required<number>()
  stars = computed(() => this.getStars(this.value()))

  private getStars(value:number) {
    const stars = ["bi-star-fill", "bi-star-fill", "bi-star-half", "bi-star", "bi-star"]
    return stars
  }
}
