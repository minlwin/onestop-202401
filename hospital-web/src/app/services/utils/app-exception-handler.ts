import { HttpErrorResponse } from "@angular/common/http";
import { ErrorHandler, Injectable, signal } from "@angular/core";

@Injectable()
export class AppExceptionHandler implements ErrorHandler{

  errorMessages = signal<string[]>([])

  handleError(error: any): void {

    if(error instanceof HttpErrorResponse && error.error) {
      this.errorMessages.set(error.error)
    } else {
      console.log(error)
    }
  }
}
