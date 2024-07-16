import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SecurityOwner } from '../security/security-owner.service';
import { catchError, switchMap, throwError } from 'rxjs';
import { AuthClientService } from '../client/auth-client.service';

export const tokenRefreshInterceptor: HttpInterceptorFn = (req, next) => {

  const security = inject(SecurityOwner)
  const authClient = inject(AuthClientService)

  return next(req).pipe(
    catchError(e => {

      if(e instanceof HttpErrorResponse) {
        if(e.status == 408 && security.refreshToken() != undefined) {
          return authClient.refreshToken({
            username: security.username(),
            refreshToken: security.refreshToken()
          }).pipe(
            switchMap(loginUser => {
              security.login(loginUser)
              return next(req.clone({headers: req.headers.append('Authorization', loginUser.accessToken)}))
            }),
            catchError(error => {
              security.logout()
              return throwError(() => error)
            })
          )
        }
      }

      return throwError(() => e)
    })
  );
};
