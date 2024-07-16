import { AfterViewInit, Component, computed, effect, ElementRef, ErrorHandler, Inject, inject, viewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AppExceptionHandler } from './services/utils/app-exception-handler';

declare let bootstrap:any

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
})
export class AppComponent implements AfterViewInit{

  errorMessages = computed(() => this.errorHandler.errorMessages())
  showErrorDialog = computed(() => this.errorMessages().length > 0)

  errorDialog = viewChild<ElementRef>('errorDialog')
  modalDialog:any

  constructor(@Inject(ErrorHandler) private errorHandler:AppExceptionHandler) {
    effect(() => {
      if(this.showErrorDialog()) {
        this.modalDialog?.show()
      }
    })
  }

  ngAfterViewInit(): void {
    this.modalDialog = new bootstrap.Modal(this.errorDialog()?.nativeElement)
    this.errorDialog()?.nativeElement.addEventListener('hidden.bs.modal', () => {
      this.errorHandler.errorMessages.set([])
    })
  }

  closeDialog() {
    this.modalDialog?.hide()
  }

}
