import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SecurityOwner } from '../security/security-owner.service';

export const accessTokenInterceptor: HttpInterceptorFn = (req, next) => {

  let request = req
  const security = inject(SecurityOwner)

  if(security.accessToken() != undefined) {
    request = req.clone({headers: req.headers.append('Authorization', security.accessToken()!)})
  }

  return next(request);
};
