import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SecurityOwner } from '../security/security-owner.service';

export const patientCanActivateGuard: CanActivateFn = (route, state) => {

  const security = inject(SecurityOwner)

  if(security.isPatient()) {
    return true
  }

  return false;
};
