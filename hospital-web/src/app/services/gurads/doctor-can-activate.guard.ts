import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SecurityOwner } from '../security/security-owner.service';

export const doctorCanActivateGuard: CanActivateFn = (route, state) => {

  const security = inject(SecurityOwner)

  if(security.isAdmin() || security.isDoctor()) {
    return true
  }

  return false;
};
